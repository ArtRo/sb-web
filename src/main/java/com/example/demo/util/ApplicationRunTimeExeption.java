package com.example.demo.util;

public class ApplicationRunTimeExeption extends RuntimeException {

    InfoCode infoCode;

    public ApplicationRunTimeExeption(){}

    public ApplicationRunTimeExeption(InfoCode infoCode){
        this.infoCode = infoCode;
    }

    public InfoCode getInfoCode() {
        return infoCode;
    }

    public void setInfoCode(InfoCode infoCode) {
        this.infoCode = infoCode;
    }
}
