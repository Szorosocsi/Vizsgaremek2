package com.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

//import com.example.restaurant.model.Restaurant_db;
import com.example.restaurant.model.Reservation;


// itt vagy-vagy az annotáció: @Repository | interface extends JpaRepository<Reservation
// inkább biztosra mentem.
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Reservation findByConfirmationToken(String token);


    /*
    SELECT COUNT(*) > 0
    FROM reservation r
    WHERE r.table_id = ?
        AND ? < r.end_time
        AND ? > r.start_time; 
    */
    @Query ("select case when count(r) > 0 then true else false end FROM Reservation r where r.tableId = :tableId and :startTime < r.endTime and :endTime > r.startTime")
    // Binding: :tableId, :startTime, :endTime
        boolean existsById(@Param("tableId") long tableId, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);    
}


/*Standart Overlap vizsgálat
                                    existing reservation
                                |------- r.startTime ------ r.endTime -------|

                                new reservation
                                    |---- startTime ---- endTime ----|
*/
