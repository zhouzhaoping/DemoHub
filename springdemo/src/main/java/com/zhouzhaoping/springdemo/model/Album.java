package com.zhouzhaoping.springdemo.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {
    private String Name;
    private List<Track> Tracks;
    private List<String> Musicians;
}
