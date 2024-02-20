package com.example.jackpills.dao;

import com.example.jackpills.entity.TypeOfPills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TypeOfPillsDao extends JpaRepository<TypeOfPills, UUID> {

    Optional<TypeOfPills> findByType(String type);
}