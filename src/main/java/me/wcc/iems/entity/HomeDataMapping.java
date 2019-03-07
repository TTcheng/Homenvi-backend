package me.wcc.iems.entity;

public class HomeDataMapping implements Mapping {
    private String name;
    private Object value;

    public HomeDataMapping(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getKey() {
        return name;
    }

    @Override
    public Object getValue() {
        return value;
    }
}
