package com.addsonweslley.loteriasapi.controllers;

import com.addsonweslley.loteriasapi.dtos.Bet.BetRequest;
import com.addsonweslley.loteriasapi.services.BetService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllBets(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return betService.getAllBets(pageable);
    }

    @GetMapping("/{UUID}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getBet(@PathVariable("UUID") String uuid){
        return betService.getBet(uuid);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createBet(@RequestBody BetRequest betRequest){
        return betService.createBet(betRequest);
    }

}
