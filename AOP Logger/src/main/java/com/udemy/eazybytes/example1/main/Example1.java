package com.udemy.eazybytes.example1.main;

import com.udemy.eazybytes.example1.bean.Person;
import com.udemy.eazybytes.example1.config.ProjectConfig;
import com.udemy.eazybytes.example1.model.Song;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example1 {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        Person person = context.getBean(Person.class);
        Song song = context.getBean(Song.class);


        person.getVehicle().getVehicleServices().playMusic(true, song);
        person.getVehicle().getVehicleServices().moveVehicle(true);
        person.getVehicle().getVehicleServices().stopVehicle();


    }
}
