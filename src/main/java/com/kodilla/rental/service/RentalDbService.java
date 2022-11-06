package com.kodilla.rental.service;

import com.kodilla.rental.client.NbpApiClient;
import com.kodilla.rental.config.AdminConfig;
import com.kodilla.rental.domain.Car;
import com.kodilla.rental.domain.Rental;
import com.kodilla.rental.domain.User;
import com.kodilla.rental.domain.dto.RentalDto;
import com.kodilla.rental.domain.enums.CarStatus;
import com.kodilla.rental.domain.enums.RentalStatus;
import com.kodilla.rental.domain.mail.Mail;
import com.kodilla.rental.exception.notFound.RentalNotFoundException;
import com.kodilla.rental.exception.notFound.UserNotFoundException;
import com.kodilla.rental.exception.rental.CarCannotBeRented;
import com.kodilla.rental.mapper.RentalMapper;
import com.kodilla.rental.repository.CarRepository;
import com.kodilla.rental.repository.RentalRepository;
import com.kodilla.rental.repository.UserRepository;
import com.kodilla.rental.service.api.WeatherConditionsRate;
import com.kodilla.rental.service.mail.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentalDbService {
    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final RentalMapper rentalMapper;
    private final EmailService emailService;
    private static final String SUBJECT_NEW_RENTAL = "Rental Application: New Rental";
    private static final String SUBJECT_RETURN_CAR = "Rental Application: Return confirmation";
    private static final String SUBJECT_NEW_PAYMENT = "Rental Application: Payment confirmation";
    private final AdminConfig adminConfig;
    private final NbpApiClient nbpApiClient;
    private final WeatherConditionsRate weatherConditionsRate;

    public List<RentalDto> getRentals() {
        List<Rental> rentals = rentalRepository.findAll();
        return rentalMapper.mapToRentalDtoList(rentals);
    }

    public RentalDto getRental(final long rentalId) throws RentalNotFoundException {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(() -> new RentalNotFoundException(rentalId));
        return rentalMapper.mapToRentalDto(rental);
    }

    public List<RentalDto> getUserRentals(final long userId) throws UserNotFoundException {
        List<Rental> userRentals = rentalRepository.findAll().stream()
                .filter(r -> r.getUser().getUserId() == userId)
                .collect(Collectors.toList());
        return rentalMapper.mapToRentalDtoList(userRentals);
    }

    @Transactional
    public RentalDto createRental(final RentalDto rentalDto) throws CarCannotBeRented {
        Rental rental = rentalMapper.mapToRental(rentalDto);
        Car car = rental.getCar();
        if (!car.getCarStatus().equals(CarStatus.RENTED)) {
            car.setCarStatus(CarStatus.RENTED);
            carRepository.save(car);
            rental.setRentDate(LocalDate.now());
            rental.setPriceRate(weatherConditionsRate.getWeatherRate());
            Rental savedRental = rentalRepository.save(rental);
            emailService.send(
                    new Mail(
                            savedRental.getUser().getMail(),
                            SUBJECT_NEW_RENTAL,
                            "This is confirmation of renting a car.\n" + "Rented car: " + savedRental.getCar() + ".\n"
                                    + "Rent date: " + savedRental.getRentDate(),
                            adminConfig.getAdminMail()
                    )
            );
            return rentalMapper.mapToRentalDto(savedRental);
        } else {
            throw new CarCannotBeRented();
        }
    }

    @Transactional
    public void returnCar(final long rentalId) throws RentalNotFoundException {
        Optional<Rental> rental = rentalRepository.findById(rentalId);

        if (rental.isPresent()) {
            Car car = rental.get().getCar();
            car.setCarStatus(CarStatus.AVAILABLE);
            carRepository.save(car);

            rental.get().setRentalStatus(RentalStatus.RETURNED_AND_TO_PAY);
            rental.get().setReturnDate(LocalDate.now());
            long days = ChronoUnit.DAYS.between(rental.get().getReturnDate(), rental.get().getRentDate()) + 1;
            double priceRate = rental.get().getPriceRate();
            double currencyRate = 1 / nbpApiClient.getExchangeRate(rental.get().getCurrency());
            BigDecimal priceToPay = rental.get().getCar().getPrice().multiply(BigDecimal.valueOf(days))
                    .multiply(BigDecimal.valueOf(priceRate)).multiply(BigDecimal.valueOf(currencyRate)).setScale(2, RoundingMode.HALF_UP);
            rental.get().setTotalValue(priceToPay);
            rental.get().setLeftToPay(priceToPay);

            User user = rental.get().getUser();
            user.setToPay(priceToPay);
            userRepository.save(user);
            rentalRepository.save(rental.get());

            emailService.send(
                    new Mail(
                            rental.get().getUser().getMail(),
                            SUBJECT_RETURN_CAR,
                            "This is confirmation of returning a car.\n" + "Rented car: " + rental.get().getCar() + ".\n"
                                    + "Rent date: " + rental.get().getRentDate() + ", return date: " + rental.get().getReturnDate() + ".\n"
                                    + "To pay: " + rental.get().getTotalValue() + rental.get().getCurrency(),
                            adminConfig.getAdminMail()
                    )
            );
        } else {
            throw new RentalNotFoundException(rentalId);
        }
    }

    @Transactional
    public void makePayment(final long rentalId) throws RentalNotFoundException {
        Optional<Rental> rental = rentalRepository.findById(rentalId);

        if (rental.isPresent()) {
            rental.get().setLeftToPay(BigDecimal.ZERO);
            rental.get().setPaymentDate(LocalDate.now());
            rental.get().setRentalStatus(RentalStatus.RETURNED_AND_PAID);
            rentalRepository.save(rental.get());

            User user = rental.get().getUser();
            user.setToPay(user.getToPay().subtract(rental.get().getTotalValue()));
            userRepository.save(user);

            emailService.send(
                    new Mail(
                            rental.get().getUser().getMail(),
                            SUBJECT_NEW_PAYMENT,
                            "This is confirmation of payment.\n" + "Paid: " + rental.get().getTotalValue() + rental.get().getCurrency() +
                                    ". " + "Payment date: " + rental.get().getPaymentDate() + ".\n"
                                    + "On your account left to pay: " + rental.get().getUser().getToPay(),
                            adminConfig.getAdminMail()
                    )
            );
        } else {
            throw new RentalNotFoundException(rentalId);
        }
    }
}
