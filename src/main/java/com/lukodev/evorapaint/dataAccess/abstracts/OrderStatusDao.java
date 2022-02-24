package com.lukodev.evorapaint.dataAccess.abstracts;

import com.lukodev.evorapaint.entities.concretes.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusDao extends JpaRepository<OrderStatus, Integer> {
}
