package com.workintech.zoo.entity;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Animal {

//subclass
    private int id ;
    private String name ;
    private double weight;
    private Gender gender ;
}
