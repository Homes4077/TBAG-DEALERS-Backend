package com.tbagdealers.controller; // Adjust package

import com.tbagdealers.model.Vehicle; // Adjust package
import com.tbagdealers.service.VehicleService; // IMPORT THE NEW SERVICE
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired // Inject the VehicleService interface
    private VehicleService vehicleService;

    // The UPLOAD_DIR and saveFile helper methods are now in VehicleServiceImpl

    // ====================================================================================
    // POST /vehicles - Create a new vehicle with multiple image uploads
    // ====================================================================================
    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(
            @RequestParam("make") String make,
            @RequestParam("model") String model,
            @RequestParam("year") int year,
            @RequestParam("price") double price,
            @RequestParam("description") String description,
            @RequestParam(value = "aerialView", required = false) MultipartFile aerialViewFile,
            @RequestParam(value = "rearView", required = false) MultipartFile rearViewFile,
            @RequestParam(value = "interiorView", required = false) MultipartFile interiorViewFile,
            @RequestParam(value = "engineView", required = false) MultipartFile engineViewFile,
            @RequestParam(value = "trunkView", required = false) MultipartFile trunkViewFile,
            @RequestParam(value = "platesView", required = false) MultipartFile platesViewFile) {

        try {
            Vehicle savedVehicle = vehicleService.createVehicle(
                make, model, year, price, description,
                aerialViewFile, rearViewFile, interiorViewFile,
                engineViewFile, trunkViewFile, platesViewFile
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(savedVehicle);
        } catch (IOException e) {
            // Log the exception (in a real app, use a logger)
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create vehicle: " + e.getMessage(), e);
        }
    }

    // ====================================================================================
    // GET /vehicles - Get all vehicle listings
    // ====================================================================================
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    // ====================================================================================
    // GET /vehicles/{id} - Get a specific vehicle by ID
    // ====================================================================================
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id)
                .map(ResponseEntity::ok) // If found, return 200 OK
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found with ID: " + id)); // Else 404 Not Found
    }

    // ====================================================================================
    // DELETE /vehicles/{id} - Delete a vehicle by ID
    // ====================================================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        try {
            vehicleService.deleteVehicle(id);
            return ResponseEntity.noContent().build(); // 204 No Content for successful deletion
        } catch (RuntimeException e) { // Catch the RuntimeException from service layer
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage()); // Convert to 404
        }
    }

    // ====================================================================================
    // PUT /vehicles/{id} - Update an existing vehicle
    // ====================================================================================
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id,
                                               @RequestBody Vehicle updatedVehicle) {
        try {
            Vehicle savedVehicle = vehicleService.updateVehicle(id, updatedVehicle);
            return ResponseEntity.ok(savedVehicle);
        } catch (RuntimeException e) { // Catch the RuntimeException from service layer
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage()); // Convert to 404
        }
    }
}
