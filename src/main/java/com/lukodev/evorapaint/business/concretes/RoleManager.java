package com.lukodev.evorapaint.business.concretes;

import com.lukodev.evorapaint.business.abstracts.RoleService;
import com.lukodev.evorapaint.business.constants.Messages;
import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.core.utilities.results.SuccessDataResult;
import com.lukodev.evorapaint.core.utilities.results.SuccessResult;
import com.lukodev.evorapaint.dataAccess.abstracts.RoleDao;
import com.lukodev.evorapaint.entities.concretes.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleManager implements RoleService {

    private RoleDao roleDao;

    @Autowired
    public RoleManager(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @CacheEvict(value = "role.getAll",allEntries = true)
    public Result add(Role role) {
        this.roleDao.save(role);
        return new SuccessResult(Messages.ROLE_ADDED);
    }

    @Override
    @CacheEvict(value = "role.getAll",allEntries = true)
    public Result update(Role role) {
        this.roleDao.save(role);
        return new SuccessResult(Messages.ROLE_UPDATED);
    }

    @Override
    @CacheEvict(value = "role.getAll",allEntries = true)
    public Result delete(Role role) {
        this.roleDao.delete(role);
        return new SuccessResult(Messages.ROLE_DELETED);
    }

    @Override
    @Cacheable(value = "role.getAll")
    public DataResult<List<Role>> getAll() {
        return new SuccessDataResult<>(this.roleDao.findAll());
    }

    @Override
    public DataResult<Role> getById(int id) {
        return new SuccessDataResult<>(this.roleDao.findById(id).get());
    }
}
