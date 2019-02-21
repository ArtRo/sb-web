package com.example.demo.bo;

import com.example.demo.validator.NotNull;

/**
 * Created by dhf_ndm on 2019/2/21
 * the previous bug derived from the previous
 */
public class Mobile {

    @NotNull
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
