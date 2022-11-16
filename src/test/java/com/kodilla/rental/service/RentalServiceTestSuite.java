package com.kodilla.rental.service;

import com.kodilla.rental.client.NbpApiClient;
import com.kodilla.rental.domain.*;
import com.kodilla.rental.domain.dto.RentalDto;
import com.kodilla.rental.domain.enums.CarStatus;
import com.kodilla.rental.domain.enums.Currency;
import com.kodilla.rental.domain.enums.RentalStatus;
import com.kodilla.rental.domain.enums.model.BodyType;
import com.kodilla.rental.domain.enums.model.FuelType;
import com.kodilla.rental.domain.enums.model.TransmissionType;
import com.kodilla.rental.mapper.RentalMapper;
import com.kodilla.rental.repository.CarRepository;
import com.kodilla.rental.repository.RentalRepository;
import com.kodilla.rental.repository.UserRepository;
import com.kodilla.rental.service.api.WeatherConditionsRate;
import com.kodilla.rental.service.mail.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RentalServiceTestSuite {

    @InjectMocks
    private RentalDbService rentalService;

    @Mock
    private RentalMapper rentalMapper;

    @Mock
    private RentalRepository rentalRepository;

    @Mock
    private CarRepository carRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private WeatherConditionsRate weatherConditionsRate;

    @Mock
    private NbpApiClient nbpApiClient;

    @Mock
    private EmailService emailService;

    private Rental rental;
    private RentalDto rentalDto;
    private final List<Rental> rentalList = new ArrayList<>();
    private final List<RentalDto> rentalDtoList = new ArrayList<>();
    private Car car;
    private User user;

    @BeforeEach
    void prepareData() {
        Manufacturer manufacturer = new Manufacturer(1L, "test name", new HashSet<>());
        Model model = new Model(1L, manufacturer, "name", 1800.00, BodyType.SEDAN, 2012, "red", 5, 4, FuelType.DIESEL, TransmissionType.AUTOMATIC, new HashSet<>());
        car = new Car(1L, model, "ST11111", BigDecimal.valueOf(150), CarStatus.AVAILABLE, new ArrayList<>());
        user = new User(1L, "name", "lastname", 940930123212L, "address", "mail@mail.com", "password", "123456789", BigDecimal.ZERO, LocalDate.of(2022,9,22), new HashSet<>());
        rental = new Rental(1L, LocalDate.of(2022,10,10), LocalDate.of(2022,10,11), Currency.EUR,
                1.5, BigDecimal.valueOf(150), BigDecimal.valueOf(150), RentalStatus.RENTED, LocalDate.of(2022,10,11), user, car);
        rentalDto = new RentalDto(1L, LocalDate.of(2022,10,10), LocalDate.of(2022,10,11), Currency.EUR,
                1.5, BigDecimal.valueOf(150), BigDecimal.valueOf(150), RentalStatus.RETURNED, LocalDate.of(2022,10,11), user, car);
        rentalList.add(rental);
        rentalDtoList.add(rentalDto);
    }

    @Test
    void getAllRentalsTest() {
        //Given
        when(rentalMapper.mapToRentalDtoList(rentalList)).thenReturn(rentalDtoList);
        when(rentalRepository.findAll()).thenReturn(rentalList);

        //When
        List<RentalDto> expectedList = rentalService.getRentals();

        //Then
        assertEquals(1, expectedList.size());
        assertEquals(1L, expectedList.get(0).getRentalId());
        assertEquals(LocalDate.of(2022,10,10), expectedList.get(0).getRentDate());
        assertEquals(LocalDate.of(2022,10,11), expectedList.get(0).getReturnDate());
        assertEquals(Currency.EUR, expectedList.get(0).getCurrency());
        assertEquals(1.5, expectedList.get(0).getPriceRate());
        assertEquals(BigDecimal.valueOf(150), expectedList.get(0).getTotalValue());
        assertEquals(BigDecimal.valueOf(150), expectedList.get(0).getLeftToPay());
        assertEquals(RentalStatus.RETURNED, expectedList.get(0).getRentalStatus());
        assertEquals(LocalDate.of(2022,10,11), expectedList.get(0).getPaymentDate());
        assertEquals(user, expectedList.get(0).getUser());
        assertEquals(car, expectedList.get(0).getCar());
    }

    @Test
    void getRentalTest() {
        //Given
        when(rentalMapper.mapToRentalDto(rental)).thenReturn(rentalDto);
        when(rentalRepository.findById(rentalDto.getRentalId())).thenReturn(Optional.of(rental));

        //When
        RentalDto expected = rentalService.getRental(1L);

        //Then
        assertEquals(1L, expected.getRentalId());
        assertEquals(LocalDate.of(2022,10,10), expected.getRentDate());
        assertEquals(LocalDate.of(2022,10,11), expected.getReturnDate());
        assertEquals(Currency.EUR, expected.getCurrency());
        assertEquals(1.5, expected.getPriceRate());
        assertEquals(BigDecimal.valueOf(150), expected.getTotalValue());
        assertEquals(BigDecimal.valueOf(150), expected.getLeftToPay());
        assertEquals(RentalStatus.RETURNED, expected.getRentalStatus());
        assertEquals(LocalDate.of(2022,10,11), expected.getPaymentDate());
        assertEquals(user, expected.getUser());
        assertEquals(car, expected.getCar());
    }

    @Test
    void getUserRentalsTest() {
        //Given
        when(rentalMapper.mapToRentalDtoList(rentalList)).thenReturn(rentalDtoList);
        when(rentalRepository.findAll()).thenReturn(rentalList);

        //When
        List<RentalDto> expectedList = rentalService.getUserRentals(1L);

        //Then
        assertEquals(1, expectedList.size());
    }

    @Test
    void createRentalTest() {
        //Given
        when(rentalMapper.mapToRental(rentalDto)).thenReturn(rental);
        when(carRepository.save(car)).thenReturn(rental.getCar());
        Rental savedRental = rentalMapper.mapToRental(rentalDto);
        when(weatherConditionsRate.getWeatherRate()).thenReturn(1.5);
        when(rentalRepository.save(rental)).thenReturn(savedRental);
        when(rentalMapper.mapToRentalDto(savedRental)).thenReturn(rentalDto);

        //When
        RentalDto expected = rentalService.createRental(rentalDto);

        //Then
        verify(emailService,times(1)).send(any());

        assertEquals(1L, expected.getRentalId());
        assertEquals(LocalDate.of(2022,10,10), expected.getRentDate());
        assertEquals(LocalDate.of(2022,10,11), expected.getReturnDate());
        assertEquals(Currency.EUR, expected.getCurrency());
        assertEquals(1.5, expected.getPriceRate());
        assertEquals(BigDecimal.valueOf(150), expected.getTotalValue());
        assertEquals(BigDecimal.valueOf(150), expected.getLeftToPay());
        assertEquals(RentalStatus.RETURNED, expected.getRentalStatus());
        assertEquals(LocalDate.of(2022,10,11), expected.getPaymentDate());
        assertEquals(user, expected.getUser());
        assertEquals(car, expected.getCar());
    }

    @Test
    void returnCarTest() {
        //Given
        when(rentalRepository.findById(1L)).thenReturn(Optional.ofNullable(rental));
        when(nbpApiClient.getExchangeRate(rental.getCurrency())).thenReturn(1.00);

        //When
        rentalService.returnCar(rental.getRentalId());

        //Then
        verify(rentalRepository, times(1)).findById(1L);
        verify(carRepository, times(1)).save(car);
        verify(nbpApiClient, times(1)).getExchangeRate(rental.getCurrency());
        verify(userRepository, times(1)).save(user);
        verify(rentalRepository, times(1)).save(rental);
        verify(emailService, times(1)).send(any());
    }

    @Test
    void makePaymentTest() {
        //Given
        when(rentalRepository.findById(1L)).thenReturn(Optional.ofNullable(rental));

        //When
        rentalService.makePayment(rental.getRentalId());

        //Then
        verify(rentalRepository, times(1)).findById(1L);
        verify(rentalRepository, times(1)).save(rental);
        verify(userRepository, times(1)).save(user);
        verify(emailService, times(1)).send(any());
    }

}
