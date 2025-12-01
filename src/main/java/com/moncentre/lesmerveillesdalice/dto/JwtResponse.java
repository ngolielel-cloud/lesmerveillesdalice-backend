package com.moncentre.lesmerveillesdalice.dto;

public class JwtResponse {

    private String token;
    private UtilisateurDTO user;

    public JwtResponse(String token, UtilisateurDTO user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() { return token; }
    public UtilisateurDTO getUser() { return user; }
}