package com.lukodev.evorapaint.dataAccess.abstracts;

import com.lukodev.evorapaint.entities.concretes.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleDao extends JpaRepository<UserRole, Integer> {

    List<UserRole> getAllByUserId(int userId);
    List<UserRole> getAllByRoleId(int roleId);

}
