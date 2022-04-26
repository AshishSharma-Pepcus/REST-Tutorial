package com.udemy.eazybytes.example1.services;

import com.udemy.eazybytes.example1.interfaces.Speakers;
import com.udemy.eazybytes.example1.interfaces.Tyres;
import com.udemy.eazybytes.example1.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleServices {
    @Autowired
    private Speakers speakers;
    @Autowired
    private Tyres tyres;

  //  @LogAspect
    public String playMusic(boolean vehicleStarted, Song song) {
       // throw new NumberFormatException("Null pointer exception inserted");
        String music = speakers.makeSound(song);
        return music;
    }

  //  @LogAspect
    public String moveVehicle(boolean vehicleStarted) {
        String rotate = tyres.rotate();
        return rotate;
    }

  //  @LogAspect
    public String stopVehicle() {
        String stop =  tyres.applyBreak();
        return stop;
    }
}
