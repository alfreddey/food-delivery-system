package org.example.fooddelivery.orderService.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

/**
 * OrderItem entity — part of the Order domain.
 *
 * MONOLITH PROBLEM: Direct @ManyToOne to MenuItem entity
 * (Restaurant domain). In microservices, store menuItemId,
 * itemName, and unitPrice as snapshot values so the Order
 * Service doesn't depend on Restaurant Service at read time.
 */
@Entity
@Table(name = "order_items")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private BigDecimal subtotal;

    private String specialInstructions;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "menu_item_id", nullable = false)
    private Long menuItemId;
}
