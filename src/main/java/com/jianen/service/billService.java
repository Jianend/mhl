package com.jianen.service;

import com.jianen.mapper.mapper;
import com.jianen.model.bill;
import com.jianen.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class billService {
    static SqlSession sqlSession1 = MyBatisUtils.getSqlSession();
    public static void dc(bill bill){
        mapper mapper = sqlSession1.getMapper(mapper.class);
        mapper.insertbill(bill);
        sqlSession1.commit();
    }
    public static List<bill> zdck(int diningTableId){
        bill bill = new bill();
        bill.setDiningTableId(diningTableId);
        bill.setState("Î´¸¶¿î");
        mapper mapper = sqlSession1.getMapper(mapper.class);
        List<bill> selectbill = mapper.selectbill(bill);
        return selectbill;
    }
    public static void jiezdhang(bill bill){
        mapper mapper = sqlSession1.getMapper(mapper.class);
        mapper.updatebill(bill);
    }
}
