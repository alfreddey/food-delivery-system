package org.example.fooddelivery.orderService.dto;

import lombok.Data;

import java.util.List;

@Data
public class PlaceOrderRequest {
    private Long restaurantId;
    private String deliveryAddress;
    private String specialInstructions;
    private List<OrderItemRequest> itemRequests;
}
