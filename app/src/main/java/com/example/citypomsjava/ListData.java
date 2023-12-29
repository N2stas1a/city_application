package com.example.citypomsjava;

public class ListData {
    public String name, desc;
    public int image;

    public ListData(String name, String desc, int image) {
        this.name = name;
        this.desc = desc;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public String getDesc() {
        return desc;
    }
}
