package com.tbagdealers.repository; // Adjust package to your repository's location

import com.tbagdealers.model.Vehicle; // Adjust package to your Vehicle entity location
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; // Optional, but good practice

@Repository // Marks this interface as a Spring Data repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    // JpaRepository provides out-of-the-box CRUD operations for Vehicle entity with Long ID.
    // No methods are needed here for basic CRUD.
}
