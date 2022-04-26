package com.udemy.eazybytes.example1.config;

import com.udemy.eazybytes.example1.model.Song;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"com.udemy.eazybytes.example1.bean", "com.udemy.eazybytes.example1.model",
                               "com.udemy.eazybytes.example1.implementation", "com.udemy.eazybytes.example1.services",
                               "com.udemy.eazybytes.example1.aspects"})
@EnableAspectJAutoProxy
public class ProjectConfig {

    @Bean(name = "song1")
    Song song1() {
        Song song = new Song();
        song.setTitle("Heathens");
        song.setSingerName("TwentyOnePilots");
        return song;
    }

    @Primary
    @Bean(name = "song2")
    Song song2() {
        Song song = new Song();
        song.setTitle("HotelCalifornia");
        song.setSingerName("Eagles");
        return song;
    }
}
