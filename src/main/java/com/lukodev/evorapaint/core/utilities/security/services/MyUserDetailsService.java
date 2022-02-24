package com.lukodev.evorapaint.core.utilities.security.services;

import com.lukodev.evorapaint.business.abstracts.UserRoleService;
import com.lukodev.evorapaint.business.abstracts.UserService;
import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.security.models.MyUserDetails;
import com.lukodev.evorapaint.entities.concretes.Role;
import com.lukodev.evorapaint.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserService userService;
    private UserRoleService userRoleService;

    @Autowired
    public MyUserDetailsService(UserService userService, UserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        DataResult<User> foundedUser = userService.getByEmail(s);
        if(foundedUser == null){
            throw new UsernameNotFoundException(s + " adlı emaille kayıtlı kullanıcı bulunamadı!");
        }
        List<Role> roles = userRoleService.getAllByUserId(foundedUser.getData().getId()).getData().stream()
                .map(userRole -> userRole.getRole())
                .collect(Collectors.toList());
        List<GrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return new MyUserDetails(foundedUser.getData().getEmail(), foundedUser.getData().getPassword(), authorities);
    }
}
