package com.kodilla.rental.repository;

import com.kodilla.rental.domain.Model;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ModelRepository extends CrudRepository<Model, Long> {
    @Override
    List<Model> findAll();

    @Override
    Optional<Model> findById(Long modelId);

    @Override
    Model save(Model model);

    @Override
    void deleteById(Long modelId);
}
