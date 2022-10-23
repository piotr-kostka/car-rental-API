package com.kodilla.rental.mapper;

import com.kodilla.rental.domain.Address;
import com.kodilla.rental.domain.dto.AddressDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressMapper {

    public Address mapToAddress(final AddressDto addressDto) {
        return new Address(
                addressDto.getAddressId(),
                addressDto.getCity(),
                addressDto.getPostalNumber(),
                addressDto.getStreet(),
                addressDto.getHouseNumber(),
                addressDto.getApartmentNumber()
        );
    }

    public AddressDto mapToAddressDto(final Address address) {
        return new AddressDto(
                address.getAddressId(),
                address.getCity(),
                address.getPostalNumber(),
                address.getStreet(),
                address.getHouseNumber(),
                address.getApartmentNumber()
        );
    }

    public List<AddressDto> mapToAddressDtoList(final List<Address> addressList) {
        return addressList.stream()
                .map(this::mapToAddressDto)
                .collect(Collectors.toList());
    }
}
