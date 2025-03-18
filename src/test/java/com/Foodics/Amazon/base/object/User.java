package com.Foodics.Amazon.base.object;


public class User {
    private  String name;
    private  String job;

    public User(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public  String getName() {
        return name;
    }

    public  String getJob() {
        return job;
    }

    // You can add setters if needed for more complex scenarios
}
