package com.udemy.eazybytes.example1.implementation;

import com.udemy.eazybytes.example1.interfaces.Tyres;
import org.springframework.stereotype.Component;

@Component
public class CEAT_Tyres implements Tyres {

    @Override
    public String rotate() {
        return "Vehicle is moving with CEAT tyres";
    }

    @Override
    public String applyBreak() {
        return "Stopping vehicle by applying breaks";
    }
}
