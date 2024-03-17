package com.addsonweslley.loteriasapi.services;

import com.addsonweslley.loteriasapi.dtos.Bet.BetRequest;
import com.addsonweslley.loteriasapi.dtos.Bet.BetResponse;
import com.addsonweslley.loteriasapi.models.Bet;
import com.addsonweslley.loteriasapi.models.Ticket;
import com.addsonweslley.loteriasapi.repository.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BetService {

    private final BetRepository betRepository;

    public BetService(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    public BetResponse betToResponse(Bet bet){
        return new BetResponse(bet.getId(), bet.getMaxNumbersByUsers(), bet.getToltalNumbers());
    }

    public ResponseEntity<BetResponse> createBet(BetRequest betRequest) {
        Bet bet = new Bet();
        bet.setId(UUID.randomUUID());
        bet.setToltalNumbers(betRequest.toltalNumbers());
        bet.setMaxNumbersByUsers(betRequest.maxNumbersByUsers());
        List<Ticket> ticketList = new ArrayList<Ticket>();
        bet.setTicketList(ticketList);
        betRepository.save(bet);
        return ResponseEntity.ok().body(betToResponse(bet));
    }
}
