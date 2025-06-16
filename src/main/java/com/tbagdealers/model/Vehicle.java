package com.tbagdealers.model; // Adjust package to your model's location

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "vehicles")
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "make", nullable = false)
    private String make;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "year")
    private int year;

    @Column(name = "price")
    private double price;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    // --- NEW IMAGE FIELDS ---
    @Column(name = "aerial_view_url")
    private String aerialViewUrl;

    @Column(name = "rear_view_url")
    private String rearViewUrl;

    @Column(name = "interior_view_url")
    private String interiorViewUrl;

    @Column(name = "engine_view_url")
    private String engineViewUrl;

    @Column(name = "trunk_view_url")
    private String trunkViewUrl;

    @Column(name = "plates_view_url")
    private String platesViewUrl;
    // -------------------------

    public Vehicle() {}

    // Constructor with all fields (optional, but adjust if you use it)
    public Vehicle(String make, String model, int year, double price, String description,
                   String aerialViewUrl, String rearViewUrl, String interiorViewUrl,
                   String engineViewUrl, String trunkViewUrl, String platesViewUrl) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.description = description;
        this.aerialViewUrl = aerialViewUrl;
        this.rearViewUrl = rearViewUrl;
        this.interiorViewUrl = interiorViewUrl;
        this.engineViewUrl = engineViewUrl;
        this.trunkViewUrl = trunkViewUrl;
        this.platesViewUrl = platesViewUrl;
    }

    // --- GETTERS AND SETTERS ---
    // (Existing getters/setters for id, make, model, year, price, description remain)

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }


    // NEW GETTERS AND SETTERS FOR IMAGE URLs
    public String getAerialViewUrl() {
        return aerialViewUrl;
    }

    public void setAerialViewUrl(String aerialViewUrl) {
        this.aerialViewUrl = aerialViewUrl;
    }

    public String getRearViewUrl() {
        return rearViewUrl;
    }

    public void setRearViewUrl(String rearViewUrl) {
        this.rearViewUrl = rearViewUrl;
    }

    public String getInteriorViewUrl() {
        return interiorViewUrl;
    }

    public void setInteriorViewUrl(String interiorViewUrl) {
        this.interiorViewUrl = interiorViewUrl;
    }

    public String getEngineViewUrl() {
        return engineViewUrl;
    }

    public void setEngineViewUrl(String engineViewUrl) {
        this.engineViewUrl = engineViewUrl;
    }

    public String getTrunkViewUrl() {
        return trunkViewUrl;
    }

    public void setTrunkViewUrl(String trunkViewUrl) {
        this.trunkViewUrl = trunkViewUrl;
    }

    public String getPlatesViewUrl() {
        return platesViewUrl;
    }

    public void setPlatesViewUrl(String platesViewUrl) {
        this.platesViewUrl = platesViewUrl;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
               "id=" + id +
               ", make='" + make + '\'' +
               ", model='" + model + '\'' +
               ", year=" + year +
               ", price=" + price +
               ", description='" + description + '\'' +
               ", aerialViewUrl='" + aerialViewUrl + '\'' +
               ", rearViewUrl='" + rearViewUrl + '\'' +
               ", interiorViewUrl='" + interiorViewUrl + '\'' +
               ", engineViewUrl='" + engineViewUrl + '\'' +
               ", trunkViewUrl='" + trunkViewUrl + '\'' +
               ", platesViewUrl='" + platesViewUrl + '\'' +
               '}';
    }
}
