package com.kodilla.rental.service;

import com.kodilla.rental.domain.Rental;
import com.kodilla.rental.domain.dto.RentalDto;
import com.kodilla.rental.exception.notFound.RentalNotFoundException;
import com.kodilla.rental.exception.notFound.UserNotFoundException;
import com.kodilla.rental.mapper.RentalMapper;
import com.kodilla.rental.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentalDbService {
    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;

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
        Rental savedRental = rentalRepository.save(rental);
        return rentalMapper.mapToRentalDto(savedRental);
    }

    @Transactional
    public RentalDto updateRental(final RentalDto rentalDto) {
        Rental rental = rentalMapper.mapToRental(rentalDto);
        Rental savedRental = rentalRepository.save(rental);
        return rentalMapper.mapToRentalDto(savedRental);
    }

    @Transactional
    public void deleteRental(final long rentalId) {
        rentalRepository.deleteById(rentalId);
    }
}
