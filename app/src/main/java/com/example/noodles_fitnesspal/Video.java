package com.example.noodles_fitnesspal;

public class Video {

    String name, videourl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public Video() {
    }

    public Video(String name, String videourl) {
        this.name = name;
        this.videourl = videourl;
    }
}