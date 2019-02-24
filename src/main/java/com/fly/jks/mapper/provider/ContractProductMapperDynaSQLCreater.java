package com.fly.jks.mapper.provider;

import com.fly.jks.domain.ContractProduct;
import org.apache.ibatis.jdbc.SQL;

/**
 *货物动态sql生成类
 * @author liang
 * @date 2019/2/24 - 0:44
 */
public class ContractProductMapperDynaSQLCreater {

    /**
     * 真删除1条/批量删除
     */
    public String deleteSQL(String sql){
        return new String("DELETE FROM contract_product WHERE contract_product_id IN ("+sql+")");
    }

    /**
     * 批量/单 出货状态 private String finshed;//是否出货完毕1未完 0完毕
     * 设置成0==完毕
     */
    public String updateStopStateSQL(String sql){
        return new String("UPDATE contract_product SET finshed = '0' WHERE contract_product_id IN ("+sql+")");
    }

    /**
     * 批量/单 出货状态 private String finshed;//是否出货完毕1未完 0完毕
     * 设置成1==未完
     */
    public String updateStartStateSQL(String sql){
        return new String("UPDATE contract_product SET finshed = '1' WHERE contract_product_id IN ("+sql+")");
    }

    /**
     * 新增一条动态sql
     * @return
     */
    public String insertSQL(){
        return new SQL(){{
            INSERT_INTO("contract_product");
            VALUES("contract_product_id, factory_id, contract_id, factory_name, product_name, product_num, product_image, product_desc, cnumber, loading_rate, packing_unit, price, amount, out_num, finshed, gross_weight, net_weight, csize, size_length, size_width, size_higt, product_request, cunit, box_num, ex_price, ex_amount, ex_unit, no_tax, tax, cost_price, cost_tax, accessories, order_no",
                    "#{contract_product_id}, #{factory_id}, #{contract_id}, #{factory_name}, #{product_name}, #{product_num}, #{product_image}, #{product_desc}, #{cnumber}, #{loading_rate}, #{packing_unit}, #{price}, #{amount}, #{out_num}, #{finshed}, #{gross_weight}, #{net_weight}, #{csize}, #{size_length}, #{size_width}, #{size_higt}, #{product_request}, #{cunit}, #{box_num}, #{ex_price}, #{ex_amount}, #{ex_unit}, #{no_tax}, #{tax}, #{cost_price}, #{cost_tax}, #{accessories}, #{order_no}");
        }}.toString();
    }

    public String updateSQL(ContractProduct contractProduct){
        return new SQL(){{
            UPDATE("contract_product");
            if (contractProduct.getFactory_id()!=null){
                SET("factory_id = #{factory_id}");
            }
            if (contractProduct.getContract_id()!=null){
                SET("contract_id = #{contract_id}");
            }
            if (contractProduct.getFactory_name()!=null){
                SET("factory_name #{factory_name}");
            }
            if (contractProduct.getProduct_name()!=null){
                SET("product_name = #{product_name}");
            }
            if (contractProduct.getProduct_num()!=null){
                SET("product_num = #{product_num}");
            }
            if (contractProduct.getProduct_image()!=null){
                SET("product_image = #{product_image}");
            }
            if (contractProduct.getProduct_desc()!=null){
                SET("product_desc = #{product_desc}");
            }
            if (contractProduct.getCnumber()!=null){
                SET("cnumber = #{cnumber}");
            }
            if (contractProduct.getLoading_rate()!=null){
                SET("loading_rate = #{loading_rate}");
            }
            if (contractProduct.getPacking_unit()!=null){
                SET("packing_unit = #{packing_unit}");
            }
            if (contractProduct.getPrice()!=null){
                SET("price= #{price}");
            }
            if (contractProduct.getAmount()!=null){
                SET("amount = #{amount}");
            }
            if (contractProduct.getOut_num()!=null){
                SET("out_num = #{out_num}");
            }
            if (contractProduct.getFinshed()!=null){
                SET("finshed = #{finshed}");
            }
            if (contractProduct.getGross_weight()!=null){
                SET("gross_weight = {gross_weight}");
            }
            if (contractProduct.getNet_weight()!=null){
                SET("net_weight = #{net_weight}");
            }
            if (contractProduct.getCsize()!=null){
                SET("csize = #{csize}");
            }
            if (contractProduct.getSize_length()!=null){
                SET("size_length = #{size_length}");
            }
            if (contractProduct.getSize_width() !=null){
                SET("size_width = #{size_width}");
            }
            if (contractProduct.getSize_higt()!=null){
                SET("size_higt = ${size_higt}");
            }
            if (contractProduct.getProduct_request()!=null){
                SET("product_request = #{product_request}");
            }
            if (contractProduct.getCunit()!=null){
                SET("cunit = #{cunit}");
            }
            if (contractProduct.getBox_num()!=null){
                SET("box_num = #{box_num}");
            }
            if (contractProduct.getEx_price()!=null){
                SET("ex_price = #{ex_price}");
            }
            if (contractProduct.getEx_amount()!=null){
                SET("ex_amount = #{ex_amount}");
            }
            if (contractProduct.getEx_unit()!=null){
                SET("ex_unit = #{ex_unit}");
            }
            if (contractProduct.getNo_tax()!=null){
                SET("no_tax = #{no_tax}");
            }
            if (contractProduct.getTax()!=null){
                SET("tax = #{tax}");
            }
            if (contractProduct.getCost_price()!=null){
                SET("cost_price = #{cost_price}");
            }
            if (contractProduct.getCost_tax()!=null){
                SET("cost_tax = #{cost_tax}");
            }
            if (contractProduct.getAccessories()!=null){
                SET("{accessories},  = #{{accessories}, }");
            }
            if (contractProduct.getOrder_no()!=null){
                SET("order_no = #{order_no}");//测试驼峰命名字段
            }
            WHERE("contract_product_id = #{contract_product_id}");
        }}.toString();

    }

}
