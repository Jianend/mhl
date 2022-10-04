package com.jianen.Interface;

import com.jianen.model.DiningTable;
import com.jianen.model.bill;
import com.jianen.model.menu;
import com.jianen.model.mhl;
import com.jianen.service.DiningService;
import com.jianen.service.EmployeeService;
import com.jianen.service.billService;
import com.jianen.service.menuService;
import com.jianen.util.Utility;

import java.util.Date;


public class MhlVlew {
    public static void main(String[] args) {

      new MhlVlew().mainMenu();
    }

    private static boolean loop = true;//控制是否退出

    /**
     * 显示主菜单
     */
    public  void mainMenu() {
        while (loop) {
            System.out.println("=================满汉楼=================");
            System.out.println("\t\t\t 1. 登录");
            System.out.println("\t\t\t 2 退出");
            System.out.println("=================欢迎你=================");
            System.out.println("请输入你的选择");
            String logining = Utility.readString(1);
            switch (logining) {
                case "1":
                    System.out.println("请输入员工号:");
                    String id = Utility.readString(50);
                    System.out.println("请输入密码:");
                    String pwd = Utility.readString(50);
                    EmployeeService es  = new EmployeeService();
                    boolean login = es.lonin(new mhl(id, pwd));
                    // 到数据库判断
                    if (login) {
                        System.out.println("登录成功");
                        System.out.println("登录时间"+new Date());
                        //显示二级菜单
                        while (loop) {
                            erjd();
                            String key = Utility.readString(1);
                            switch (key) {
                                case "1":
                                    listDingTable();//显示餐桌
                                    break;
                                case "2":
                                    System.out.println("预定餐桌");
                                    ydDingTable();
                                    break;
                                case "3":
                                    System.out.println("显示所有菜品");
                                    for (menu menu : menuService.selectall()) {
                                        System.out.println(menu);
                                    }
                                    break;
                                case "4":
                                    dcfw();
                                    break;
                                case "5":
                                    System.out.println("查看账单");
                                    ckzd();
                                    break;
                                case "6":
                                    System.out.println("结账");
                                    jiezhang();
                                    break;
                                case "9":
                                    loop = false;//退出系统
                                    break;
                                default:
                                    System.out.println("你的输入有错误+");
                            }
                        }
                    } else {
                        System.out.println("登录失败");
                    }
                    break;
                case "2":
                    loop = false;//退出系统
                    break;
                default:
                    System.out.println("输入出错 重新输入");
            }
        }
        System.out.println("退出满汉楼");

    }

    /**
     * 显示二级菜单
     */
    public static void erjd() {
        System.out.println("==============二级菜单==============");
        System.out.println("\t\t 1 显示餐桌状态");
        System.out.println("\t\t 2 预定餐桌");
        System.out.println("\t\t 3 显示所有菜品");
        System.out.println("\t\t 4 点餐服务");
        System.out.println("\t\t 5 查看菜单");
        System.out.println("\t\t 6 结账");
        System.out.println("\t\t 9 退出满汉楼");
        System.out.println("==============二级菜单==============");
        System.out.println("请输入你的选择");

    }
    public void listDingTable(){
        System.out.println("餐桌编号\t 餐桌状态");
        for (DiningTable diningTable : DiningService.Dining()) {
            System.out.println(diningTable.xx());
        }
    }

    /**
     * 预定餐桌
     */
    public void ydDingTable(){
        System.out.println("请输入预定的餐桌编号");
        int i = Utility.readInt(1);
//        if (i==-1)
        if (DiningService.Diningbyid(i)) {
            System.out.println("预定人名字");
            String name = Utility.readString(20);
            System.out.println("预定人电话");
            String dh = Utility.readString(20);
            DiningTable diningTable = new DiningTable(i, "预定成功", name, dh);
            DiningService.DiningState(diningTable);
        }else {
            System.out.println("该餐桌已经有人使用了");
        }
    }
    /**
     * 点餐
     */
    public void dcfw(){

        System.out.println("点餐服务");
        System.out.println("选择点餐桌号");
        int i = Utility.readInt(1);
        if (DiningService.DiningByid(i)) {
            while (true) {
                System.out.println("输入菜品编号(-1 退出)：");
                int menu = Utility.readInt(1);
                if (menuService.selectmenuByid(menu)!=null) {
                    System.out.println("菜品数量");
                    int i1 = Utility.readInt();
                    DiningService.DiningUpdate(new DiningTable(i,"点餐成功"));
                    bill bill = new bill(menu, i, "未付款", i1);
                    billService.dc(bill);//生成账单
                }else if (menu==-1){
                    break;
                } else {
                    System.out.println("没有这个菜品");
                }
            }
        }else {
            System.out.println("没有这个桌号");
        }

    }
    /**
     * 查看账单
     */
    public void ckzd(){
        int zj=0;
        System.out.println("请输入桌号");
        int i = Utility.readInt();
        if (DiningService.DiningByid(i)) {
            for (bill bill : billService.zdck(i)) {
                System.out.println(bill);
                zj+=bill.getMoney()*bill.getNums();
            }
            System.out.println("总价为："+zj);
        }



    }
    /**
     * 结账
     */
    public void jiezhang(){
        System.out.println("请输入结账桌号");
        int i = Utility.readInt();
        if (!DiningService.Diningbyid(i)) {
            System.out.println("请输入结账方式 微信/支付宝/现金");
            String s = Utility.readString(3);
            System.out.println("是否结账:y|n");
            char c = Utility.readChar();
            if (c=='y'){
                for (bill bill : billService.zdck(i)) {
                    bill.setState(s);
                    billService.jiezdhang(bill);
                }
                DiningService.DiningState(new DiningTable(i,"空","",""));

            }else {
                System.out.println("已取消结账");
            }

        }
    }



}
