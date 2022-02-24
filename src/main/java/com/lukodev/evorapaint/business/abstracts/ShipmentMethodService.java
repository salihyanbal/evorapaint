package com.lukodev.evorapaint.business.abstracts;

import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.entities.concretes.ShipmentMethod;

import java.util.List;

public interface ShipmentMethodService {

    Result add(ShipmentMethod shipmentMethod);
    Result update(ShipmentMethod shipmentMethod);
    Result delete(ShipmentMethod shipmentMethod);

    DataResult<List<ShipmentMethod>> getAll();
    DataResult<List<ShipmentMethod>> getAllByShipmentTypeId(int shipmentTypeId);

    DataResult<ShipmentMethod> getById(int id);

}
