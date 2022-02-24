package com.lukodev.evorapaint.dataAccess.abstracts;

import com.lukodev.evorapaint.entities.concretes.RemittanceInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RemittanceInformationDao extends JpaRepository<RemittanceInformation, Integer> {

    List<RemittanceInformation> getAllByPaymentMethodId(int paymentMethodId);

}
