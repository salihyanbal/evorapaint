package com.lukodev.evorapaint.configurations;

import com.lukodev.evorapaint.core.exceptionHandling.RestExceptionHandler;
import com.lukodev.evorapaint.core.utilities.security.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;
    private JwtRequestFilter jwtRequestFilter;
    private RestExceptionHandler restExceptionHandler;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, JwtRequestFilter jwtRequestFilter, RestExceptionHandler restExceptionHandler) {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
        this.restExceptionHandler = restExceptionHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().cacheControl();
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors();

        categoriesAuthorizations(http);
        customersAuthorizations(http);
        employeesAuthorizations(http);
        orderAuthorizations(http);
        orderStatusesAuthorizations(http);
        packageTypesAuthorizations(http);
        paymentMethodsAuthorizations(http);
        productsAuthorizations(http);
        productImagesAuthorizations(http);
        remittanceInformationsAuthorizations(http);
        rolesAuthorizations(http);
        shipmentsAuthorizations(http);
        shipmentMethodsAuthorizations(http);
        shipmentTypesAuthorizations(http);
        usersAuthorizations(http);
        userRolesAuthorizations(http);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    private void categoriesAuthorizations(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers("/api/categories/add").hasAuthority("admin")
                .antMatchers("/api/categories/update").hasAuthority("admin")
                .antMatchers("/api/categories/delete").hasAuthority("admin");
    }

    private void customersAuthorizations(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers("/api/customer/add").hasAuthority("admin")
                .antMatchers("/api/customer/update").hasAuthority("admin")
                .antMatchers("/api/customer/delete").hasAuthority("admin");
    }

    private void employeesAuthorizations(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers("/api/employees/add").hasAuthority("admin")
                .antMatchers("/api/employees/update").hasAuthority("admin")
                .antMatchers("/api/employees/delete").hasAuthority("admin");
    }

    private void orderAuthorizations(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers("/api/orderstatuses/getall").hasAuthority("admin");
    }

    private void orderStatusesAuthorizations(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers("/api/orderstatuses/add").hasAuthority("admin")
                .antMatchers("/api/orderstatuses/update").hasAuthority("admin")
                .antMatchers("/api/orderstatuses/delete").hasAuthority("admin");
    }

    private void packageTypesAuthorizations(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers("/api/packagetypes/add").hasAuthority("admin")
                .antMatchers("/api/packagetypes/update").hasAuthority("admin")
                .antMatchers("/api/packagetypes/delete").hasAuthority("admin");
    }

    private void paymentMethodsAuthorizations(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers("/api/paymentmethods/add").hasAuthority("admin")
                .antMatchers("/api/paymentmethods/update").hasAuthority("admin")
                .antMatchers("/api/paymentmethods/delete").hasAuthority("admin");
    }

    private void productsAuthorizations(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers("/api/products/add").hasAuthority("admin")
                .antMatchers("/api/products/update").hasAuthority("admin")
                .antMatchers("/api/products/delete").hasAuthority("admin");
    }

    private void productImagesAuthorizations(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers("/api/productimages/add").hasAuthority("admin")
                .antMatchers("/api/productimages/update").hasAuthority("admin")
                .antMatchers("/api/productimages/delete").hasAuthority("admin");
    }

    private void remittanceInformationsAuthorizations(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers("/api/remittanceinformations/add").hasAuthority("admin")
                .antMatchers("/api/remittanceinformations/update").hasAuthority("admin")
                .antMatchers("/api/remittanceinformations/delete").hasAuthority("admin");
    }

    private void rolesAuthorizations(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers("/api/roles/add").hasAuthority("admin")
                .antMatchers("/api/roles/update").hasAuthority("admin")
                .antMatchers("/api/roles/delete").hasAuthority("admin");
    }

    private void shipmentsAuthorizations(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers("/api/shipments/add").hasAuthority("admin")
                .antMatchers("/api/shipments/update").hasAuthority("admin")
                .antMatchers("/api/shipments/delete").hasAuthority("admin");
    }

    private void shipmentMethodsAuthorizations(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers("/api/shipmentmethods/add").hasAuthority("admin")
                .antMatchers("/api/shipmentmethods/update").hasAuthority("admin")
                .antMatchers("/api/shipmentmethods/delete").hasAuthority("admin");
    }

    private void shipmentTypesAuthorizations(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers("/api/shipmenttypes/add").hasAuthority("admin")
                .antMatchers("/api/shipmenttypes/update").hasAuthority("admin")
                .antMatchers("/api/shipmenttypes/delete").hasAuthority("admin");
    }

    private void usersAuthorizations(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers("/api/users/add").hasAuthority("admin")
                .antMatchers("/api/users/update").hasAuthority("admin")
                .antMatchers("/api/users/delete").hasAuthority("admin");
    }

    private void userRolesAuthorizations(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers("/api/userroles/add").hasAuthority("admin")
                .antMatchers("/api/userroles/update").hasAuthority("admin")
                .antMatchers("/api/userroles/delete").hasAuthority("admin");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
