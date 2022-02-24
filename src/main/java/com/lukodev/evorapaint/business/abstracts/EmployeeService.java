package com.lukodev.evorapaint.business.abstracts;

import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.entities.concretes.Employee;

import java.util.List;

public interface EmployeeService {

    Result add(Employee employee);
    Result update(Employee employee);
    Result delete(Employee employee);

    DataResult<List<Employee>> getAll();
    DataResult<Employee> getById(int id);

}
