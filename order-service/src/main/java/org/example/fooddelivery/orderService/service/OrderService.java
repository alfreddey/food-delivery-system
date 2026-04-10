package org.example.fooddelivery.orderService.service;

import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.orderService.client.CustomerClient;
import org.example.fooddelivery.orderService.client.RestaurantClient;
import org.example.fooddelivery.orderService.dto.*;
import org.example.fooddelivery.orderService.model.Order;
import org.example.fooddelivery.orderService.model.OrderItem;
import org.example.fooddelivery.orderService.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.ServiceUnavailableException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final RestaurantClient restaurantClient;

    @Transactional
    public OrderResponse placeOrder(String customerUsername, PlaceOrderRequest request) throws ServiceUnavailableException {
        CustomerResponse customerResponse = null;
        RestaurantResponse restaurantResponse = null;

        try {
            customerResponse = customerClient.getByUsername(customerUsername);
            restaurantResponse = restaurantClient.getById(request.getRestaurantId());
        } catch (Exception e) {
            throw new ServiceUnavailableException(e.getMessage() + ". Thrown by " + e.getStackTrace().getClass());
        }

        if (!restaurantResponse.isActive()) {
            throw new IllegalStateException("Restaurant is currently not accepting orders");
        }

        // Build order
        Order order = Order.builder()
                .customerId(customerResponse.getId())
                .restaurantId(restaurantResponse.getId())
                .deliveryAddress(request.getDeliveryAddress() != null
                        ? request.getDeliveryAddress()
                        : customerResponse.getDeliveryAddress())
                .specialInstructions(request.getSpecialInstructions())
                .estimatedDeliveryTime(
                        LocalDateTime.now().plusMinutes(restaurantResponse.getEstimatedDeliveryMinutes()))
                .build();

        BigDecimal total = BigDecimal.ZERO;
        for (OrderItemRequest itemReq : request.getItemRequests()) {
            MenuItemResponse menuItemResponse = restaurantClient.getMenuItemById(itemReq.getMenuItemId());

            if (!menuItemResponse.isAvailable()) {
                throw new IllegalStateException("Menu item '" + menuItemResponse.getName() + "' is not available");
            }
            if (!menuItemResponse.getRestaurantId().equals(restaurantResponse.getId())) {
                throw new IllegalStateException("Menu item '" + menuItemResponse.getName()
                        + "' does not belong to restaurant '" + restaurantResponse.getName() + "'");
            }

            BigDecimal subtotal = menuItemResponse.getPrice().multiply(BigDecimal.valueOf(itemReq.getQuantity()));

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .menuItemId(menuItemResponse.getId())
                    .quantity(itemReq.getQuantity())
                    .unitPrice(menuItemResponse.getPrice())
                    .subtotal(subtotal)
                    .specialInstructions(itemReq.getSpecialInstructions())
                    .build();

            order.getItems().add(orderItem);
            total = total.add(subtotal);
        }

        order.setTotalAmount(total);
        Order savedOrder = orderRepository.save(order);

        return OrderResponse.fromEntity(savedOrder);
    }
}

