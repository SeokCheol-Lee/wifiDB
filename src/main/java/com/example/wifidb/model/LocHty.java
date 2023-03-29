package com.example.wifidb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

@Setter
@Getter
@NoArgsConstructor
public class LocHty {
    private int Id;
    private float x;
    private float y;
    private String date;
}
