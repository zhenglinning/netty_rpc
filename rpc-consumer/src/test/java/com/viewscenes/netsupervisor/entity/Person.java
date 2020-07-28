package com.viewscenes.netsupervisor.entity;

import org.springframework.stereotype.Component;

@Component
public class Person {

    private Cat cat;

    public void setCat(Cat cat) {
        this.cat = cat;
    }
}
