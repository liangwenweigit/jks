package com.fly.jks.mapper.provider;

import org.apache.ibatis.jdbc.SQL;

/**
 *货物动态sql生成类
 * @author liang
 * @date 2019/2/24 - 0:44
 */
public class ContractProductMapperDynaSQLCreater {

    /**
     * 新增一条动态sql
     * @return
     */
    public String insertSQL(){
        return new SQL(){{
            INSERT_INTO("contract_product");
            VALUES("contract_product_id, factory_id, contract_id, factory_name, product_name, product_num, product_image, product_desc, cnumber, loading_rate, packing_unit, price, amount, out_num, finshed, gross_weight, net_weight, csize, size_length, size_width, size_weight, product_request, cunit, box_num, ex_price, ex_amount, ex_unit, no_tax, tax, cost_price, cost_tax, accessories, order_no",
                    "#{contract_product_id}, #{factory_id}, #{contract_id}, #{factory_name}, #{product_name}, #{product_num}, #{product_image}, #{product_desc}, #{cnumber}, #{loading_rate}, #{packing_unit}, #{price}, #{amount}, #{out_num}, #{finshed}, #{gross_weight}, #{net_weight}, #{csize}, #{size_length}, #{size_width}, #{size_weight}, #{product_request}, #{cunit}, #{box_num}, #{ex_price}, #{ex_amount}, #{ex_unit}, #{no_tax}, #{tax}, #{cost_price}, #{cost_tax}, #{accessories}, #{order_no}");
        }}.toString();
    }

}
