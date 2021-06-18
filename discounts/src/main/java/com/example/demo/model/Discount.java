package com.example.demo.model;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class Discount implements Serializable {

    private String title;
    private String price;
    private String description;
}
