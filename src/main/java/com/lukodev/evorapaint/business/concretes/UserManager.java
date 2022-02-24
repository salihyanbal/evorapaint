package com.lukodev.evorapaint.business.concretes;

import com.lukodev.evorapaint.business.abstracts.UserService;
import com.lukodev.evorapaint.business.constants.Messages;
import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.core.utilities.results.SuccessDataResult;
import com.lukodev.evorapaint.core.utilities.results.SuccessResult;
import com.lukodev.evorapaint.dataAccess.abstracts.UserDao;
import com.lukodev.evorapaint.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements UserService {

    private UserDao userDao;

    @Autowired
    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Result add(User user) {
        this.userDao.save(user);
        return new SuccessResult(Messages.USER_ADDED);
    }

    @Override
    public Result update(User user) {
        this.userDao.save(user);
        return new SuccessResult(Messages.USER_UPDATED);
    }

    @Override
    public Result delete(User user) {
        this.userDao.delete(user);
        return new SuccessResult(Messages.USER_DELETED);
    }

    @Override
    public DataResult<List<User>> getAll() {
        return new SuccessDataResult<>(this.userDao.findAll());
    }

    @Override
    public DataResult<User> getById(int id) {
        return new SuccessDataResult<>(this.userDao.findById(id).get());
    }

    @Override
    public DataResult<User> getByEmail(String email) {
        return new SuccessDataResult<>(this.userDao.getByEmail(email));
    }
}
