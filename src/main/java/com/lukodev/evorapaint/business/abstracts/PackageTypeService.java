package com.lukodev.evorapaint.business.abstracts;

import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.entities.concretes.PackageType;

import java.util.List;

public interface PackageTypeService {

    Result add(PackageType packageType);
    Result update(PackageType packageType);
    Result delete(PackageType packageType);

    DataResult<List<PackageType>> getAll();
    DataResult<List<PackageType>> getAllByShipmentTypeId(int shipmentTypeId);
    DataResult<PackageType> getById(int id);

}
