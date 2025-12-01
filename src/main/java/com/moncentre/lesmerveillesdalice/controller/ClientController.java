package com.moncentre.lesmerveillesdalice.controller;

import com.moncentre.lesmerveillesdalice.dto.ClientDTO;
import com.moncentre.lesmerveillesdalice.dto.ClientStatsDTO;
import com.moncentre.lesmerveillesdalice.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "*")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    // ✔ Tous les clients
    @GetMapping("/all")
    public List<ClientDTO> getAll() {
        return service.getAll();
    }

    // ✔ Un client par ID
    @GetMapping("/{id}")
    public ClientDTO getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    // ✔ Créer un client
    @PostMapping("/add")
    public ClientDTO create(@RequestBody ClientDTO dto) {
        return service.create(dto);
    }

    // ✔ Modifier un client
    @PutMapping("/update/{id}")
    public ClientDTO update(@PathVariable Integer id, @RequestBody ClientDTO dto) {
        return service.update(id, dto);
    }

    // ✔ Supprimer un client
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    // ✔ Clients par service
    @GetMapping("/service/{name}")
    public List<ClientDTO> getByService(@PathVariable String name) {
        return service.getByService(name);
    }

    // ✔ STATISTIQUES (INDICATEURS)
    @GetMapping("/stats/services")
    public ClientStatsDTO getStats() {
        return service.stats();
    }
}