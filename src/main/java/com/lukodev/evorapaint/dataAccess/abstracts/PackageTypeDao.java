package com.lukodev.evorapaint.dataAccess.abstracts;

import com.lukodev.evorapaint.entities.concretes.PackageType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageTypeDao extends JpaRepository<PackageType, Integer> {

    List<PackageType> getAllByShipmentTypeId(int shipmentTypeId);

}
