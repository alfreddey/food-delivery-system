package org.example.fooddelivery.orderService.client;

import org.example.fooddelivery.orderService.dto.MenuItemResponse;
import org.example.fooddelivery.orderService.dto.RestaurantResponse;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "restaurant-service", url = "${restaurant.service.url}")
public interface RestaurantClient {
    RestaurantResponse getById(Long id);
    MenuItemResponse getMenuItemById(Long menuItemId);
}
