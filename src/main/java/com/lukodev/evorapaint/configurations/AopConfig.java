package com.lukodev.evorapaint.configurations;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AopConfig {

    @Pointcut("within(com.lukodev.evorapaint.business.concretes.*)")
    public void performanceMonitor() { }

}
