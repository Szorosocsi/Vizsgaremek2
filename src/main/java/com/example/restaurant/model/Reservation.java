package com.example.restaurant.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

// import org.springframework.cglib.core.Local;

// import io.micrometer.common.lang.Nullable;


// automatikusan generál egy reservation táblát a db-ben. 
// A Db-t eléri: application.properties 
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private int numberOfPeople;
    
    private LocalDateTime startTime;
    
    private long durationOfStay = 2; // órában számolva

    
    private long tableId; 
    private LocalDateTime endTime;


    private LocalDateTime reservedAt = LocalDateTime.now();
    private boolean confirmed;
    private String confirmationToken;
    

    public Reservation() { }

    public Reservation(String name, String email, int numberOfPeople, LocalDateTime dateTime, long tableId) {
        this.name = name;
        this.email = email;
        this.numberOfPeople = numberOfPeople;
        this.startTime = dateTime;
        this.tableId = tableId;
    }

    // A terv az volt, hogy lombokon használok, de az nem akart működni, így kézzel írtam meg a gettereket és settereket.
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getNumberOfPeople() { return numberOfPeople; }
    public void setNumberOfPeople(int numberOfPeople) { this.numberOfPeople = numberOfPeople; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public long getTableId() { return tableId; }
    public void setTableId(long tableId) { this.tableId = tableId; }

    public LocalDateTime getReservedAt() { return reservedAt; }
    public void setReservedAt(LocalDateTime reservedAt) { this.reservedAt = reservedAt; }

    public boolean isConfirmed() { return confirmed; }
    public void setConfirmed(boolean confirmed) { this.confirmed = confirmed; }

    public String getConfirmationToken() { return confirmationToken; }
    public void setConfirmationToken(String confirmationToken) { this.confirmationToken = confirmationToken; }

    public long getDurationOfStay() { return durationOfStay; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    

}