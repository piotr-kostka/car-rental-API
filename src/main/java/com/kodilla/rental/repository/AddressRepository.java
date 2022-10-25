package com.kodilla.rental.repository;

import com.kodilla.rental.domain.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
    @Override
    List<Address> findAll();

    @Override
    Optional<Address> findById(Long userAddressId);

    @Override
    Address save(Address address);

    @Override
    void deleteById(Long userAddressId);
}
