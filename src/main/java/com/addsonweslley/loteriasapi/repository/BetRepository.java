package com.addsonweslley.loteriasapi.repository;

import com.addsonweslley.loteriasapi.models.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BetRepository extends JpaRepository<Bet, UUID> {
}
