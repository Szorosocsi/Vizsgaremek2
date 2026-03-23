// package com.example.restaurant.service;

// import java.time.LocalDateTime;

// import java.util.List;
// import com.example.restaurant.model.ReservationDto;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.dao.DataIntegrityViolationException;
// import org.springframework.stereotype.Service;

// import com.example.restaurant.model.GuestTable;
// import com.example.restaurant.model.Reservation;
// import com.example.restaurant.repository.GuestTableRepository;
// import com.example.restaurant.repository.ReservationRepository;
// import jakarta.transaction.Transactional;

// @Service
// public class ReservationService {

//     @Autowired
//     private ReservationRepository reservationRepository;

//     @Autowired
//     private GuestTableRepository guestTableRepository;

//     @Transactional
//     @SuppressWarnings("@SuppressWarnings")
//     public Reservation saveReservation(ReservationDto reservationDto) {

//     Reservation reservation = new Reservation();

//     reservation.setName(reservationDto.name());
//     reservation.setEmail(reservationDto.email());
//     reservation.setNumberOfPeople(reservationDto.numberOfPeople());
//     reservation.setStartTime(reservationDto.startTime());
//     reservation.setTableId(reservationDto.tableId());

//     LocalDateTime endTime = reservation.getStartTime().plusHours(reservation.getDurationOfStay());

//     reservation.setEndTime(endTime);

//     boolean isConflict = reservationRepository.existsById(
//             reservation.getTableId(),
//             reservation.getStartTime(),
//             reservation.getEndTime()
//     );

//     if (isConflict) {
//         throw new DataIntegrityViolationException("");
//     }

//     return reservationRepository.save(reservation);
//     // itt jon letre a rekord.
// }

//     public void confirm(String token) {

//         Reservation reservation = reservationRepository.findByConfirmationToken(token);

//         if (reservation != null) {
//             reservation.setConfirmed(true);
//             reservationRepository.save(reservation);
//         }
//     }

//     public List<ReservationDto> getReservations() {

//     System.out.println("Getting reservations at " + LocalDateTime.now());
//     return reservationRepository.findAll().stream()
//             .map(reservation -> new ReservationDto(
//                     reservation.getName(),
//                     reservation.getEmail(),
//                     reservation.getNumberOfPeople(),
//                     reservation.getStartTime(),
//                     reservation.getTableId()
//             ))
//             .toList();
//     }           

//     @Transactional
//     public Reservation createReservation(Reservation reservation) {
//         Reservation saved = reservationRepository.save(reservation);


//         GuestTable table = guestTableRepository.findById(reservation.getTableId())
//         .orElseThrow(() -> new RuntimeException("Table not found"));


//         table.setReservation(saved);
//         table.setAvailability(false);

//         guestTableRepository.save(table);
//         return saved;
//     }
// }
   
package com.example.restaurant.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.restaurant.model.GuestTable;
import com.example.restaurant.model.Reservation;
import com.example.restaurant.model.ReservationDto;
import com.example.restaurant.repository.GuestTableRepository;
import com.example.restaurant.repository.ReservationRepository;

import jakarta.transaction.Transactional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private GuestTableRepository guestTableRepository;

    @Transactional
    public Reservation createReservation(ReservationDto reservationDto) {
        // 1. Reservation létrehozása DTO-ból
        Reservation reservation = new Reservation();
        reservation.setName(reservationDto.name());
        reservation.setEmail(reservationDto.email());
        reservation.setNumberOfPeople(reservationDto.numberOfPeople());
        reservation.setStartTime(reservationDto.startTime());
        reservation.setTableId(reservationDto.tableId());

        // 2. EndTime kiszámítása
        LocalDateTime endTime = reservation.getStartTime().plusHours(reservation.getDurationOfStay());
        reservation.setEndTime(endTime);

        // 3. Időütközés ellenőrzése
        boolean isConflict = reservationRepository.existsById(
                reservation.getTableId(),
                reservation.getStartTime(),
                reservation.getEndTime()
        );
        if (isConflict) {
            throw new DataIntegrityViolationException("Time conflict with existing reservation");
        }

        // 4. Reservation mentése
        Reservation savedReservation = reservationRepository.save(reservation);

        // 5. GuestTable frissítése: lekérés és kapcsolat létrehozása
        GuestTable table = new GuestTable();
        
        table.setMaxCapacity(getMaxCapacityByTableId(reservationDto.tableId()));
        table.setAvailability(false);
        table.setReservation(savedReservation);

        guestTableRepository.save(table);

        return savedReservation;
    }

    public int getMaxCapacityByTableId(long tableId) {
        switch ((int) tableId) {
            case 1: return 4;
            case 2: return 4;
            case 3: return 4;
            case 4: return 6;
            case 5: return 2;
            case 6: return 2;
            case 7: return 2;
            case 8: return 4;
            case 9: return 2;
            case 10: return 4;
            case 11: return 4;
            case 12: return 6;
            case 13: return 8;
            case 14: return 16;
            case 15: return 2;
            default: throw new IllegalArgumentException("Unknown table ID: " + tableId);
        }
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