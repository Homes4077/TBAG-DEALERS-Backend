package com.tbagdealers.service; // Adjust package to your service's location

import com.tbagdealers.model.Vehicle; // Adjust package to your Vehicle entity location
import com.tbagdealers.repository.VehicleRepository; // Adjust package to your VehicleRepository location

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // For transaction management
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service // Marks this class as a Spring Service component
public class VehicleServiceImpl implements VehicleService {

    @Autowired // Injects the VehicleRepository
    private VehicleRepository vehicleRepository;

    // Define the base upload directory. This MUST match your WebConfig and application.properties.
    private final String UPLOAD_DIR = "./uploads/"; // On Railway, this resolves to /app/uploads/


    // Helper method to save a file and return its URL (moved from Controller to Service)
    private String saveFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null; // No file provided
        }

        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String originalFilename = file.getOriginalFilename();
        String fileExtension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

        Path filePath = uploadPath.resolve(uniqueFileName);
        Files.copy(file.getInputStream(), filePath);

        return "/uploads/" + uniqueFileName;
    }

    // Helper method to delete a file given its URL path (optional, for cleanup)
    private void deleteFile(String imageUrl) throws IOException {
        if (imageUrl != null && !imageUrl.isEmpty() && imageUrl.startsWith("/uploads/")) {
            String fileName = Paths.get(imageUrl).getFileName().toString();
            Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName);
            Files.deleteIfExists(filePath);
        }
    }


    // ====================================================================================
    // Service Methods Implementation
    // ====================================================================================

    @Override
    @Transactional // Ensures the entire method runs within a single database transaction
    public Vehicle createVehicle(String make, String model, int year, double price, String description,
                                 MultipartFile aerialViewFile, MultipartFile rearViewFile,
                                 MultipartFile interiorViewFile, MultipartFile engineViewFile,
                                 MultipartFile trunkViewFile, MultipartFile platesViewFile) throws IOException {

        Vehicle vehicle = new Vehicle();
        vehicle.setMake(make);
        vehicle.setModel(model);
        vehicle.setYear(year);
        vehicle.setPrice(price);
        vehicle.setDescription(description);

        // Save each file and set the corresponding URL in the vehicle object
        vehicle.setAerialViewUrl(saveFile(aerialViewFile));
        vehicle.setRearViewUrl(saveFile(rearViewFile));
        vehicle.setInteriorViewUrl(saveFile(interiorViewFile));
        vehicle.setEngineViewUrl(saveFile(engineViewFile));
        vehicle.setTrunkViewUrl(saveFile(trunkViewFile));
        vehicle.setPlatesViewUrl(saveFile(platesViewFile));

        return vehicleRepository.save(vehicle);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    @Override
    @Transactional
    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {
        Optional<Vehicle> existingVehicleOptional = vehicleRepository.findById(id);

        if (existingVehicleOptional.isPresent()) {
            Vehicle existingVehicle = existingVehicleOptional.get();

            // Update fields
            existingVehicle.setMake(updatedVehicle.getMake());
            existingVehicle.setModel(updatedVehicle.getModel());
            existingVehicle.setYear(updatedVehicle.getYear());
            existingVehicle.setPrice(updatedVehicle.getPrice());
            existingVehicle.setDescription(updatedVehicle.getDescription());
            existingVehicle.setAerialViewUrl(updatedVehicle.getAerialViewUrl());
            existingVehicle.setRearViewUrl(updatedVehicle.getRearViewUrl());
            existingVehicle.setInteriorViewUrl(updatedVehicle.getInteriorViewUrl());
            existingVehicle.setEngineViewUrl(updatedVehicle.getEngineViewUrl());
            existingVehicle.setTrunkViewUrl(updatedVehicle.getTrunkViewUrl());
            existingVehicle.setPlatesViewUrl(updatedVehicle.getPlatesViewUrl());

            return vehicleRepository.save(existingVehicle);
        } else {
            // In a service layer, you might throw a custom exception like VehicleNotFoundException
            // and let a global exception handler in the controller layer convert it to 404.
            // For now, we'll re-throw a RuntimeException which the controller can catch.
            throw new RuntimeException("Vehicle not found with ID: " + id);
        }
    }

    @Override
    @Transactional
    public void deleteVehicle(Long id) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        if (vehicleOptional.isPresent()) {
            // Optional: Delete associated image files from disk for cleanup
            // (Remember these files are ephemeral on Railway without external storage)
            // Vehicle vehicle = vehicleOptional.get();
            // try {
            //     deleteFile(vehicle.getAerialViewUrl());
            //     deleteFile(vehicle.getRearViewUrl());
            //     deleteFile(vehicle.getInteriorViewUrl());
            //     deleteFile(vehicle.getEngineViewUrl());
            //     deleteFile(vehicle.getTrunkViewUrl());
            //     deleteFile(vehicle.getPlatesViewUrl());
            // } catch (IOException e) {
            //     // Log error but don't prevent DB deletion if file deletion fails
            //     e.printStackTrace();
            // }

            vehicleRepository.deleteById(id);
        } else {
            throw new RuntimeException("Vehicle not found with ID: " + id);
        }
    }
}
