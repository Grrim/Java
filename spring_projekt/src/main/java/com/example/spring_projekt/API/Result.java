package com.example.spring_projekt.API;


public class Result {
    private int status;

    public int getStatus() {
        return status;
    }

    public Result(int status) {
        this.status = status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
