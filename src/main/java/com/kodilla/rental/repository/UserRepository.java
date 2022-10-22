package com.kodilla.rental.repository;

import com.kodilla.rental.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(Long userId);

    @Override
    User save(User user);

    @Override
    void deleteById(Long userId);
}
