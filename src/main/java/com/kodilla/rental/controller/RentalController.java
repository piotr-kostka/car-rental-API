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

    @GetMapping(value = "/userRentals/{userId}")
    public List<RentalDto> getUserRentals(@PathVariable long userId) {
        return rentalDbService.getUserRentals(userId);
    }

    @DeleteMapping(value = "{rentalId}")
    public void returnCar(@PathVariable long rentalId) {
        rentalDbService.deleteRental(rentalId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public RentalDto createRental(@RequestBody RentalDto rentalDto){
        return rentalDbService.createRental(rentalDto);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public RentalDto updateRental(@RequestBody RentalDto rentalDto){
        return rentalDbService.updateRental(rentalDto);
    }
}

//makePayment
//return do poprawy