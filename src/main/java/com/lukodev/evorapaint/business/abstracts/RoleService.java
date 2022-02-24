package com.lukodev.evorapaint.business.abstracts;

import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.entities.concretes.Role;

import java.util.List;

public interface RoleService {

    Result add(Role role);
    Result update(Role role);
    Result delete(Role role);

    DataResult<List<Role>> getAll();
    DataResult<Role> getById(int id);

}
