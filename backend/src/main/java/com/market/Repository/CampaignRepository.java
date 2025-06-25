package com.market.Repository;

import com.market.Entities.Campaign;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    List<Campaign> findByIsActiveTrue();
}
