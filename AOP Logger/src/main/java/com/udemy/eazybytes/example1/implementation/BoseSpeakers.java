package com.udemy.eazybytes.example1.implementation;

import com.udemy.eazybytes.example1.interfaces.Speakers;
import com.udemy.eazybytes.example1.model.Song;
import org.springframework.stereotype.Component;

@Component
public class BoseSpeakers implements Speakers {

    @Override
    public String makeSound(Song song) {
        return "Now playing" + song.getTitle() + " By " + song.getSingerName()
                + " with BoseSpeakers";
    }
}
