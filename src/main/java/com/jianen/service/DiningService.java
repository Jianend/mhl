package com.jianen.service;

import com.jianen.mapper.mapper;
import com.jianen.model.DiningTable;
import com.jianen.model.mhl;
import com.jianen.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class DiningService {
    static SqlSession sqlSession1 = MyBatisUtils.getSqlSession();

    public static List<DiningTable> Dining() {
        mapper mapper = sqlSession1.getMapper(mapper.class);
        List<DiningTable> diningTables = mapper.selectDining();
        return diningTables;

    }

    public static boolean Diningbyid(Integer id) {
        mapper mapper = sqlSession1.getMapper(mapper.class);
        DiningTable diningTable = mapper.selectDiningByid(id);
        if (diningTable != null)
            if (diningTable.getState().equals("Пе")) {
                return true;
            }
        return false;
    }



    public static boolean DiningByid(Integer id) {
        mapper mapper = sqlSession1.getMapper(mapper.class);
        DiningTable diningTable = mapper.selectDiningByid(id);
        if (diningTable == null)return false;
            return true;
    }


    public static void DiningState(DiningTable diningTable) {
        mapper mapper = sqlSession1.getMapper(mapper.class);
        mapper.updateDining(diningTable);

    }
    public static void DiningUpdate(DiningTable diningTable){
        mapper mapper = sqlSession1.getMapper(mapper.class);
        mapper.updateDiningByid(diningTable);
    }



}
