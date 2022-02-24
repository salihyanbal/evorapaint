package com.lukodev.evorapaint.business.concretes;

import com.lukodev.evorapaint.business.abstracts.ShipmentMethodService;
import com.lukodev.evorapaint.business.constants.Messages;
import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.core.utilities.results.SuccessDataResult;
import com.lukodev.evorapaint.core.utilities.results.SuccessResult;
import com.lukodev.evorapaint.dataAccess.abstracts.ShipmentMethodDao;
import com.lukodev.evorapaint.entities.concretes.ShipmentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentMethodManager implements ShipmentMethodService {

    private ShipmentMethodDao shipmentMethodDao;

    @Autowired
    public ShipmentMethodManager(ShipmentMethodDao shipmentMethodDao) {
        this.shipmentMethodDao = shipmentMethodDao;
    }

    @Override
    @CacheEvict(value = "shipmentMethod.getAll",allEntries = true)
    public Result add(ShipmentMethod shipmentMethod) {
        this.shipmentMethodDao.save(shipmentMethod);
        return new SuccessResult(Messages.SHIPMENT_METHOD_ADDED);
    }

    @Override
    @CacheEvict(value = "shipmentMethod.getAll",allEntries = true)
    public Result update(ShipmentMethod shipmentMethod) {
        this.shipmentMethodDao.save(shipmentMethod);
        return new SuccessResult(Messages.SHIPMENT_METHOD_UPDATED);
    }

    @Override
    @CacheEvict(value = "shipmentMethod.getAll",allEntries = true)
    public Result delete(ShipmentMethod shipmentMethod) {
        this.shipmentMethodDao.delete(shipmentMethod);
        return new SuccessResult(Messages.SHIPMENT_METHOD_DELETED);
    }

    @Override
    @Cacheable(value = "shipmentMethod.getAll")
    public DataResult<List<ShipmentMethod>> getAll() {
        return new SuccessDataResult<>(this.shipmentMethodDao.findAll());
    }

    @Override
    public DataResult<List<ShipmentMethod>> getAllByShipmentTypeId(int shipmentTypeId) {
        return new SuccessDataResult<>(this.shipmentMethodDao.getAllByShipmentTypeId(shipmentTypeId));
    }

    @Override
    public DataResult<ShipmentMethod> getById(int id) {
        return new SuccessDataResult<>(this.shipmentMethodDao.findById(id).get());
    }
}
