package com.kodilla.rental.repository;

import com.kodilla.rental.domain.Manufacturer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ManufacturerRepository extends CrudRepository<Manufacturer, Long> {
    @Override
    List<Manufacturer> findAll();

    @Override
    Optional<Manufacturer> findById(Long manufacturerId);

    @Override
    Manufacturer save(Manufacturer manufacturer);

    @Override
    void deleteById(Long manufacturerId);
}
