package com.lukodev.evorapaint.dataAccess.abstracts;

import com.lukodev.evorapaint.entities.concretes.UserVerificationByMail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVerificationByMailDao extends JpaRepository<UserVerificationByMail, Integer> {

    UserVerificationByMail getByUserId(int userId);

}
