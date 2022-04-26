package com.udemy.eazybytes.example1.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Component
@Order(2)
public class LoggerAspect {
    private Logger logger = Logger.getLogger(LoggerAspect.class.getName());
    // this logger object will help us in logging all the details that i want

    @Around("execution(* com.udemy.eazybytes.example1.services.*.*(..))")
    public void log(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info(joinPoint.getSignature().toString() + " method execution starts here");
        Instant start = Instant.now();
        logger.info("Start" + String.valueOf(start));
        joinPoint.proceed();
        Instant finish = Instant.now();
        logger.info("Finish " + String.valueOf(finish));
        long timeElapsed = Duration.between(start, finish).toMillis();
        logger.info("Time taken in method execution is " + timeElapsed);
        logger.info(joinPoint.getSignature().toString() + " method execution ends here");
    }

//    @Around("@annotation(com.udemy.eazybytes.example1.interfaces)")
//    public void logWithAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
//        logger.info(joinPoint.getSignature().toString() + " method execution starts here");
//        Instant start = Instant.now();
//        logger.info("Start" + String.valueOf(start));
//        joinPoint.proceed();
//        Instant finish = Instant.now();
//        logger.info("Finish " + String.valueOf(finish));
//        long timeElapsed = Duration.between(start, finish).toMillis();
//        logger.info("Time taken in method execution is " + timeElapsed);
//        logger.info(joinPoint.getSignature().toString() + " method execution ends here");
//    }

    @AfterThrowing(value = "execution(* com.udemy.eazybytes.example1.services.*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        logger.log(Level.SEVERE, joinPoint.getSignature() + " An Exception thrown with @AfterThrowing " +
                "Which happened due to : "+ex.getMessage());
    }

    @AfterReturning(value = "execution(* com.udemy.eazybytes.example1.services.*.*(..))", returning = "retVal")
    public void logStatus(JoinPoint joinPoint, Object retVal) {
        logger.log(Level.INFO,joinPoint.getSignature()+ " Method successfully processed with the status " +
                   retVal.toString());
    }
}
