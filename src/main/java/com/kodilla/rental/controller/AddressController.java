package com.kodilla.rental.controller;

import com.kodilla.rental.domain.dto.AddressDto;
import com.kodilla.rental.service.AddressDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/users/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressDbService addressDbService;

    @GetMapping
    public List<AddressDto> getAddresses() {
        return addressDbService.getAddresses();
    }

    @GetMapping(value = "{addressId}")
    public AddressDto getAddress(@PathVariable long addressId) {
        return addressDbService.getAddress(addressId);
    }

    @DeleteMapping(value = "{addressId}")
    public void deleteAddress(@PathVariable long addressId) {
        addressDbService.deleteAddress(addressId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public AddressDto createAddress(@RequestBody AddressDto addressDto) {
        return addressDbService.createAddress(addressDto);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public AddressDto updateAddress(@RequestBody AddressDto addressDto) {
        return addressDbService.updateAddress(addressDto);
    }

}
