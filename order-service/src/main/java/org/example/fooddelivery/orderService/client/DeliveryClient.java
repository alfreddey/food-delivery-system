package org.example.fooddelivery.orderService.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "delivery-service", url = "${delivery.service.url}")
public interface DeliveryClient {
    DeliveryResponse createDelivery(DeliveryRequest request);
}
