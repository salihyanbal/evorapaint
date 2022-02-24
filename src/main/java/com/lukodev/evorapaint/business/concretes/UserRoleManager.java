package com.lukodev.evorapaint.business.concretes;

import com.lukodev.evorapaint.business.abstracts.UserRoleService;
import com.lukodev.evorapaint.business.constants.Messages;
import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.core.utilities.results.SuccessDataResult;
import com.lukodev.evorapaint.core.utilities.results.SuccessResult;
import com.lukodev.evorapaint.dataAccess.abstracts.UserRoleDao;
import com.lukodev.evorapaint.entities.concretes.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleManager implements UserRoleService {

    private UserRoleDao userRoleDao;

    @Autowired
    public UserRoleManager(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    @Override
    public Result add(UserRole userRole) {
        this.userRoleDao.save(userRole);
        return new SuccessResult(Messages.USER_ROLE_ADDED);
    }

    @Override
    public Result update(UserRole userRole) {
        this.userRoleDao.save(userRole);
        return new SuccessResult(Messages.USER_ROLE_UPDATED);
    }

    @Override
    public Result delete(UserRole userRole) {
        this.userRoleDao.delete(userRole);
        return new SuccessResult(Messages.USER_ROLE_DELETED);
    }

    @Override
    public DataResult<List<UserRole>> getAll() {
        return new SuccessDataResult<>(this.userRoleDao.findAll());
    }

    @Override
    public DataResult<List<UserRole>> getAllByUserId(int userId) {
        return new SuccessDataResult<>(this.userRoleDao.getAllByUserId(userId));
    }

    @Override
    public DataResult<List<UserRole>> getAllByRoleId(int roleId) {
        return new SuccessDataResult<>(this.userRoleDao.getAllByRoleId(roleId));
    }

    @Override
    public DataResult<UserRole> getById(int id) {
        return new SuccessDataResult<>(this.userRoleDao.findById(id).get());
    }
}
