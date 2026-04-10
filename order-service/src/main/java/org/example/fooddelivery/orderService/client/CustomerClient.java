package org.example.fooddelivery.orderService.client;

import org.example.fooddelivery.orderService.dto.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "customer-service", url = "${customer.service.url}")
public interface CustomerClient {
    CustomerResponse getByUsername(String username);
}
