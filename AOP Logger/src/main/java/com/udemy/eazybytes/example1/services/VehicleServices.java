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

    public void playMusic(boolean vehicleStarted, Song song) {
        String music = speakers.makeSound(song);
        System.out.println(music);
    }

    public void moveVehicle(boolean vehicleStarted) {
        String rotate = tyres.rotate();
        System.out.println(rotate);
    }

    public void stopVehicle() {
        String stop =  tyres.applyBreak();
        System.out.println(stop);
    }
}
