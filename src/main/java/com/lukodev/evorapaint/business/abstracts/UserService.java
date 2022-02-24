package com.lukodev.evorapaint.business.abstracts;

import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.entities.concretes.User;

import java.util.List;

public interface UserService {

    Result add(User user);
    Result update(User user);
    Result delete(User user);

    DataResult<List<User>> getAll();
    DataResult<User> getById(int id);
    DataResult<User> getByEmail(String email);

}
