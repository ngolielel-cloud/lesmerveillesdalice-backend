package com.moncentre.lesmerveillesdalice.dto;

public class ClientStatsDTO {

    private long kine;
    private long massage;
    private long spa;
    private long sport;

    public ClientStatsDTO(long kine, long massage, long spa, long sport) {
        this.kine = kine;
        this.massage = massage;
        this.spa = spa;
        this.sport = sport;
    }

    // Getters
    public long getKine() { return kine; }
    public long getMassage() { return massage; }
    public long getSpa() { return spa; }
    public long getSport() { return sport; }
}