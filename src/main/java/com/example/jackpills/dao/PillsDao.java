package com.example.jackpills.dao;

import com.example.jackpills.entity.Pills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PillsDao extends JpaRepository<Pills, UUID> {

    Optional<Pills> findByTypeOfPillsGidAndName (UUID gid, String name);
}
