package org.example.fooddelivery.orderService.dto;

import lombok.Data;
import org.example.fooddelivery.orderService.model.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {
    private Long id;
    private String status;
    private BigDecimal totalAmount;
    private BigDecimal deliveryFee;
    private String deliveryAddress;
    private String specialInstructions;
    private LocalDateTime createdAt;
    private LocalDateTime estimatedDeliveryTime;
    private List<OrderItemDetail> items;

    private Long customerId;
    private Long restaurantId;

    private Long deliveryId;

    @Data
    public static class OrderItemDetail {
        private Long id;
        private Long menuItemId;
        private int quantity;
        private BigDecimal unitPrice;
        private BigDecimal subtotal;
    }

    public static OrderResponse fromEntity(Order o) {
        OrderResponse dto = new OrderResponse();
        dto.setId(o.getId());
        dto.setStatus(o.getStatus().name());
        dto.setTotalAmount(o.getTotalAmount());
        dto.setDeliveryFee(o.getDeliveryFee());
        dto.setDeliveryAddress(o.getDeliveryAddress());
        dto.setSpecialInstructions(o.getSpecialInstructions());
        dto.setCreatedAt(o.getCreatedAt());
        dto.setEstimatedDeliveryTime(o.getEstimatedDeliveryTime());

        dto.setCustomerId(o.getCustomerId());
        dto.setRestaurantId(o.getRestaurantId());

        if (o.getDeliveryId() != null) {
            dto.setDeliveryId(o.getDeliveryId());
        }

        dto.setItems(o.getItems().stream().map(item -> {
            OrderItemDetail detail = new OrderItemDetail();
            detail.setId(item.getId());
            detail.setMenuItemId(item.getMenuItemId());
            detail.setQuantity(item.getQuantity());
            detail.setUnitPrice(item.getUnitPrice());
            detail.setSubtotal(item.getSubtotal());
            return detail;
        }).toList());

        return dto;
    }
}
