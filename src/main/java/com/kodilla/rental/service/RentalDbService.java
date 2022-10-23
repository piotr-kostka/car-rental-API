package com.kodilla.rental.service;

import com.kodilla.rental.domain.Rental;
import com.kodilla.rental.domain.dto.RentalDto;
import com.kodilla.rental.exception.notFound.RentalNotFoundException;
import com.kodilla.rental.mapper.RentalMapper;
import com.kodilla.rental.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
