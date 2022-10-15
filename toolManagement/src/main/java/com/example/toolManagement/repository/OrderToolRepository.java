package com.example.toolManagement.repository;

import com.example.toolManagement.entities.OrderTool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderToolRepository extends JpaRepository<OrderTool, Integer> {
    @Query("SELECT o.toolQuantity FROM OrderTool o WHERE o.orderId = ?1 and o.toolId = ?2")
    int findToolquantity(Long orderId, Long toolId);
}
