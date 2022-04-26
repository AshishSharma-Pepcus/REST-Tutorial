package com.udemy.eazybytes.example1.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
@Order(1)

public class VehicleStartedAspect {
    private Logger logger = Logger.getLogger(VehicleStartedAspect.class.getName());

    @Before("execution(* com.udemy.eazybytes.example1.services.*.*(..)) && args(vehicleStarted,..)")
    public void checkVehicleStarted(JoinPoint joinPoint, boolean vehicleStarted) throws Throwable {
        if(!vehicleStarted) {
            throw new RuntimeException("Vehicle not started");
        }
    }
}
