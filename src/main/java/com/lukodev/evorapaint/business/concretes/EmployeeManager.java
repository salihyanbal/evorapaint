package com.lukodev.evorapaint.business.concretes;

import com.lukodev.evorapaint.business.abstracts.EmployeeService;
import com.lukodev.evorapaint.business.constants.Messages;
import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.core.utilities.results.SuccessDataResult;
import com.lukodev.evorapaint.core.utilities.results.SuccessResult;
import com.lukodev.evorapaint.dataAccess.abstracts.EmployeeDao;
import com.lukodev.evorapaint.entities.concretes.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeManager implements EmployeeService {

    private EmployeeDao employeeDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeManager(EmployeeDao employeeDao, PasswordEncoder passwordEncoder) {
        this.employeeDao = employeeDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Result add(Employee employee) {
        this.employeeDao.save(employee);
        return new SuccessResult(Messages.EMPLOYEE_ADDED);
    }

    @Override
    public Result update(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        this.employeeDao.save(employee);
        return new SuccessResult(Messages.EMPLOYEE_UPDATED);
    }

    @Override
    public Result delete(Employee employee) {
        this.employeeDao.delete(employee);
        return new SuccessResult(Messages.EMPLOYEE_DELETED);
    }

    @Override
    public DataResult<List<Employee>> getAll() {
        return new SuccessDataResult<>(this.employeeDao.findAll());
    }

    @Override
    public DataResult<Employee> getById(int id) {
        return new SuccessDataResult<>(this.employeeDao.findById(id).get());
    }
}
