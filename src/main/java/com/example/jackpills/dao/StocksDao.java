package com.example.jackpills.dao;

import com.example.jackpills.entity.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StocksDao extends JpaRepository<Stocks, UUID> {

    Optional<Stocks> findByTypeOfPillsGidAndPillGidAndQuantity(UUID gid, UUID gid1, Integer quantity);
    List<Stocks> findAllByTypeOfPillsGid(UUID gid);
}
