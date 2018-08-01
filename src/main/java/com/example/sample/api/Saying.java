package com.example.sample.api;

import org.hibernate.validator.constraints.Length;

public class Saying {
    private long id;

    @Length(max =3)
    private String content;

    public Saying(){

    }

    public Saying(Long id, String content){
        this.id = id;
        this.content = content;
    }

    public long getId(){
        return id;
    }

    public String getContent(){
        return content;
    }
}
