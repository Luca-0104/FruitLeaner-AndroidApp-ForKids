package com.example.assignment2_fruits;

public class Fruit {

    private String name;        //name of the fruit
    private int picID;       //the id of the picture of this fruit
    private String firstLetter; //the capitalized first letter of the fruit name

    //the constructor
    public Fruit(String name, int picID){
        this.name = name;
        this.picID = picID;
        this.firstLetter = name.substring(0, 1).toUpperCase();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPicID() {
        return picID;
    }

    public void setPicID(int picID) {
        this.picID = picID;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }
}
