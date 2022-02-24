package com.lukodev.evorapaint.business.abstracts;

import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.entities.concretes.UserRole;

import java.util.List;

public interface UserRoleService {

    Result add(UserRole userRole);
    Result update(UserRole userRole);
    Result delete(UserRole userRole);

    DataResult<List<UserRole>> getAll();
    DataResult<List<UserRole>> getAllByUserId(int userId);
    DataResult<List<UserRole>> getAllByRoleId(int roleId);

    DataResult<UserRole> getById(int id);

}
