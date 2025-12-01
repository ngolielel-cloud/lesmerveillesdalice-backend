package com.moncentre.lesmerveillesdalice.controller;

import com.moncentre.lesmerveillesdalice.dto.PrestationDTO;
import com.moncentre.lesmerveillesdalice.entity.Prestation;
import com.moncentre.lesmerveillesdalice.service.PrestationService;

import org.springframework.web.bind.annotation.*;

import java.util.Map;       // ⭐⭐ AJOUT OBLIGATOIRE
import java.util.HashMap;  // ⭐⭐ AJOUT OBLIGATOIRE
import java.util.List;

@RestController
@RequestMapping("/api/prestations")
@CrossOrigin("*")
public class PrestationController {

    private final PrestationService service;

    public PrestationController(PrestationService service) {
        this.service = service;
    }

    // ⭐ CREATE
    @PostMapping
    public PrestationDTO create(@RequestBody Map<String, Object> data) {

        Prestation p = new Prestation();
        p.setType((String) data.get("type"));
        p.setHistorique((String) data.get("historique"));

        Integer idClient = (Integer) data.get("idClient");
        Integer idPersonnel = (Integer) data.get("idPersonnel");
        Integer idService = (Integer) data.get("idService");

        return service.create(p, idClient, idPersonnel, idService);
    }

    // ⭐ GET ALL
    @GetMapping
    public List<PrestationDTO> getAll() {
        return service.getAll();
    }

    // ⭐ GET BY CLIENT
    @GetMapping("/client/{idClient}")
    public List<PrestationDTO> getByClient(@PathVariable Integer idClient) {
        return service.getByClient(idClient);
    }

    // ⭐ GET BY PERSONNEL
    @GetMapping("/personnel/{idPersonnel}")
    public List<PrestationDTO> getByPersonnel(@PathVariable Integer idPersonnel) {
        return service.getByPersonnel(idPersonnel);
    }

    // ⭐ UPDATE
    @PutMapping("/{id}")
    public PrestationDTO update(@PathVariable Integer id, @RequestBody Prestation data) {
        return service.update(id, data);
    }

    // ⭐ DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    // ⭐ TERMINER
    @PutMapping("/terminer/{id}")
    public PrestationDTO terminer(@PathVariable Integer id) {
        return service.terminer(id);
    }
}