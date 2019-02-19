package com.fly.jks.mapper.provider;

import com.fly.jks.domain.Factory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * 生产厂家Mapper接口 动态sql生产器
 * @author liang
 * @date 2019/2/18 - 12:01
 */
public class FactoryMapperDynaSQLCreater {

    /**
     * 插入
     * @param factory
     * @return
     */
    public String insertSQL(Factory factory){
        return new SQL(){{
            INSERT_INTO("factory");
            VALUES("factory_id, full_name, factory_name, contacts, phone, mobile, fax, cnote, " +
                            "inspector, order_no, order_by, create_dept, create_time",
                    "#{factory_id}, #{full_name}, #{factory_name}, #{contacts}, #{phone}, #{mobile}, " +
                            "#{fax}, #{cnote}, #{inspector}, #{order_no}, #{order_by}, #{create_dept}, #{create_time}");
        }}.toString();
    }

    /**
     * 更新
     * @param factory
     * @return
     */
    public String updateSQL(Factory factory){
        return new SQL(){{
            UPDATE("factory");
            if (factory.getFull_name()!=null && !"".equals(factory.getFull_name())){
                SET("full_name = #{full_name}");
            }
            if (factory.getFactory_name()!=null && !"".equals(factory.getFactory_name())){
                SET("factory_name = #{factory_name}");
            }
            if (factory.getContacts()!=null && !"".equals(factory.getContacts())){
                SET("contacts = #{contacts}");
            }
            if (factory.getPhone()!=null && !"".equals(factory.getPhone())){
                SET("phone = #{phone}");
            }
            if (factory.getMobile()!=null && !"".equals(factory.getMobile())){
                SET("mobile = #{mobile}");
            }
            if (factory.getFax()!=null && !"".equals(factory.getFax())){
                SET("fax = #{fax}");
            }
            if (factory.getCnote()!=null && !"".equals(factory.getCnote())){
                SET("cnote = #{cnote}");
            }
            if (factory.getInspector()!=null && !"".equals(factory.getInspector())){
                SET("inspector = #{inspector}");
            }
            WHERE("factory_id = #{factory_id}");
        }}.toString();
    }

    /**
     * 批量删除 sql 是在业务层通过数组拼接好的
     * @return
     */
    public String deleteSQL(String sql){
        return new String("DELETE FROM factory WHERE factory_id IN ("+sql+")");
    }
}
