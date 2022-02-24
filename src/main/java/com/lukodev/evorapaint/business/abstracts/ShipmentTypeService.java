package com.lukodev.evorapaint.business.abstracts;

import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.entities.concretes.ShipmentType;

import java.util.List;

public interface ShipmentTypeService {

    Result add(ShipmentType shipmentType);
    Result update(ShipmentType shipmentType);
    Result delete(ShipmentType shipmentType);

    DataResult<List<ShipmentType>> getAll();
    DataResult<ShipmentType> getById(int id);

}
