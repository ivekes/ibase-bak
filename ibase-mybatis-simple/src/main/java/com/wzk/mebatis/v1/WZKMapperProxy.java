package com.wzk.mebatis.v1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class WZKMapperProxy implements InvocationHandler {

    private WZKSqlSession sqlSession;

    public WZKMapperProxy(WZKSqlSession sqlSession){
        this.sqlSession = sqlSession;
    }
    //通过
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String mapperInterface = method.getDeclaringClass().getName();
        String methodName = method.getName();
        String statementId = mapperInterface + methodName;
        return sqlSession.selectOne(statementId,args[0]);
    }
}
