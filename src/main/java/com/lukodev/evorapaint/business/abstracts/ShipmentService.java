package com.lukodev.evorapaint.business.abstracts;

import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.entities.concretes.Shipment;

import java.util.List;

public interface ShipmentService {

    Result add(Shipment shipment);
    Result update(Shipment shipment);
    Result delete(Shipment shipment);

    DataResult<List<Shipment>> getAll();

    DataResult<Shipment> getById(int id);
    DataResult<Shipment> getByOrderId(int orderId);

}
