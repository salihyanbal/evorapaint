package com.lukodev.evorapaint.business.concretes;

import com.lukodev.evorapaint.business.abstracts.ShipmentTypeService;
import com.lukodev.evorapaint.business.constants.Messages;
import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.core.utilities.results.SuccessDataResult;
import com.lukodev.evorapaint.core.utilities.results.SuccessResult;
import com.lukodev.evorapaint.dataAccess.abstracts.ShipmentTypeDao;
import com.lukodev.evorapaint.entities.concretes.ShipmentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentTypeManager implements ShipmentTypeService {

    private ShipmentTypeDao shipmentTypeDao;

    @Autowired
    public ShipmentTypeManager(ShipmentTypeDao shipmentTypeDao) {
        this.shipmentTypeDao = shipmentTypeDao;
    }

    @Override
    @CacheEvict(value = "shipmentType.getAll", allEntries = true)
    public Result add(ShipmentType shipmentType) {
        this.shipmentTypeDao.save(shipmentType);
        return new SuccessResult(Messages.SHIPMENT_TYPE_ADDED);
    }

    @Override
    @CacheEvict(value = "shipmentType.getAll", allEntries = true)
    public Result update(ShipmentType shipmentType) {
        this.shipmentTypeDao.save(shipmentType);
        return new SuccessResult(Messages.SHIPMENT_TYPE_UPDATED);
    }

    @Override
    @CacheEvict(value = "shipmentType.getAll", allEntries = true)
    public Result delete(ShipmentType shipmentType) {
        this.shipmentTypeDao.delete(shipmentType);
        return new SuccessResult(Messages.SHIPMENT_TYPE_DELETED);
    }

    @Override
    @Cacheable(value = "shipmentType.getAll")
    public DataResult<List<ShipmentType>> getAll() {
        return new SuccessDataResult<>(this.shipmentTypeDao.findAll());
    }

    @Override
    public DataResult<ShipmentType> getById(int id) {
        return new SuccessDataResult<>(this.shipmentTypeDao.findById(id).get());
    }
}
