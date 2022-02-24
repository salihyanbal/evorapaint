package com.lukodev.evorapaint.core.aspects.performance;

import com.lukodev.evorapaint.core.crossCuttingConcerns.logging.fileLogger.FileLogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class PerformanceAspect {

    private FileLogger fileLogger;
    private int methodInvocationMaximumTimeInMs = 5000;

    @Autowired
    public PerformanceAspect(FileLogger fileLogger) {
        this.fileLogger = fileLogger;
    }

    @Around("com.lukodev.evorapaint.configurations.AopConfig.performanceMonitor()")
    public Object doPerformanceControl(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        String methodName = method.toString();
        long start = System.currentTimeMillis();
        fileLogger.info("Method " + methodName + " execution started at: " + new Date());
        try {
            return pjp.proceed();
        }
        finally {
            long end = System.currentTimeMillis();
            long time = end - start;
            fileLogger.info("Method " + methodName + " execution lasted: " + time + " ms");
            fileLogger.info("Method " + methodName + " execution ended at: " + new Date());
            if (time > methodInvocationMaximumTimeInMs){
                fileLogger.warn(methodName + " Method execution longer than " + methodInvocationMaximumTimeInMs + " ms!");
            }
        }
    }
}
