package com.fly.jks.mapper.provider;

import com.fly.jks.domain.Export;
import com.fly.jks.domain.ExtCproduct;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author liang
 * @date 2019/3/1 - 22:26
 */
public class ExportMapperDynaSQLCreater {

    /**
     * 真删除1条/批量删除
     */
    public String deleteSQL(String sql){
        return new String("DELETE FROM export WHERE export_id IN ("+sql+")");
    }


    /**
     * 新增一条动态sql
     * @return
     */
    public String insertSQL(){
        return new SQL(){{
            INSERT_INTO("export");
            VALUES("export_id,packing_list_id,contract_id,input_date,contract_ids,customer_contract,lcno,consignee,marks,shipment_port,destination_port,transport_mode,price_condition,remark,box_num,cnumber,packing_unit,gross_weight,net_weight,size_length,size_width,size_height,csize,amount,no_tax,tax,cost_price,cost_tax,state,create_by,create_dept,create_time",
                    "#{export_id},#{packing_list_id},#{contract_id},#{input_date},#{contract_ids},#{customer_contract},#{lcno},#{consignee},#{marks},#{shipment_port},#{destination_port},#{transport_mode},#{price_condition},#{remark},#{box_num},#{cnumber},#{packing_unit},#{gross_weight},#{net_weight},#{size_length},#{size_width},#{size_height},#{csize},#{amount},#{no_tax},#{tax},#{cost_price},#{cost_tax},#{state},#{create_by},#{create_dept},#{create_time}");
        }}.toString();
    }

    /**
     * 更新
     * @param export
     * @return
     */
    public String updateSQL(Export export) {
        return new SQL() {{
            UPDATE("export");
            if (export.getInput_date()!=null){
                SELECT("input_date = #{input_date}");
            }
            if (export.getContract_ids()!=null){
                SELECT("contract_ids = #{contract_ids}");
            }
            if (export.getCustomer_contract()!=null){
                SELECT("customer_contract = #{customer_contract}");
            }
            if (export.getLcno()!=null){
                SELECT("lcno = #{lcno}");
            }
            if (export.getConsignee()!=null){
                SELECT("consignee = #{consignee}");
            }
            if (export.getMarks()!=null){
                SELECT("marks = #{marks}");
            }
            if (export.getRemark()!=null){
                SELECT("remark = #{remark}");
            }
            if (export.getShipment_port()!=null){
                SELECT("shipment_port = #{shipment_ort}");
            }
            if (export.getDestination_port()!=null){
                SELECT("destination_port = #{destination_port}");
            }
            if (export.getTransport_mode()!=null){
                SELECT("transport_mode = #{transport_mode}");
            }
            if (export.getPrice_condition()!=null){
                SELECT("price_condition = #{price_condition}");
            }
            if (export.getState()!=null){
                SELECT("state = #{state}");
            }
            if (export.getGross_weight()!=null){
                SELECT("gross_weight = #{gross_weight}");
            }
            if (export.getNet_weight()!=null){
                SELECT("net_weight = #{net_weight}");
            }
            if (export.getCsize()!=null){
                SELECT("csize = #{csize}");
            }
            if (export.getCreate_by()!=null){
                SELECT("create_by = #{create_by}");
            }
            if (export.getCreate_dept()!=null){
                SELECT("create_dept = #{create_dept}");
            }
            if (export.getCreate_time()!=null){
                SELECT("create_time = #{create_time}");
            }
            WHERE("export_id = #{export_id}");
        }}.toString();
    }
}
