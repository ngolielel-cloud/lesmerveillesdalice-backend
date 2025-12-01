package com.moncentre.lesmerveillesdalice.controller;

import com.moncentre.lesmerveillesdalice.dto.PersonnelDTO;
import com.moncentre.lesmerveillesdalice.entity.Personnel;
import com.moncentre.lesmerveillesdalice.service.PersonnelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personnel")
@CrossOrigin(origins = "*")
public class PersonnelController {

    private final PersonnelService service;

    public PersonnelController(PersonnelService service) {
        this.service = service;
    }

    @GetMapping
    public List<PersonnelDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PersonnelDTO getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PostMapping("/create/{idService}")
    public PersonnelDTO create(@PathVariable Integer idService, @RequestBody Personnel p) {
        return service.create(p, idService);
    }

    @PutMapping("/{id}")
    public PersonnelDTO update(@PathVariable Integer id, @RequestBody Personnel p) {
        return service.update(id, p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}