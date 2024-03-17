package com.addsonweslley.loteriasapi.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany
    @JoinColumn(name = "bet_id")
    @JsonManagedReference
    private List<Ticket> ticketList;

    @Column(columnDefinition = "TEXT")
    private String raffleNumbersJson;

    @Column(nullable = false)
    private int toltalNumbers;

    @Column(nullable = false)
    private int maxNumbersByUsers;

    public List<Integer> getRaffleNumbers() throws JsonProcessingException {
        return new ObjectMapper().readValue(raffleNumbersJson, new TypeReference<List<Integer>>() {});
    }

    public void setRaffleNumbers(List<Integer> raffleNumbers) {
        try {
            this.raffleNumbersJson = new ObjectMapper().writeValueAsString(raffleNumbers);
        } catch (JsonProcessingException e) {
            // Lidar com a exceção de serialização, se necessário
            e.printStackTrace();
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public String getRaffleNumbersJson() {
        return raffleNumbersJson;
    }

    public void setRaffleNumbersJson(String raffleNumbersJson) {
        this.raffleNumbersJson = raffleNumbersJson;
    }

    public int getToltalNumbers() {
        return toltalNumbers;
    }

    public void setToltalNumbers(int toltalNumbers) {
        this.toltalNumbers = toltalNumbers;
    }

    public int getMaxNumbersByUsers() {
        return maxNumbersByUsers;
    }

    public void setMaxNumbersByUsers(int maxNumbersByUsers) {
        this.maxNumbersByUsers = maxNumbersByUsers;
    }

}
