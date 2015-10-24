package com.kenodoggy.masterdetailflow.model;

/**
 *
 */
public class Puppy {
    public String title;
    public String image;
    public String description;

    public Puppy(String title, String image, String description) {
        this.title = title;
        this.image = image;
        this.description = description;
    }

    public String getTitle() {
        return this.title;
    }

    public String getImage() {
        return this.image;
    }

    public String getDescription() {
        return this.description;
    }
}
