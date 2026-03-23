package com.example.restaurant.model;

public record GuestTableDto(
    int guestId,
    int maxCapacity,
    boolean availability,
    int waiterId
) {}
