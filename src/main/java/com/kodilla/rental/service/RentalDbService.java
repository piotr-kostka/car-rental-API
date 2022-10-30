package com.kodilla.rental.service;

import com.kodilla.rental.client.NbpApiClient;
import com.kodilla.rental.domain.Car;
import com.kodilla.rental.domain.Rental;
import com.kodilla.rental.domain.dto.RentalDto;
import com.kodilla.rental.domain.enums.CarStatus;
import com.kodilla.rental.domain.enums.RentalStatus;
import com.kodilla.rental.domain.mail.Mail;
import com.kodilla.rental.exception.notFound.RentalNotFoundException;
import com.kodilla.rental.exception.notFound.UserNotFoundException;
import com.kodilla.rental.mapper.RentalMapper;
import com.kodilla.rental.repository.RentalRepository;
import com.kodilla.rental.service.api.WeatherConditionsRate;
import com.kodilla.rental.service.mail.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentalDbService {
    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;
    private final EmailService emailService;
    private static final String SUBJECT_NEW_RENTAL = "Rental Application: New Rental";
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
    public RentalDto createRental(final RentalDto rentalDto) {
        Rental rental = rentalMapper.mapToRental(rentalDto);
        Car car = rental.getCar();
        car.setCarStatus(CarStatus.RENTED);
        rental.setPriceRate(weatherConditionsRate.getWeatherRate());
        Rental savedRental = rentalRepository.save(rental);
        emailService.send(
                new Mail(
                        savedRental.getUser().getMail(),
                        SUBJECT_NEW_RENTAL,
                        "New rental no. " + savedRental.getRentalId() + ".\n" + "Rented car: " + savedRental.getCar() + ".\n"
                        + "Rent date: " + savedRental.getRentDate() + ". Return date: " + savedRental.getReturnDate()
                )
        );
        return rentalMapper.mapToRentalDto(savedRental);
    }

    @Transactional
    public RentalDto updateRental(final RentalDto rentalDto) {
        Rental rental = rentalMapper.mapToRental(rentalDto);
        Rental savedRental = rentalRepository.save(rental);
        return rentalMapper.mapToRentalDto(savedRental);
    }

    @Transactional
    public void returnCar(final long rentalId) {
        Optional<Rental> rental = rentalRepository.findById(rentalId);

        if (rental.isPresent()) {
            Car car = rental.get().getCar();
            car.setCarStatus(CarStatus.AVAILABLE);
            rental.get().setRentalStatus(RentalStatus.RETURNED_AND_TO_PAY);
            rental.get().setReturnDate(LocalDate.now());
            long days = ChronoUnit.DAYS.between(rental.get().getReturnDate(), rental.get().getRentDate());
            double priceRate = rental.get().getPriceRate();
            double currencyRate = nbpApiClient.getExchangeRate(rental.get().getCurrency());
            BigDecimal priceToPay = rental.get().getCar().getPrice().multiply(BigDecimal.valueOf(days))
                    .multiply(BigDecimal.valueOf(priceRate)).multiply(BigDecimal.valueOf(currencyRate));
            rental.get().setTotalValue(priceToPay);
            rental.get().setLeftToPay(priceToPay);
        }
    }

    @Transactional
    public void makePayment(final long rentalId) {
        Optional<Rental> rental = rentalRepository.findById(rentalId);

        if (rental.isPresent()) {
            rental.get().setLeftToPay(BigDecimal.ZERO);
            rental.get().setPaymentDate(LocalDate.now());
            rental.get().setRentalStatus(RentalStatus.RETURNED_AND_PAID);
        }
    }
}
