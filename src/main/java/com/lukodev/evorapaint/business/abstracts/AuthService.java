package com.lukodev.evorapaint.business.abstracts;

import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.core.utilities.security.models.TokenModel;
import com.lukodev.evorapaint.entities.concretes.Customer;
import com.lukodev.evorapaint.entities.concretes.Employee;
import com.lukodev.evorapaint.entities.concretes.User;
import com.lukodev.evorapaint.entities.dtos.CustomerForRegisterDto;
import com.lukodev.evorapaint.entities.dtos.EmployeeForRegisterDto;
import com.lukodev.evorapaint.entities.dtos.UserForLoginDto;

public interface AuthService {

    DataResult<Employee> registerForEmployee(EmployeeForRegisterDto employeeForRegisterDto);

    DataResult<Customer> registerForCustomer(CustomerForRegisterDto customerForRegisterDto);

    DataResult<User> login(UserForLoginDto userForLoginDto);

    Result userExist(String email);

    DataResult<String> generateAccessToken(User user);

    DataResult<String> generateRefreshToken(User user);

    DataResult<TokenModel> generateTokens(User user);

    DataResult<TokenModel> refreshTokens(String refreshToken);

}
