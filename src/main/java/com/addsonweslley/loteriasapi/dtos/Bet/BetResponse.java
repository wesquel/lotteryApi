package com.addsonweslley.loteriasapi.dtos.Bet;

import com.addsonweslley.loteriasapi.models.Ticket;

import java.util.List;
import java.util.UUID;

public record BetResponse(UUID id, int maxNumbersByUser, int toltalNumbers) {}
