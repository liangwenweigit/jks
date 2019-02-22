package com.fly.jks.mapper.provider;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author liang
 * @date 2019/2/22 - 15:20
 */
public class ContractMapperDynaSQLCreater {
    /**
     * 新增一条动态sql
     * @return
     */
    public String insertContractSQL(){
        return new SQL(){{
            INSERT_INTO("contract");
            VALUES("contract_id, offeror, contract_no, signing_date, input_by, check_by, inspector, total_price, crequest, customer_name, ship_date, import_num, instructions, print_style, old_state, contract_state, out_state, delivery_date, trade_clause, create_by, create_bept, create_time",
                    "#{contract_id}, #{offeror}, #{contract_no}, #{signing_date}, #{input_by}, #{check_by}, #{inspector}, #{total_price}, #{crequest}, #{customer_name}, #{ship_date}, #{import_num}, #{instructions}, #{print_style}, #{old_state}, #{contract_state}, #{out_state}, #{delivery_date}, #{trade_clause}, #{create_by}, #{create_bept}, #{create_time}");
        }}.toString();
    }
}
