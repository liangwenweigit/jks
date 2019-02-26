package com.fly.jks.mapper.provider;

import com.fly.jks.domain.ExtCproduct;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author liang
 * @date 2019/2/26 - 2:48
 */
public class ExtCproductMapperDynaSQLCreater {

    /**
     * 真删除1条/批量删除
     */
    public String deleteSQL(String sql){
        return new String("DELETE FROM ext_cproduct WHERE ext_cproduct_id IN ("+sql+")");
    }

    /**
     * 批量/单 出货状态 private String finshed;//是否出货完毕1未完 0完毕
     * 设置成0==完毕
     */
    public String updateStopStateSQL(String sql){
        return new String("UPDATE ext_cproduct SET finshed = '0' WHERE ext_cproduct_id IN ("+sql+")");
    }

    /**
     * 批量/单 出货状态 private String finshed;//是否出货完毕1未完 0完毕
     * 设置成1==未完
     */
    public String updateStartStateSQL(String sql){
        return new String("UPDATE ext_cproduct SET finshed = '1' WHERE ext_cproduct_id IN ("+sql+")");
    }

    /**
     * 新增一条动态sql
     * @return
     */
    public String insertSQL(){
        return new SQL(){{
            INSERT_INTO("ext_cproduct");
            VALUES("contract_product_id, factory_id, ext_cproduct_id, factory_name, product_name, product_num, product_image, product_desc, cnumber, loading_rate, packing_unit, price, amount, out_num, finshed, gross_weight, net_weight, ctype, size_length, size_width, size_higt, crequest, cunit, box_num, ex_price, ex_amount, ex_unit, no_tax, tax, cost_price, cost_tax, accessories, order_no",
                    "#{contract_product_id}, #{factory_id}, #{ext_cproduct_id}, #{factory_name}, #{product_name}, #{product_num}, #{product_image}, #{product_desc}, #{cnumber}, #{loading_rate}, #{packing_unit}, #{price}, #{amount}, #{out_num}, #{finshed}, #{gross_weight}, #{net_weight}, #{ctype}, #{size_length}, #{size_width}, #{size_higt}, #{crequest}, #{cunit}, #{box_num}, #{ex_price}, #{ex_amount}, #{ex_unit}, #{no_tax}, #{tax}, #{cost_price}, #{cost_tax}, #{accessories}, #{order_no}");
        }}.toString();
    }

    public String updateSQL(ExtCproduct extCproduct){
        return new SQL(){{
            UPDATE("ext_cproduct");
            if (extCproduct.getFactory_id()!=null){
                SET("factory_id = #{factory_id}");
            }
            if (extCproduct.getExt_cproduct_id()!=null){
                SET("ext_cproduct_id = #{ext_cproduct_id}");
            }
            if (extCproduct.getFactory_name()!=null){
                SET("factory_name = #{factory_name}");
            }
            if (extCproduct.getProduct_name()!=null){
                SET("product_name = #{product_name}");
            }
            if (extCproduct.getProduct_num()!=null){
                SET("product_num = #{product_num}");
            }
            if (extCproduct.getProduct_image()!=null){
                SET("product_image = #{product_image}");
            }
            if (extCproduct.getProduct_desc()!=null){
                SET("product_desc = #{product_desc}");
            }
            if (extCproduct.getCnumber()!=null){
                SET("cnumber = #{cnumber}");
            }
            if (extCproduct.getLoading_rate()!=null){
                SET("loading_rate = #{loading_rate}");
            }
            if (extCproduct.getPacking_unit()!=null){
                SET("packing_unit = #{packing_unit}");
            }
            if (extCproduct.getPrice()!=null){
                SET("price= #{price}");
            }
            if (extCproduct.getAmount()!=null){
                SET("amount = #{amount}");
            }
            if (extCproduct.getOut_num()!=null){
                SET("out_num = #{out_num}");
            }
            if (extCproduct.getFinshed()!=null){
                SET("finshed = #{finshed}");
            }
            if (extCproduct.getGross_weight()!=null){
                SET("gross_weight = {gross_weight}");
            }
            if (extCproduct.getNet_weight()!=null){
                SET("net_weight = #{net_weight}");
            }
            if (extCproduct.getCtype()!=null){
                SET("ctype = #{ctype}");
            }
            if (extCproduct.getSize_length()!=null){
                SET("size_length = #{size_length}");
            }
            if (extCproduct.getSize_width() !=null){
                SET("size_width = #{size_width}");
            }
            if (extCproduct.getSize_higt()!=null){
                SET("size_higt = ${size_higt}");
            }
            if (extCproduct.getCrequest()!=null){
                SET("crequest = #{crequest}");
            }
            if (extCproduct.getCunit()!=null){
                SET("cunit = #{cunit}");
            }
            if (extCproduct.getBox_num()!=null){
                SET("box_num = #{box_num}");
            }
            if (extCproduct.getEx_price()!=null){
                SET("ex_price = #{ex_price}");
            }
            if (extCproduct.getEx_amount()!=null){
                SET("ex_amount = #{ex_amount}");
            }
            if (extCproduct.getEx_unit()!=null){
                SET("ex_unit = #{ex_unit}");
            }
            if (extCproduct.getNo_tax()!=null){
                SET("no_tax = #{no_tax}");
            }
            if (extCproduct.getTax()!=null){
                SET("tax = #{tax}");
            }
            if (extCproduct.getCost_price()!=null){
                SET("cost_price = #{cost_price}");
            }
            if (extCproduct.getCost_tax()!=null){
                SET("cost_tax = #{cost_tax}");
            }
            if (extCproduct.getAccessories()!=null){
                SET("accessories = #{accessories}");
            }
            if (extCproduct.getOrder_no()!=null){
                SET("order_no = #{order_no}");
            }
            WHERE("ext_cproduct_id = #{ext_cproduct_id}");
        }}.toString();
    }
}
