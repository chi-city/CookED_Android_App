package com.example.cooked_app.Model;

import java.io.Serializable;

public class Course_Model implements Serializable {
    private String name;
    private String description;
    private int thumbnail;

    private String YT_ID;

    public Course_Model(String name, String description, int thumbnail, String YT_ID) {
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
        this.YT_ID = YT_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getYouTubeID() {
        return YT_ID;
    }

    public void setYouTube_ID(String youTube_ID) {
        this.YT_ID = youTube_ID;
    }
}

