package com.tubb.cityindex;

import android.support.annotation.NonNull;

public class City implements Comparable<City> {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(@NonNull City another) {
        if(another == null){
            return -1;
        }
        return id.compareTo(another.getId());
    }
}
