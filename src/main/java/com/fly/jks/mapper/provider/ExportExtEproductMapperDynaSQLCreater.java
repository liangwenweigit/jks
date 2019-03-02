package com.fly.jks.mapper.provider;


import com.fly.jks.domain.ExportExtEproduct;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author liang
 * @date 2019/3/3 - 0:28
 */
public class ExportExtEproductMapperDynaSQLCreater {

    /**
     *插入一条数据
     * @param
     * @return
     */
    public String insert(ExportExtEproduct exportExtEproduct) {
        return new SQL() {{
            INSERT_INTO("export_ext_eproduct");
            VALUES("ext_eproduct_id,factory_id,export_product_id,contract_product_id,product_name,product_no,product_image,product_desc,loading_rate,packing_unit,cnumber,out_number,finished,gross_weight,net_weight,size_length,size_width,size_height,product_request,factory_name,price,amount,cunit,box_num,ex_price,ex_unit,no_tax,tax,cost_price,cost_tax,accessories,order_no",
                    "#{ext_eproduct_id},#{factory_id},#{export_product_id},#{contract_product_id},#{product_name},#{product_no},#{product_image},#{product_desc},#{loading_rate},#{packing_unit},#{cnumber},#{out_number},#{finished},#{gross_weight},#{net_weight},#{size_length},#{size_width},#{size_height},#{product_request},#{factory_name},#{price},#{amount},#{cunit},#{box_num},#{ex_price},#{ex_unit},#{no_tax},#{tax},#{cost_price},#{cost_tax},#{accessories},#{order_no}");
        }}.toString();
    }
}
