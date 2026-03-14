package com.example.restaurant.service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;
import com.example.restaurant.model.ReservationDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.restaurant.model.Reservation;
import com.example.restaurant.repository.ReservationRepository;
import jakarta.transaction.Transactional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Transactional
    @SuppressWarnings("@SuppressWarnings")
    public Reservation saveReservation(ReservationDto reservationDto) {

    Reservation reservation = new Reservation();

    reservation.setName(reservationDto.name());
    reservation.setEmail(reservationDto.email());
    reservation.setNumberOfPeople(reservationDto.numberOfPeople());
    reservation.setStartTime(reservationDto.startTime());
    reservation.setTableId(reservationDto.tableId());

    LocalDateTime endTime = reservation.getStartTime().plusHours(reservation.getDurationOfStay());

    reservation.setEndTime(endTime);

    boolean isConflict = reservationRepository.existsById(
            reservation.getTableId(),
            reservation.getStartTime(),
            reservation.getEndTime()
    );

    if (isConflict) {
        throw new DataIntegrityViolationException("");
    }

    return reservationRepository.save(reservation);
    // itt jon letre a rekord.
}

    public void confirm(String token) {

        Reservation reservation = reservationRepository.findByConfirmationToken(token);

        if (reservation != null) {
            reservation.setConfirmed(true);
            reservationRepository.save(reservation);
        }
    }

    public List<ReservationDto> getReservations() {

    System.out.println("Getting reservations at " + LocalDateTime.now());
    return reservationRepository.findAll().stream()
            .map(reservation -> new ReservationDto(
                    reservation.getName(),
                    reservation.getEmail(),
                    reservation.getNumberOfPeople(),
                    reservation.getStartTime(),
                    reservation.getTableId()
            ))
            .toList();
    }           
}
   