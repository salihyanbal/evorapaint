package com.lukodev.evorapaint.business.abstracts;

import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.core.utilities.results.SuccessResult;
import com.lukodev.evorapaint.entities.concretes.User;
import com.lukodev.evorapaint.entities.concretes.UserVerificationByMail;

import java.time.LocalDateTime;
import java.util.List;

public interface UserVerificationByMailService {

    Result add(UserVerificationByMail userVerificationByMail);
    Result update(UserVerificationByMail userVerificationByMail);
    Result delete(UserVerificationByMail userVerificationByMail);

    Result verify(UserVerificationByMail userVerificationByMail);

    Result sendVerificationMail(User user);

    DataResult<List<UserVerificationByMail>> getAll();
    DataResult<UserVerificationByMail> getById(int id);
    DataResult<UserVerificationByMail> getByUserId(int userId);

    DataResult<Boolean> isUserVerified(int userId);

}
