package org.example.fooddelivery.orderService.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MenuItemResponse {
    private Long id;
    private boolean isAvailable;
    private String name;
    private Long restaurantId;
    private BigDecimal price;
}
