package com.addsonweslley.loteriasapi.services;

import com.addsonweslley.loteriasapi.dtos.Bet.BetRequest;
import com.addsonweslley.loteriasapi.dtos.Bet.BetResponse;
import com.addsonweslley.loteriasapi.models.Bet;
import com.addsonweslley.loteriasapi.models.Ticket;
import com.addsonweslley.loteriasapi.repository.BetRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
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
        System.out.println(bet.getId());
        System.out.println(bet.getMaxNumbersByUsers());
        return new BetResponse(bet.getId(), bet.getMaxNumbersByUsers(), bet.getToltalNumbers());
    }

    public ResponseEntity<BetResponse> createBet(BetRequest betRequest) {
        Bet bet = new Bet();
        UUID uuid = UUID.randomUUID();
        bet.setId(uuid);
        bet.setToltalNumbers(betRequest.toltalNumbers());
        bet.setMaxNumbersByUsers(betRequest.maxNumbersByUsers());
        List<Ticket> ticketList = new ArrayList<>();
        bet.setTicketList(ticketList);
        betRepository.save(bet);
        return ResponseEntity.created(URI.create("/api/bet/get/"+uuid)).body(betToResponse(bet));
    }

    public ResponseEntity<?> getAllBets(Pageable pageable){
        var betPage = betRepository.findAll(pageable);
        var pageResponseBet = betPage.map(this::betToResponse);
        System.out.println(pageResponseBet);
        return ResponseEntity.ok().body(pageResponseBet);
    }

    public ResponseEntity<?> getBet(String uuidString) {
        UUID uuid = UUID.fromString(uuidString);
        Bet bet = betRepository.getReferenceById(uuid);
        return ResponseEntity.ok().body(betToResponse(bet));
    }

    // Método para converter a representação hexadecimal para UUID
    private String hexToStringUuid(String hex) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hex.length(); i += 2) {
            String str = hex.substring(i, i + 2);
            sb.append((char) Integer.parseInt(str, 16));
        }
        return sb.toString();
    }
}
