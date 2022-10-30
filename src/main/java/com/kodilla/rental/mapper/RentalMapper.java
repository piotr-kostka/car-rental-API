package com.kodilla.rental.mapper;

import com.kodilla.rental.domain.Rental;
import com.kodilla.rental.domain.dto.RentalDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalMapper {

    public Rental mapToRental(final RentalDto rentalDto) {
        return new Rental(
                rentalDto.getRentalId(),
                rentalDto.getRentDate(),
                rentalDto.getReturnDate(),
                rentalDto.getCurrency(),
                rentalDto.getPriceRate(),
                rentalDto.getTotalValue(),
                rentalDto.getLeftToPay(),
                rentalDto.getRentalStatus(),
                rentalDto.getPaymentDate(),
                rentalDto.getUser(),
                rentalDto.getCar()
        );
    }

    public RentalDto mapToRentalDto(final Rental rental) {
        return new RentalDto(
                rental.getRentalId(),
                rental.getRentDate(),
                rental.getReturnDate(),
                rental.getCurrency(),
                rental.getPriceRate(),
                rental.getTotalValue(),
                rental.getLeftToPay(),
                rental.getRentalStatus(),
                rental.getPaymentDate(),
                rental.getUser(),
                rental.getCar()
        );
    }

    public List<RentalDto> mapToRentalDtoList(final List<Rental> rentalList) {
        return rentalList.stream()
                .map(this::mapToRentalDto)
                .collect(Collectors.toList());
    }
}
