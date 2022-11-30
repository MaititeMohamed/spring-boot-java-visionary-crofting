package com.youcode.visionarycrofting.classes;

public class Message {

    String state;
    String message;
    Object obj;

    public Message ( ) {
    }

    public Message ( String state , String message , Object obj ) {
        this.state = state;
        this.message = message;
        this.obj = obj;
    }

    public Message ( String state , String message ) {
        this.state = state;
        this.message = message;
    }

    public String getState ( ) {
        return state;
    }

    public String getMessage ( ) {
        return message;
    }

    public Object getObj ( ) {
        return obj;
    }

    public void setState ( String state ) {
        this.state = state;
    }

    public void setMessage ( String message ) {
        this.message = message;
    }

    public void setObj ( Object obj ) {
        this.obj = obj;
    }

    @Override
    public String toString ( ) {
        return "Message{" +
                "state='" + state + '\'' +
                ", message='" + message + '\'' +
                ", obj=" + obj +
                '}';
    }
}
