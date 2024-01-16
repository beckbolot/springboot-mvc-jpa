package com.beck.springbootmvcjpa.entity;

public enum ProductTypeEnum{
    PC("Computer"),LAPTOP("Notebook"),PHONE("Smartphone"),TABLET("Tablet");

    private String name;

    ProductTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
