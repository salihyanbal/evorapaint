package com.lukodev.evorapaint.dataAccess.abstracts;

import com.lukodev.evorapaint.entities.concretes.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductDao extends JpaRepository<OrderProduct, Integer> {

    List<OrderProduct> getAllByOrderId(int orderId);

}
