package com.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restaurant.model.GuestTable;


@Repository
public interface GuestTableRepository extends JpaRepository<GuestTable, Long> {

}
