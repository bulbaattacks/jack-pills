package com.example.jackpills.dao;

import com.example.jackpills.entity.Intervals;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IntervalsDao extends JpaRepository<Intervals, UUID> {

    Optional<Intervals> findByTypeOfPillsGidAndColdMonthsAndWarmMonths(UUID gid, Integer coldMonths, Integer warmMonths);

    Optional<Intervals> findByTypeOfPillsGid(UUID gid);
}
