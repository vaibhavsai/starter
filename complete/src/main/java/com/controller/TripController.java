package com.controller;

import com.model.Customer;
import com.model.Driver;
import com.model.Trip;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;


@RestController
@Validated
@RequestMapping(value = "/cabs/", produces = MediaType.APPLICATION_JSON_VALUE)
public class TripController
{
    @GetMapping("Drivers")
    public ResponseEntity<List<Driver>> getTopNDrivers(@Min(value = 1, message =  "Invalid number of drivers given") Integer n) throws Exception
    {
        return null;
    }

    @GetMapping("Customers")
    public ResponseEntity<List<Customer>> getTopNCustomers(@Min(value = 1, message =  "Invalid number of customers given") Integer n) throws Exception
    {
        return null;
    }

    @PostMapping("Trip")
    public ResponseEntity<Trip> postTrip(@Valid @RequestBody Trip trip) throws Exception
    {
        return null;
    }

    @GetMapping("Match")
    public ResponseEntity<List<Driver>> getDriversForCustomers(String customerId) throws Exception
    {
        return null;
    }

    @GetMapping("customerAvg")
    public ResponseEntity<Double> getAvgCustomerRating(String customerId) throws Exception
    {
        return null;
    }

    @GetMapping("driverAvg")
    public ResponseEntity<Double> getAvgDriverRating(String driverId) throws Exception
    {
        return null;
    }
}
