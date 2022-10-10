package com.lukodev.evorapaint.dataAccess.abstracts;

import com.lukodev.evorapaint.entities.concretes.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ShoppingCartDao extends JpaRepository<ShoppingCart, Integer> {

    ShoppingCart getByCustomerId(int customerId);

    boolean existsShoppingCartByCustomerId(int customerId);

    @Modifying
    @Transactional
    void deleteByCustomerId(int customerId);
}
