package com.lukodev.evorapaint.dataAccess.abstracts;

import com.lukodev.evorapaint.entities.concretes.ShipmentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipmentMethodDao extends JpaRepository<ShipmentMethod, Integer> {

    List<ShipmentMethod> getAllByShipmentTypeId(int shipmentTypeId);

}
