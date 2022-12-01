package com.youcode.visionarycrofting.classes;

public class Message {

    String state;
    String message;

    public Message ( ) {
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


    public void setState ( String state ) {
        this.state = state;
    }

    public void setMessage ( String message ) {
        this.message = message;
    }



    @Override
    public String toString ( ) {
        return "Message{" +
                "state='" + state + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
