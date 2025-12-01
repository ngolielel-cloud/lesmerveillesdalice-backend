package com.moncentre.lesmerveillesdalice.controller;

import com.moncentre.lesmerveillesdalice.dto.ServiceDTO;
import com.moncentre.lesmerveillesdalice.entity.ServiceCentre;
import com.moncentre.lesmerveillesdalice.service.ServiceCentreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "*")
public class ServiceCentreController {

    private final ServiceCentreService service;

    public ServiceCentreController(ServiceCentreService service) {
        this.service = service;
    }

    @GetMapping
    public List<ServiceDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ServiceDTO getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PostMapping
    public ServiceDTO create(@RequestBody ServiceCentre s) {
        return service.create(s);
    }

    @PutMapping("/{id}")
    public ServiceDTO update(@PathVariable Integer id, @RequestBody ServiceCentre s) {
        return service.update(id, s);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}