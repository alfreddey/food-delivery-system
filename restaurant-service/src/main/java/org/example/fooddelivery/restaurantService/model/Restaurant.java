package org.example.fooddelivery.restaurantService.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "restaurants")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String cuisineType;
    private String address;
    private String city;
    private String phone;

    private boolean active;

    @Column(nullable = false)
    private double rating;

    private int estimatedDeliveryMinutes;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (rating == 0) rating = 0.0;
        active = true;
    }
}

