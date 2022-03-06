package com.lukodev.evorapaint.business.concretes;

import com.lukodev.evorapaint.business.abstracts.*;
import com.lukodev.evorapaint.business.constants.Messages;
import com.lukodev.evorapaint.core.utilities.results.*;
import com.lukodev.evorapaint.core.utilities.security.jwt.TokenHelper;
import com.lukodev.evorapaint.core.utilities.security.models.TokenModel;
import com.lukodev.evorapaint.entities.concretes.*;
import com.lukodev.evorapaint.entities.dtos.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthManager implements AuthService {

    private UserService userService;
    private EmployeeService employeeService;
    private CustomerService customerService;
    private RoleService roleService;
    private UserRoleService userRoleService;
    private UserVerificationByMailService userVerificationByMailService;
    private TokenHelper tokenHelper;
    private PasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;

    @Autowired
    public AuthManager(UserService userService, EmployeeService employeeService, CustomerService customerService, RoleService roleService, UserRoleService userRoleService, UserVerificationByMailService userVerificationByMailService, TokenHelper tokenHelper, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userService = userService;
        this.employeeService = employeeService;
        this.customerService = customerService;
        this.roleService = roleService;
        this.userRoleService = userRoleService;
        this.userVerificationByMailService = userVerificationByMailService;
        this.tokenHelper = tokenHelper;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public DataResult<Employee> registerForEmployee(EmployeeForRegisterDto employeeForRegisterDto) {
        String encodedPassword = passwordEncoder.encode(employeeForRegisterDto.getPassword());
        Employee employeeToRegister = modelMapper.map(employeeForRegisterDto, Employee.class);
        employeeToRegister.setPassword(encodedPassword);
        employeeService.add(employeeToRegister);
        this.userVerificationByMailService.sendVerificationMail(employeeToRegister);
        return new SuccessDataResult<>(employeeToRegister, Messages.USER_REGISTERED);
    }

    @Transactional
    @Override
    public DataResult<Customer> registerForCustomer(CustomerForRegisterDto customerForRegisterDto) {
        String encodedPassword = passwordEncoder.encode(customerForRegisterDto.getPassword());
        Customer customerToRegister = modelMapper.map(customerForRegisterDto, Customer.class);
        customerToRegister.setPassword(encodedPassword);
        customerService.add(customerToRegister);
        this.userVerificationByMailService.sendVerificationMail(customerToRegister);
        return new SuccessDataResult<>(customerToRegister, Messages.USER_REGISTERED);
    }

    @Override
    public DataResult<User> login(UserForLoginDto userForLoginDto) {
        User user = userService.getByEmail(userForLoginDto.getEmail()).getData();
        if(user == null){
            return new ErrorDataResult<>("Kullanıcı bulunamadı.");
        }
        boolean isMatch = passwordEncoder.matches(userForLoginDto.getPassword(), user.getPassword());
        if(!isMatch){
            return new ErrorDataResult<>("Şifrenizi eksik veya hatalı girdiniz.");
        }
        if(!user.isActive()){
            return new ErrorDataResult<>("Kullanıcı hesabı askıya alınmış.");
        }
        return new SuccessDataResult<>(user, "Giriş başarılı.");
    }

    @Override
    @Transactional
    public Result changeEmail(ChangeEmailDto changeEmailDto) {
        User user = userService.getByEmail(changeEmailDto.getUser().getEmail()).getData();
        user.setEmail(changeEmailDto.getNewEmail());
        this.userVerificationByMailService.sendVerificationMail(user);
        this.userService.update(user);
        return new SuccessResult("Emailiniz güncellendi, lütfen emailinizi doğrulayınız.");
    }

    @Override
    @Transactional
    public Result changePassword(ChangePasswordDto changePasswordDto) {
        User oldUser = userService.getByEmail(changePasswordDto.getUser().getEmail()).getData();
        boolean isMatch = passwordEncoder.matches(changePasswordDto.getOldPassword(), oldUser.getPassword());
        if(!isMatch){
            return new ErrorDataResult<>("Eski şifrenizi eksik veya hatalı girdiniz.");
        }
        String encodedPassword = passwordEncoder.encode(changePasswordDto.getNewPassword());
        oldUser.setPassword(encodedPassword);
        this.userService.update(oldUser);
        return new SuccessResult("Parolanız başarıyla güncellendi.");
    }

    @Override
    public Result userExist(String email) {
        return userService.getByEmail(email).getData() != null ? new SuccessResult("Kullanıcı kayıtlı.") : new ErrorResult();
    }

    @Override
    public DataResult<String> generateAccessToken(User user) {
        List<UserRole> userRoles = userRoleService.getAllByUserId(user.getId()).getData();
        List<String> roles = userRoles.stream().map(ur -> ur.getRole().getName()).collect(Collectors.toList());
        return new SuccessDataResult<>(tokenHelper.createAccessToken(roles, user.getEmail(), user.getId()),null);
    }

    @Override
    public DataResult<String> generateRefreshToken(User user) {
        return new SuccessDataResult<>(tokenHelper.createRefreshToken(user.getEmail(),user.getId()),null);
    }

    @Override
    public DataResult<TokenModel> generateTokens(User user) {
        String accessToken = generateAccessToken(user).getData();
        String refreshToken = generateRefreshToken(user).getData();
        TokenModel tokenModel = new TokenModel(accessToken, refreshToken);
        return new SuccessDataResult<>(tokenModel);
    }

    @Override
    public DataResult<TokenModel> refreshTokens(String refreshToken) {
        if(this.tokenHelper.isTokenExpired(refreshToken)){
            return new ErrorDataResult<>("Token expired.");
        }
        int userId = this.tokenHelper.extractUserId(refreshToken);
        User user = this.userService.getById(userId).getData();
        return generateTokens(user);
    }


}
