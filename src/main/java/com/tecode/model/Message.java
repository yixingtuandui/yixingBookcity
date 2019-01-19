package com.tecode.model;
import org.springframework.stereotype.Component;

@Component
public class Message {
    private boolean status;
    private String masg;


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMasg() {
        return masg;
    }

    public void setMasg(String masg) {
        this.masg = masg;
    }
}