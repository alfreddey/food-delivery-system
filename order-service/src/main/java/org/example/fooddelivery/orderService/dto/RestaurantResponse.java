package org.example.fooddelivery.orderService.dto;

import lombok.Data;

@Data
public class RestaurantResponse {
    private Long id;
    private boolean isActive;
    private Long estimatedDeliveryMinutes;
    private String name;
}
