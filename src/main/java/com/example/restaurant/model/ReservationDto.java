package com.example.restaurant.model;

import java.time.LocalDateTime;


public record ReservationDto(
    String name,
    String email,
    int numberOfPeople,
    LocalDateTime startTime,
    long tableId
) {}

