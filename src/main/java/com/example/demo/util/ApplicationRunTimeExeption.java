package com.example.demo.util;

import javax.sound.sampled.Line;

public class ApplicationRunTimeExeption extends RuntimeException {

    InfoCode infoCode;

    public ApplicationRunTimeExeption(){}

    public ApplicationRunTimeExeption(InfoCode infoCode){
        this.infoCode = infoCode;
    }

    public ApplicationRunTimeExeption(String msg){
        infoCode = InfoCode.PARAMETER_ERROR;
        infoCode.setDesc(msg);
    }

    public InfoCode getInfoCode() {
        return infoCode;
    }

    public void setInfoCode(InfoCode infoCode) {
        this.infoCode = infoCode;
    }
}
