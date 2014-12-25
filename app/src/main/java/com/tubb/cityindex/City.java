package com.tubb.cityindex;

public class City implements Comparable{

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
    public int compareTo(Object another) {
        if(another == null || !(another instanceof City)){
            return -1;
        }
        City other = (City)another;
        return id.compareTo(other.getId());
    }
}
