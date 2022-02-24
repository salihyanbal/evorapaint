package com.lukodev.evorapaint.dataAccess.abstracts;

import com.lukodev.evorapaint.entities.concretes.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentDao extends JpaRepository<Shipment, Integer> {

    Shipment getByOrderId(int orderId);

}
