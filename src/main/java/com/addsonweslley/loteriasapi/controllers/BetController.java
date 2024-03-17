package com.addsonweslley.loteriasapi.controllers;

import com.addsonweslley.loteriasapi.dtos.Bet.BetRequest;
import com.addsonweslley.loteriasapi.services.BetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bet")
public class BetController {

    private final BetService betService;

    public BetController(BetService betService) {
        this.betService = betService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createBet(@RequestBody BetRequest betRequest){
        return betService.createBet(betRequest);
    }

}
