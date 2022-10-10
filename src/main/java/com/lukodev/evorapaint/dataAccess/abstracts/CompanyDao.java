package com.lukodev.evorapaint.dataAccess.abstracts;

import com.lukodev.evorapaint.entities.concretes.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyDao extends JpaRepository<Company, Integer> {

    Company getByCustomerId(int customerId);
    boolean existsByCustomerId(int customerId);

}
