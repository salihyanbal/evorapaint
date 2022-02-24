package com.lukodev.evorapaint.business.abstracts;

import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.entities.concretes.RemittanceInformation;

import java.util.List;

public interface RemittanceInformationService {

    Result add(RemittanceInformation remittanceInformation);
    Result update(RemittanceInformation remittanceInformation);
    Result delete(RemittanceInformation remittanceInformation);

    DataResult<List<RemittanceInformation>> getAll();
    DataResult<List<RemittanceInformation>> getAllByPaymentMethodId(int paymentMethodId);

    DataResult<RemittanceInformation> getById(int id);

}
