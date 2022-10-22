package com.kodilla.rental.repository;

import com.kodilla.rental.domain.Rental;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RentalRepository extends CrudRepository<Rental, Long> {
    @Override
    List<Rental> findAll();

    @Override
    Optional<Rental> findById(Long rentalId);

    @Override
    Rental save(Rental rental);

    @Override
    void deleteById(Long rentalId);
}
