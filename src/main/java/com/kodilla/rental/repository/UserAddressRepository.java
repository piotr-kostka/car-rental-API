package com.kodilla.rental.repository;

import com.kodilla.rental.domain.UserAddress;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserAddressRepository extends CrudRepository<UserAddress, Long> {

    @Override
    List<UserAddress> findAll();

    @Override
    Optional<UserAddress> findById(Long userAddressId);

    @Override
    UserAddress save(UserAddress userAddress);

    @Override
    void deleteById(Long userAddressId);
}
