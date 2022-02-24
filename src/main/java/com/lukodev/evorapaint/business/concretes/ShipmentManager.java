package com.lukodev.evorapaint.business.concretes;

import com.lukodev.evorapaint.business.abstracts.ShipmentService;
import com.lukodev.evorapaint.business.constants.Messages;
import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.core.utilities.results.SuccessDataResult;
import com.lukodev.evorapaint.core.utilities.results.SuccessResult;
import com.lukodev.evorapaint.dataAccess.abstracts.ShipmentDao;
import com.lukodev.evorapaint.entities.concretes.Shipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentManager implements ShipmentService {

    private ShipmentDao shipmentDao;

    @Autowired
    public ShipmentManager(ShipmentDao shipmentDao) {
        this.shipmentDao = shipmentDao;
    }

    @Override
    @CacheEvict(value = "shipment.getAll", allEntries = true)
    public Result add(Shipment shipment) {
        this.shipmentDao.save(shipment);
        return new SuccessResult(Messages.SHIPMENT_ADDED);
    }

    @Override
    @CacheEvict(value = "shipment.getAll", allEntries = true)
    public Result update(Shipment shipment) {
        this.shipmentDao.save(shipment);
        return new SuccessResult(Messages.SHIPMENT_UPDATED);
    }

    @Override
    @CacheEvict(value = "shipment.getAll", allEntries = true)
    public Result delete(Shipment shipment) {
        this.shipmentDao.delete(shipment);
        return new SuccessResult(Messages.SHIPMENT_DELETED);
    }

    @Override
    @Cacheable(value = "shipment.getAll")
    public DataResult<List<Shipment>> getAll() {
        return new SuccessDataResult<>(this.shipmentDao.findAll());
    }

    @Override
    public DataResult<Shipment> getById(int id) {
        return new SuccessDataResult<>(this.shipmentDao.findById(id).get());
    }

    @Override
    public DataResult<Shipment> getByOrderId(int orderId) {
        return new SuccessDataResult<>(this.shipmentDao.getByOrderId(orderId));
    }
}
