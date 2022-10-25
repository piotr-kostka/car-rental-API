package com.kodilla.rental.service;

import com.kodilla.rental.domain.Address;
import com.kodilla.rental.domain.dto.AddressDto;
import com.kodilla.rental.exception.notFound.AddressNotFoundException;
import com.kodilla.rental.exception.notFound.UserNotFoundException;
import com.kodilla.rental.mapper.AddressMapper;
import com.kodilla.rental.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressDbService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public List<AddressDto> getAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return addressMapper.mapToAddressDtoList(addresses);
    }

    public AddressDto getAddress(final long addressId) throws AddressNotFoundException {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new UserNotFoundException(addressId));
        return addressMapper.mapToAddressDto(address);
    }

    @Transactional
    public AddressDto createAddress(final AddressDto addressDto) {
        Address address = addressMapper.mapToAddress(addressDto);
        Address savedAddress = addressRepository.save(address);
        return addressMapper.mapToAddressDto(savedAddress);
    }

    @Transactional
    public AddressDto updateAddress(final AddressDto addressDto) {
        Address address = addressMapper.mapToAddress(addressDto);
        Address savedAddress = addressRepository.save(address);
        return addressMapper.mapToAddressDto(savedAddress);
    }

    @Transactional
    public void deleteAddress(final long addressId) {
        addressRepository.deleteById(addressId);
    }
}
