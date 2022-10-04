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

    private static boolean loop = true;//�����Ƿ��˳�

    /**
     * ��ʾ���˵�
     */
    public  void mainMenu() {
        while (loop) {
            System.out.println("=================����¥=================");
            System.out.println("\t\t\t 1. ��¼");
            System.out.println("\t\t\t 2 �˳�");
            System.out.println("=================��ӭ��=================");
            System.out.println("���������ѡ��");
            String logining = Utility.readString(1);
            switch (logining) {
                case "1":
                    System.out.println("������Ա����:");
                    String id = Utility.readString(50);
                    System.out.println("����������:");
                    String pwd = Utility.readString(50);
                    EmployeeService es  = new EmployeeService();
                    boolean login = es.lonin(new mhl(id, pwd));
                    // �����ݿ��ж�
                    if (login) {
                        System.out.println("��¼�ɹ�");
                        System.out.println("��¼ʱ��"+new Date());
                        //��ʾ�����˵�
                        while (loop) {
                            erjd();
                            String key = Utility.readString(1);
                            switch (key) {
                                case "1":
                                    listDingTable();//��ʾ����
                                    break;
                                case "2":
                                    System.out.println("Ԥ������");
                                    ydDingTable();
                                    break;
                                case "3":
                                    System.out.println("��ʾ���в�Ʒ");
                                    for (menu menu : menuService.selectall()) {
                                        System.out.println(menu);
                                    }
                                    break;
                                case "4":
                                    dcfw();
                                    break;
                                case "5":
                                    System.out.println("�鿴�˵�");
                                    ckzd();
                                    break;
                                case "6":
                                    System.out.println("����");
                                    jiezhang();
                                    break;
                                case "9":
                                    loop = false;//�˳�ϵͳ
                                    break;
                                default:
                                    System.out.println("��������д���+");
                            }
                        }
                    } else {
                        System.out.println("��¼ʧ��");
                    }
                    break;
                case "2":
                    loop = false;//�˳�ϵͳ
                    break;
                default:
                    System.out.println("������� ��������");
            }
        }
        System.out.println("�˳�����¥");

    }

    /**
     * ��ʾ�����˵�
     */
    public static void erjd() {
        System.out.println("==============�����˵�==============");
        System.out.println("\t\t 1 ��ʾ����״̬");
        System.out.println("\t\t 2 Ԥ������");
        System.out.println("\t\t 3 ��ʾ���в�Ʒ");
        System.out.println("\t\t 4 ��ͷ���");
        System.out.println("\t\t 5 �鿴�˵�");
        System.out.println("\t\t 6 ����");
        System.out.println("\t\t 9 �˳�����¥");
        System.out.println("==============�����˵�==============");
        System.out.println("���������ѡ��");

    }
    public void listDingTable(){
        System.out.println("�������\t ����״̬");
        for (DiningTable diningTable : DiningService.Dining()) {
            System.out.println(diningTable.xx());
        }
    }

    /**
     * Ԥ������
     */
    public void ydDingTable(){
        System.out.println("������Ԥ���Ĳ������");
        int i = Utility.readInt(1);
//        if (i==-1)
        if (DiningService.Diningbyid(i)) {
            System.out.println("Ԥ��������");
            String name = Utility.readString(20);
            System.out.println("Ԥ���˵绰");
            String dh = Utility.readString(20);
            DiningTable diningTable = new DiningTable(i, "Ԥ���ɹ�", name, dh);
            DiningService.DiningState(diningTable);
        }else {
            System.out.println("�ò����Ѿ�����ʹ����");
        }
    }
    /**
     * ���
     */
    public void dcfw(){

        System.out.println("��ͷ���");
        System.out.println("ѡ��������");
        int i = Utility.readInt(1);
        if (DiningService.DiningByid(i)) {
            while (true) {
                System.out.println("�����Ʒ���(-1 �˳�)��");
                int menu = Utility.readInt(1);
                if (menuService.selectmenuByid(menu)!=null) {
                    System.out.println("��Ʒ����");
                    int i1 = Utility.readInt();
                    DiningService.DiningUpdate(new DiningTable(i,"��ͳɹ�"));
                    bill bill = new bill(menu, i, "δ����", i1);
                    billService.dc(bill);//�����˵�
                }else if (menu==-1){
                    break;
                } else {
                    System.out.println("û�������Ʒ");
                }
            }
        }else {
            System.out.println("û���������");
        }

    }
    /**
     * �鿴�˵�
     */
    public void ckzd(){
        int zj=0;
        System.out.println("����������");
        int i = Utility.readInt();
        if (DiningService.DiningByid(i)) {
            for (bill bill : billService.zdck(i)) {
                System.out.println(bill);
                zj+=bill.getMoney()*bill.getNums();
            }
            System.out.println("�ܼ�Ϊ��"+zj);
        }



    }
    /**
     * ����
     */
    public void jiezhang(){
        System.out.println("�������������");
        int i = Utility.readInt();
        if (!DiningService.Diningbyid(i)) {
            System.out.println("��������˷�ʽ ΢��/֧����/�ֽ�");
            String s = Utility.readString(3);
            System.out.println("�Ƿ����:y|n");
            char c = Utility.readChar();
            if (c=='y'){
                for (bill bill : billService.zdck(i)) {
                    bill.setState(s);
                    billService.jiezdhang(bill);
                }
                DiningService.DiningState(new DiningTable(i,"��","",""));

            }else {
                System.out.println("��ȡ������");
            }

        }
    }



}
