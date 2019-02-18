package com.fly.jks.mapper.provider;

import com.fly.jks.domain.Factory;
import com.fly.jks.pagination.Page;
import org.apache.ibatis.jdbc.SQL;

/**
 * 生产厂家Mapper接口 动态sql生产器
 * @author liang
 * @date 2019/2/18 - 12:01
 */
public class FactoryMapperDynaSQLCreater {

    public String insertSQL(){
        return new SQL(){{
            INSERT_INTO("factory");
            VALUES("factory_id, full_name, factory_name, contacts, phone, mobile, fax, cnote, inspector, order_no, order_by, create_dep, create_time",
                    "#{factory_id}, #{full_name}, #{factory_name}, #{contacts}, #{phone}, #{mobile}, #{fax}, #{cnote}, #{inspector}, #{order_no}, #{order_by}, #{create_dep}, #{create_time}");
        }}.toString();
    }
}
