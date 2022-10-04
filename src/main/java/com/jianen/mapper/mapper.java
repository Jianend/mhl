package com.jianen.mapper;

import com.jianen.model.DiningTable;
import com.jianen.model.bill;
import com.jianen.model.menu;
import com.jianen.model.mhl;

import java.util.List;

public interface mapper {
    Object select(mhl a);
    List<DiningTable> selectDining();
    DiningTable selectDiningByid(Integer id);
    void updateDining(DiningTable diningTable);
    void updateDiningByid(DiningTable diningTable);
    List<menu> selectmenu();


    List<bill> selectbill(bill bill);

    void updatebill(bill bill);

    menu selectMenuByid(int id);
    void insertbill(bill bill);


}
