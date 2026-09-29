
package com.example.complaintbox.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/warden")
public class WardenController {

    @GetMapping("/test")
    public String test() {
        return "Warden API is working!";
    }
}