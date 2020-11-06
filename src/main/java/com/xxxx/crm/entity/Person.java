package com.xxxx.crm.entity;

import lombok.Data;

import java.util.List;

@Data
public class Person {
    private Integer personId;
    private String personName;

    private List<Car> carList;
}
