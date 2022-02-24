package com.lukodev.evorapaint.business.concretes;

import com.lukodev.evorapaint.business.abstracts.PackageTypeService;
import com.lukodev.evorapaint.business.constants.Messages;
import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.core.utilities.results.SuccessDataResult;
import com.lukodev.evorapaint.core.utilities.results.SuccessResult;
import com.lukodev.evorapaint.dataAccess.abstracts.PackageTypeDao;
import com.lukodev.evorapaint.entities.concretes.PackageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageTypeManager implements PackageTypeService {

    private PackageTypeDao packageTypeDao;

    @Autowired
    public PackageTypeManager(PackageTypeDao packageTypeDao) {
        this.packageTypeDao = packageTypeDao;
    }

    @Override
    @CacheEvict(value = "packageType.getAll", allEntries = true)
    public Result add(PackageType packageType) {
        this.packageTypeDao.save(packageType);
        return new SuccessResult(Messages.PACKAGE_TYPE_ADDED);
    }

    @Override
    @CacheEvict(value = "packageType.getAll", allEntries = true)
    public Result update(PackageType packageType) {
        this.packageTypeDao.save(packageType);
        return new SuccessResult(Messages.PACKAGE_TYPE_UPDATED);
    }

    @Override
    @CacheEvict(value = "packageType.getAll", allEntries = true)
    public Result delete(PackageType packageType) {
        this.packageTypeDao.delete(packageType);
        return new SuccessResult(Messages.PACKAGE_TYPE_DELETED);
    }

    @Override
    @Cacheable(value = "packageType.getAll")
    public DataResult<List<PackageType>> getAll() {
        return new SuccessDataResult<>(this.packageTypeDao.findAll());
    }

    @Override
    public DataResult<List<PackageType>> getAllByShipmentTypeId(int shipmentTypeId) {
        return new SuccessDataResult<>(this.packageTypeDao.getAllByShipmentTypeId(shipmentTypeId));
    }

    @Override
    public DataResult<PackageType> getById(int id) {
        return new SuccessDataResult<>(this.packageTypeDao.findById(id).get());
    }
}
