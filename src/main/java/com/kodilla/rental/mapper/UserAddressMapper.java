package com.kodilla.rental.mapper;

import com.kodilla.rental.domain.UserAddress;
import com.kodilla.rental.domain.dto.UserAddressDto;

import java.util.List;
import java.util.stream.Collectors;

public class UserAddressMapper {

    public UserAddress mapToUserAddress(final UserAddressDto userAddressDto) {
        return new UserAddress(
                userAddressDto.getAddressId(),
                userAddressDto.getCity(),
                userAddressDto.getPostalNumber(),
                userAddressDto.getStreet(),
                userAddressDto.getHouseNumber(),
                userAddressDto.getApartmentNumber()
        );
    }

    public UserAddressDto mapToUserAddressDto(final UserAddress userAddress) {
        return new UserAddressDto(
                userAddress.getAddressId(),
                userAddress.getCity(),
                userAddress.getPostalNumber(),
                userAddress.getStreet(),
                userAddress.getHouseNumber(),
                userAddress.getApartmentNumber()
        );
    }

    public List<UserAddressDto> mapToUserAddressDtoList(final List<UserAddress> userAddressList) {
        return userAddressList.stream()
                .map(this::mapToUserAddressDto)
                .collect(Collectors.toList());
    }
}
