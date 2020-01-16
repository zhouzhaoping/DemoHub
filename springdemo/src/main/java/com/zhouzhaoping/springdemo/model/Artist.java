package com.zhouzhaoping.springdemo.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artist {
    private String Name;
    private List<String> Members;
    private String Origin;
    private boolean isSolo;
    public boolean isFrom(String where) {
        return Origin.equals(where);
    }
}
