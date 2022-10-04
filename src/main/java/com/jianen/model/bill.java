package com.jianen.model;

import com.jianen.service.menuService;

import java.util.Date;

public class bill {


    String id;
    int billId;
    int menuId;
    int money;
    int diningTableId;
    Date billDate;
    String state;
    int nums;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getDiningTableId() {
        return diningTableId;
    }

    public void setDiningTableId(int diningTableId) {
        this.diningTableId = diningTableId;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public bill() {
    }

    public bill( int menuId, int diningTableId, String state, int nums) {
        this.billId = (int)System.currentTimeMillis();
        this.menuId = menuId;
        this.diningTableId = diningTableId;
        this.billDate = new Date();
        this.state = state;
        this.nums = nums;
        this.money= menuService.selectmenuByid(menuId).getPrice();

    }

    public bill(String id, int menuId, int money, int diningTableId, Date billDate, String state, int nums) {
        this.id = id;
        this.billId = (int)System.currentTimeMillis();;
        this.menuId = menuId;
        this.money = money;
        this.diningTableId = diningTableId;
        this.billDate = billDate;
        this.state = state;
        this.nums = nums;
    }

    @Override
    public String toString() {
        return
                ", 单号=" + billId +
                ", 菜品：" + menuService.selectmenuByid(menuId).getName()+
                ", 单价：'" + money + '\'' +
                ", 桌号" + diningTableId +
                ", 时间" + billDate +
                ", 状态：'" + state + '\'' +
                ", 份数：" + nums
                ;
    }
}
