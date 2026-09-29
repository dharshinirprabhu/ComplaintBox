package com.example.complaintbox.controller;

import com.example.complaintbox.entity.Staff;
import com.example.complaintbox.repository.StaffRepository;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private final StaffRepository staffRepository;

    public StaffController(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    // TEST API
    @GetMapping("/test")
    public String test() {
        return "Staff API is working!";
    }

    // GET ALL STAFF
    @GetMapping
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    // GET STAFF BY ID
    @GetMapping("/{id}")
    public Staff getStaffById(@PathVariable Long id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found"));
    }

    // CREATE STAFF
    @PostMapping
    public Staff createStaff(@RequestBody Staff staff) {
        return staffRepository.save(staff);
    }

    // UPDATE STAFF
    @PutMapping("/{id}")
    public Staff updateStaff(
            @PathVariable Long id,
            @RequestBody Staff updatedStaff) {

        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        staff.setName(updatedStaff.getName());
        staff.setEmail(updatedStaff.getEmail());
        staff.setRole(updatedStaff.getRole());
        staff.setPhoneNumber(updatedStaff.getPhoneNumber());

        return staffRepository.save(staff);
    }

    // DELETE STAFF
    @DeleteMapping("/{id}")
    public String deleteStaff(@PathVariable Long id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        staffRepository.delete(staff);

        return "Staff deleted successfully!";
    }
}