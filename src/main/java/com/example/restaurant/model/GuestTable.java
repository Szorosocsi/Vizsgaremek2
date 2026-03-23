package com.example.restaurant.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class GuestTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int maxCapacity;
    private int guestId;
    private boolean availability;
    private int waiterId;


    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
    

    public GuestTable() // default Constructor
    {

    }   


    public GuestTable(int maxCapacity, int guestId, boolean availability, int waiterId) {
        this.maxCapacity = maxCapacity;
        this.guestId = guestId;
        this.availability = availability;
        this.waiterId = waiterId;
    }



    public long getId() { return id; }

    
    public int getMaxCapacity() { return maxCapacity; }
    
    public void setMaxCapacity(int maxCapacity)
    { this.maxCapacity = maxCapacity; }


    public int getGuestId() 
    { return guestId; }    
    
    public void setGuestId(int guestId)
    { this.guestId = guestId; }
    

    public boolean isAvailability()
    {  return availability;  }
    
    public void setAvailability(boolean availability)
    {  this.availability = availability; }
    

    public int getWaiter_Id()
    {  return waiterId; }
    
    public void setWaiter_Id(int waiterId)
    { this.waiterId = waiterId; }


    public void setReservation(Reservation reservation)
    { this.reservation = reservation; }
    
}
