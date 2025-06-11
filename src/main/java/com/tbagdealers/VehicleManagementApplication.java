package com.tbagdealers; // Ensure this package matches your project's root package

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // This annotation enables auto-configuration, component scanning, etc.
public class VehicleManagementApplication {

    public static void main(String[] args) {
        // This method bootstraps and runs your Spring Boot application.
        SpringApplication.run(VehicleManagementApplication.class, args);
        System.out.println("Vehicle Management Application started successfully!");
    }
}
