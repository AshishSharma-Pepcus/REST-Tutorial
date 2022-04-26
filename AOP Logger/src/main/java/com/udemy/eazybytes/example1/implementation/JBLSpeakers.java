package com.udemy.eazybytes.example1.implementation;

import com.udemy.eazybytes.example1.interfaces.Speakers;
import com.udemy.eazybytes.example1.model.Song;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class JBLSpeakers implements Speakers {

    @Override
    public String makeSound(Song song) {
        return "Now playing " + song.getTitle() + " By " + song.getSingerName() +
                " with JBL Speakers";
    }
}
