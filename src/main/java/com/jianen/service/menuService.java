package com.jianen.service;

import com.jianen.mapper.mapper;
import com.jianen.model.menu;
import com.jianen.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class menuService {
    static SqlSession sqlSession1 = MyBatisUtils.getSqlSession();
    public static List<menu> selectall(){
        mapper mapper = sqlSession1.getMapper(mapper.class);
        return mapper.selectmenu();
    }
    public static  menu selectmenuByid(int id){
        mapper mapper = sqlSession1.getMapper(mapper.class);
        menu b = mapper.selectMenuByid(id);
        return b;
    }
}
