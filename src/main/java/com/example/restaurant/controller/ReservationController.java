package com.example.restaurant.controller;
//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.util.List;
//import com.example.restaurant.controller.ReservationController;

//import com.example.restaurant.model.Reservation;
import com.example.restaurant.model.ReservationDto;
//import com.example.restaurant.model.Restaurant;
import com.example.restaurant.service.ReservationService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired

    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody ReservationDto reservationDto) {
        reservationService.createReservation(reservationDto);
        return ResponseEntity.ok("Reservation created successfully"); // .build();
    }

    @GetMapping
    public List<ReservationDto> getReservations() {
        return reservationService.getReservations();
    }

    @GetMapping("/confirm")
    public ResponseEntity<String> confirmReservation(@RequestParam String token) {

        reservationService.confirm(token);
        return ResponseEntity.ok("Reservation confirmed");
    }

    // @GetMapping("/{id}")
    // public Reservation getReservation(@PathVariable Long id) {
    //     return reservationService.getReservationById(id);
    // }

    // @DeleteMapping("/{id}")
    // public void deleteReservation(@PathVariable Long id) {
    //     reservationService.deleteReservation(id);
    // }
}
