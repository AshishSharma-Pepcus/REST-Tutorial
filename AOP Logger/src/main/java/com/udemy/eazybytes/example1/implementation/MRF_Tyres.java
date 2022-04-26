package com.udemy.eazybytes.example1.implementation;

import com.udemy.eazybytes.example1.interfaces.Tyres;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MRF_Tyres implements Tyres {


    @Override
    public String rotate() {
        return "Vehicle is moving with MRF tyres";
    }

    @Override
    public String applyBreak() {
        return "Stopping vehicle by applying breaks";
    }
}
