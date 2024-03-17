package com.addsonweslley.loteriasapi.dtos.Bet;

import java.util.UUID;

public record BetResponse(UUID id, int maxNumbersByUser, int toltalNumbers) {}
