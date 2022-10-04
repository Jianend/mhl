package com.jianen.model;

public class menu {
    private int id;
    private String name;
    private String type;
    private int price;
    private int nums;

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public menu( int id ,int nums) {
        this.id = id;
        this.nums=nums;


    }

    public menu(int id, String name, String type, int price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public menu() {
    }

    @Override
    public String toString() {
        return  "菜品编号：'"+id+'\''+
                ", 菜名：'" + name + '\'' +
                ", 类型：'" + type + '\'' +
                ", 价格：'" + price + '\''
                ;
    }
}
