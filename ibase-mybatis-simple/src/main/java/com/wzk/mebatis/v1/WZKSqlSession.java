package com.wzk.mebatis.v1;

import com.wzk.mebatis.v1.WZKConfiguration;
import com.wzk.mebatis.v1.WZKExecutor;

public class WZKSqlSession {
    private WZKConfiguration configuration; //sqlMapping 资源类
    private WZKExecutor wzkExecutor;        //执行器

    /**
     *
     * @param statementId   key
     * @param paramater     参数
     * @param <T>
     * @return
     */
    public <T> T selectOne(String statementId,Object paramater){
        String sql = WZKConfiguration.sqlMappings.getString(statementId);
        if(sql != null && !"".equals(sql)){
            return wzkExecutor.query(sql,paramater);
        }
        return null;
    }
    //获取 mapper 类对象
    public <T> T getMapper(Class clazz){
        return configuration.getMapper(clazz,this);
    }
}
