package com.example.jackpills.dao;

import com.example.jackpills.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScheduleDao extends JpaRepository<Schedule, UUID> {
}
