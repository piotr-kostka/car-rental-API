package com.kodilla.rental.repository;

import com.kodilla.rental.domain.ModelPrice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ModelPriceRepository extends CrudRepository<ModelPrice, Long> {
    @Override
    List<ModelPrice> findAll();

    @Override
    Optional<ModelPrice> findById(Long modelPriceId);

    @Override
    ModelPrice save(ModelPrice modelPrice);

    @Override
    void deleteById(Long modelPriceId);
}
