package com.addsonweslley.loteriasapi.dtos.Bet;

import com.addsonweslley.loteriasapi.models.Ticket;

import java.util.List;
import java.util.UUID;

public record BetRequest(int toltalNumbers, int maxNumbersByUsers){}
