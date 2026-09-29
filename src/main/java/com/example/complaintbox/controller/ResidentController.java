
package com.example.complaintbox.controller;

import com.example.complaintbox.entity.Resident;
import com.example.complaintbox.repository.ResidentRepository;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/residents")
public class ResidentController {

    private final ResidentRepository residentRepository;

    public ResidentController(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    // TEST API
    @GetMapping("/test")
    public String test() {
        return "Resident API is working!";
    }

    // GET ALL RESIDENTS
    @GetMapping
    public List<Resident> getAllResidents() {
        return residentRepository.findAll();
    }

    // GET RESIDENT BY ID
    @GetMapping("/{id}")
    public Resident getResidentById(@PathVariable Long id) {
        return residentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resident not found"));
    }

    // CREATE RESIDENT
    @PostMapping
    public Resident createResident(@RequestBody Resident resident) {
        return residentRepository.save(resident);
    }

    // UPDATE RESIDENT
    @PutMapping("/{id}")
    public Resident updateResident(
            @PathVariable Long id,
            @RequestBody Resident updatedResident) {

        Resident resident = residentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resident not found"));

        resident.setName(updatedResident.getName());
        resident.setEmail(updatedResident.getEmail());
        resident.setRoomNumber(updatedResident.getRoomNumber());

        return residentRepository.save(resident);
    }

    // DELETE RESIDENT
    @DeleteMapping("/{id}")
    public String deleteResident(@PathVariable Long id) {
        Resident resident = residentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resident not found"));

        residentRepository.delete(resident);

        return "Resident deleted successfully!";
    }
}