package com.example.premium;

public class Message {

    private Object message;

    public Message(){}

    public Message(Object message){
        this.message = message;
    }

    public Object getMessage(){
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
