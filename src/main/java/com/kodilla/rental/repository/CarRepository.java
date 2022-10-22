package com.kodilla.rental.repository;

import com.kodilla.rental.domain.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    @Override
    List<Car> findAll();

    @Override
    Optional<Car> findById(Long carId);

    @Override
    Car save(Car car);

    @Override
    void deleteById(Long carId);
}
