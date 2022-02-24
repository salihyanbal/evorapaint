package com.lukodev.evorapaint.business.abstracts;

import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.entities.concretes.Address;

import java.util.List;

public interface AddressService {

    Result add(Address address);
    Result update(Address address);
    Result delete(Address address);

    DataResult<List<Address>> getAll();
    DataResult<List<Address>> getAllByCustomerId(int customerId);

    DataResult<List<Address>> getAllByPageNumber(int pageNumber);

    DataResult<Address> getById(int id);
}
