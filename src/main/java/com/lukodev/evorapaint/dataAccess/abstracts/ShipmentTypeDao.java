package com.lukodev.evorapaint.dataAccess.abstracts;

import com.lukodev.evorapaint.entities.concretes.ShipmentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentTypeDao extends JpaRepository<ShipmentType, Integer> {
}
