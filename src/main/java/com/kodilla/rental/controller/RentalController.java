package com.kodilla.rental.controller;

import com.kodilla.rental.domain.dto.RentalDto;
import com.kodilla.rental.service.RentalDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/rentals")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RentalController {

    private final RentalDbService rentalDbService;

    @GetMapping
    public List<RentalDto> getRentals() {
        return rentalDbService.getRentals();
    }

    @GetMapping(value = "{rentalId}")
    public RentalDto getRental(@PathVariable long rentalId) {
        return rentalDbService.getRental(rentalId);
    }

    @GetMapping(value = "/user/{userId}")
    public List<RentalDto> getUserRentals(@PathVariable long userId) {
        return rentalDbService.getUserRentals(userId);
    }

    @PutMapping(value = "/return/{rentalId}")
    public void returnCar(@PathVariable long rentalId) {
        rentalDbService.returnCar(rentalId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public RentalDto createRental(@RequestBody RentalDto rentalDto){
        return rentalDbService.createRental(rentalDto);
    }

    @PutMapping(value = "/pay/{rentalId}")
    public void makePayment(@PathVariable long rentalId) {
        rentalDbService.makePayment(rentalId);
    }
}