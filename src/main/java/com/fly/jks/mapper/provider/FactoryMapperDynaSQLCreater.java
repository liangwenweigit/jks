package com.fly.jks.mapper.provider;

import com.fly.jks.domain.Factory;
import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

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
                            "inspector, order_no, order_by, create_dept, create_time, state",
                    "#{factory_id}, #{full_name}, #{factory_name}, #{contacts}, #{phone}, #{mobile}, " +
                            "#{fax}, #{cnote}, #{inspector}, #{order_no}, #{order_by}, #{create_dept}, #{create_time}, #{state}");
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
            if (factory.getFull_name()!=null){
                SET("full_name = #{full_name}");
            }
            if (factory.getFactory_name()!=null){
                SET("factory_name = #{factory_name}");
            }
            if (factory.getContacts()!=null){
                SET("contacts = #{contacts}");
            }
            if (factory.getPhone()!=null){
                SET("phone = #{phone}");
            }
            if (factory.getMobile()!=null){
                SET("mobile = #{mobile}");
            }
            if (factory.getFax()!=null){
                SET("fax = #{fax}");
            }
            if (factory.getCnote()!=null){
                SET("cnote = #{cnote}");
            }
            if (factory.getInspector()!=null){
                SET("inspector = #{inspector}");
            }
            WHERE("factory_id = #{factory_id}");
        }}.toString();
    }

    /**
     * 真批量删除/删除一个 sql 是在业务层通过数组拼接好的
     * @return
     */
    public String deleteSQL(String sql){
        return new String("DELETE FROM factory WHERE factory_id IN ("+sql+")");
    }

    /**
     *批量/单个停用，类似假删除，业务删除
     * @param sql
     * @return
     */
    public String updateStopStateSQL(String sql){
        return new String("UPDATE factory SET state = '0' WHERE factory_id IN ("+sql+")");
    }

    /**
     *批量/单个启用
     * @param sql
     * @return
     */
    public String updateStartStateSQL(String sql){
        return new String("UPDATE factory SET state = '1' WHERE factory_id IN ("+sql+")");
    }

    public String findByCondition(Map<String, Object> paraMap){
        return new SQL(){{
            SELECT("*");
            FROM("factory");
            if (paraMap.get("state")!=null){
                WHERE("state = #{state}");
            }
        }}.toString();
    }
}
