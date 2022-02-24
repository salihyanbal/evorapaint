package com.lukodev.evorapaint.dataAccess.abstracts;

import com.lukodev.evorapaint.entities.concretes.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressDao extends JpaRepository<Address, Integer> {

    List<Address> getAllByCustomerId(int customerId);

}
