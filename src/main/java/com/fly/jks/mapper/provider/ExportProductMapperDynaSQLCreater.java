package com.fly.jks.mapper.provider;

import com.fly.jks.domain.ExportProduct;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author liang
 * @date 2019/3/3 - 0:13
 */
public class ExportProductMapperDynaSQLCreater {

    /**
     *插入一条数据
     * @param exportProduct
     * @return
     */
    public String insert(ExportProduct exportProduct){
        return new SQL(){{
            INSERT_INTO("export_product");
            VALUES("export_product_id,factory_id,export_id,contract_product_id,contract_id,contract_no,product_name,product_no,product_image,product_desc,loading_rate,packing_unit,cnumber,out_number,finished,gross_weight,net_weight,size_length,size_width,size_height,product_request,factory_name,price,amount,cunit,box_num,ex_price,ex_unit,no_tax,tax,cost_price,cost_tax,accessories,order_no",
                    "#{export_product_id},#{factory_id},#{export_id},#{contract_product_id},#{contract_id},#{contract_no},#{product_name},#{product_no},#{product_image},#{product_desc},#{loading_rate},#{packing_unit},#{cnumber},#{out_number},#{finished},#{gross_weight},#{net_weight},#{size_length},#{size_width},#{size_height},#{product_request},#{factory_name},#{price},#{amount},#{cunit},#{box_num},#{ex_price},#{ex_unit},#{no_tax},#{tax},#{cost_price},#{cost_tax},#{accessories},#{order_no}");
        }}.toString();
    }
}
