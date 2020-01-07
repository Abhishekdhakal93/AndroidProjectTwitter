package com.shresthagaurav.androidprojecttwitter.model;

public class TweetM {
    String headingtext;
    String messagetext;
    String userimage;
    String messageimage;

    public TweetM(String headingtext, String messagetext, String userimage, String messageimage) {
        this.headingtext = headingtext;
        this.messagetext = messagetext;
        this.userimage = userimage;
        this.messageimage = messageimage;
    }

    public String getHeadingtext() {
        return headingtext;
    }

    public String getMessagetext() {
        return messagetext;
    }

    public String getUserimage() {
        return userimage;
    }

    public String getMessageimage() {
        return messageimage;
    }
}
