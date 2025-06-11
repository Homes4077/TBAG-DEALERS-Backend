package com.tbagdealers.service; // Adjust package to your service's location

import com.tbagdealers.model.Vehicle; // Adjust package to your Vehicle entity location
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface VehicleService {

    // Method to create a new vehicle, handling file uploads
    Vehicle createVehicle(String make, String model, int year, double price, String description,
                          MultipartFile aerialViewFile, MultipartFile rearViewFile,
                          MultipartFile interiorViewFile, MultipartFile engineViewFile,
                          MultipartFile trunkViewFile, MultipartFile platesViewFile) throws IOException;

    // Method to get all vehicles
    List<Vehicle> getAllVehicles();

    // Method to get a specific vehicle by ID
    Optional<Vehicle> getVehicleById(Long id);

    // Method to update an existing vehicle
    Vehicle updateVehicle(Long id, Vehicle updatedVehicle);

    // Method to delete a vehicle by ID
    void deleteVehicle(Long id);
}
