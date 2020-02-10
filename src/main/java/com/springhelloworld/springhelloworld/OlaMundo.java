package com.springhelloworld.springhelloworld;

public class OlaMundo {

    private long id;
    private String content;

    public OlaMundo(long id, String content){
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
