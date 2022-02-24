package com.lukodev.evorapaint.dataAccess.abstracts;

import com.lukodev.evorapaint.entities.concretes.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDao extends JpaRepository<Order, Integer> {

    List<Order> getAllByCustomerId(int customerId);
    List<Order> getAllByOrderStatusId(int orderStatusId);
}
