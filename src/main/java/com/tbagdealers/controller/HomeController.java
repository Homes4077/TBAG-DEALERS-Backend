package com.tbagdealers.controller; // Ensure this matches your package structure

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/") // This maps to the root URL (e.g., https://tbag-dealers-backend.onrender.com/)
    public String home() {
        return "Welcome to the TBAG Dealers Backend API! Access vehicle endpoints at /vehicles";
    }

    // You can add other general-purpose endpoints here if needed
    @GetMapping("/status")
    public String status() {
        return "Application is running.";
    }
}
