package com.lukodev.evorapaint.dataAccess.abstracts;

import com.lukodev.evorapaint.entities.concretes.OrderProduct;
import com.lukodev.evorapaint.entities.dtos.ShoppingCartItemWithImageDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderProductDao extends JpaRepository<OrderProduct, Integer> {

    List<OrderProduct> getAllByOrderId(int orderId);
}
