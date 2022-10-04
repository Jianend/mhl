package com.jianen.service;

import com.jianen.mapper.mapper;

import com.jianen.model.mhl;
import com.jianen.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

public class EmployeeService {
   static SqlSession   sqlSession1 = MyBatisUtils.getSqlSession();
    public  boolean lonin(mhl mhl){
        mapper mapper = sqlSession1.getMapper(mapper.class);
        Object select = mapper.select(mhl);
        return select != null;
    }
}
