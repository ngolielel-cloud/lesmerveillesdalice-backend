package com.moncentre.lesmerveillesdalice.controller;

import com.moncentre.lesmerveillesdalice.dto.ClientRdvRequest;
import com.moncentre.lesmerveillesdalice.dto.RendezVousDTO;
import com.moncentre.lesmerveillesdalice.service.ClientRdvService;
import com.moncentre.lesmerveillesdalice.service.RendezVousService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client-rdv")
@CrossOrigin(origins="http://localhost:4200")
public class ClientRdvController {

    private final ClientRdvService service;
    private final RendezVousService rdvService;

    public ClientRdvController(ClientRdvService service, RendezVousService rdvService) {
        this.service = service;
        this.rdvService = rdvService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createClientAndRdv(@RequestBody ClientRdvRequest req) {
        try {
            RendezVousDTO dto = service.createFromClient(req);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/ping")
    public String ping() {
        return "Client RDV API OK ✔";
    }

    // ⭐ MES RENDEZ-VOUS PAR CODE
    @GetMapping("/mes-rdv/{code}")
    public List<RendezVousDTO> getMesRdv(@PathVariable String code) {
        return rdvService.getRdvByCode(code);
    }
}
