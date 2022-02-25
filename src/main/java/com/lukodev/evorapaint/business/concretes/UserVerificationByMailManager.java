package com.lukodev.evorapaint.business.concretes;

import com.lukodev.evorapaint.business.abstracts.UserService;
import com.lukodev.evorapaint.business.abstracts.UserVerificationByMailService;
import com.lukodev.evorapaint.business.constants.Messages;
import com.lukodev.evorapaint.core.services.automaticMail.AutomaticMailService;
import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.core.utilities.results.SuccessDataResult;
import com.lukodev.evorapaint.core.utilities.results.SuccessResult;
import com.lukodev.evorapaint.dataAccess.abstracts.UserVerificationByMailDao;
import com.lukodev.evorapaint.entities.concretes.User;
import com.lukodev.evorapaint.entities.concretes.UserVerificationByMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserVerificationByMailManager implements UserVerificationByMailService {

    private UserVerificationByMailDao userVerificationByMailDao;
    private UserService userService;
    private AutomaticMailService automaticMailService;
    private int expirationInMinutes = 10;

    @Autowired
    public UserVerificationByMailManager(UserVerificationByMailDao userVerificationByMailDao, UserService userService, AutomaticMailService automaticMailService) {
        this.userVerificationByMailDao = userVerificationByMailDao;
        this.userService = userService;
        this.automaticMailService = automaticMailService;
    }

    @Override
    @Transactional
    @CacheEvict(value = "userVerificationByMail.getAll", allEntries = true)
    public Result add(UserVerificationByMail userVerificationByMail) {
        if(userVerificationByMail.getUser().getEmail() == null){
            userVerificationByMail.setUser(this.userService.getById(userVerificationByMail.getUser().getId()).getData());
        }
        DataResult<Integer> sendResult = this.automaticMailService.sendVerificationMail(userVerificationByMail.getUser());
        userVerificationByMail.setVerificationCode(sendResult.getData());
        userVerificationByMail.setExpiration(LocalDateTime.now().plusMinutes(expirationInMinutes));
        this.userVerificationByMailDao.save(userVerificationByMail);
        return new SuccessResult();
    }

    @Override
    @Transactional
    @CacheEvict(value = "userVerificationByMail.getAll", allEntries = true)
    public Result update(UserVerificationByMail userVerificationByMail) {
        if(userVerificationByMail.getUser().getEmail() == null){
            userVerificationByMail.setUser(this.userService.getById(userVerificationByMail.getUser().getId()).getData());
        }
        DataResult<Integer> sendResult = this.automaticMailService.sendVerificationMail(userVerificationByMail.getUser());
        userVerificationByMail.setVerificationCode(sendResult.getData());
        userVerificationByMail.setExpiration(LocalDateTime.now().plusMinutes(expirationInMinutes));
        this.userVerificationByMailDao.save(userVerificationByMail);
        return new SuccessResult();
    }

    @Override
    @CacheEvict(value = "userVerificationByMail.getAll", allEntries = true)
    public Result delete(UserVerificationByMail userVerificationByMail) {
        this.userVerificationByMailDao.delete(userVerificationByMail);
        return new SuccessResult(Messages.USER_VERIFICATION_BY_MAIL_DELETED);
    }

    @Override
    @CacheEvict(value = "userVerificationByMail.getAll", allEntries = true)
    public Result verify(UserVerificationByMail userVerificationByMail) {
        userVerificationByMail.setVerified(true);
        this.userVerificationByMailDao.save(userVerificationByMail);
        return new SuccessResult();
    }

    @Override
    public Result sendVerificationMail(User user){
        UserVerificationByMail userVerificationByMail = new UserVerificationByMail(0,0,false,LocalDateTime.now().plusMinutes(10),user);
        add(userVerificationByMail);
        return new SuccessResult("Onay maili gönderildi.");
    }

    @Override
    @Cacheable("userVerificationByMail.getAll")
    public DataResult<List<UserVerificationByMail>> getAll() {
        return new SuccessDataResult<>(this.userVerificationByMailDao.findAll());
    }

    @Override
    public DataResult<UserVerificationByMail> getById(int id) {
        return new SuccessDataResult<>(this.userVerificationByMailDao.findById(id).get());
    }

    @Override
    public DataResult<UserVerificationByMail> getByUserId(int userId) {
        return new SuccessDataResult<>(this.userVerificationByMailDao.getByUserId(userId));
    }

    @Override
    public DataResult<Boolean> isUserVerified(int userId) {
        return new SuccessDataResult<>(this.getByUserId(userId).getData().isVerified());
    }


}
