package com.fly.jks.domain.vo;

import com.fly.jks.domain.Contract;
import java.io.Serializable;
import java.util.Date;

/**
 * 包装类，接收结果集
 * @author liang
 * @date 2019/2/27 - 22:41
 */
public class ContractVO extends Contract implements Serializable{
    /**
     * 添加结果集的另外5个货物字段
     */
    private String product_num;//货物编号
    private Integer cnumber;//货物数量
    private String packing_unit;//包装单位 只pcs/套sets
    private String factory_name;//厂家简称 冗余字段
    private String accessories;//是否有附件1有 0

    public ContractVO(String contract_id, String offeror, String contract_no, Date signing_date, String input_by, String check_by, String inspector, double total_price, String crequest, String customer_name, Date ship_date, Integer import_num, String instructions, String print_style, String old_state, String contract_state, String out_state, Date delivery_date, String trade_clause, String create_by, String create_bept, Date create_time, String product_num, Integer cnumber, String packing_unit, String factory_name, String accessories) {
        super(contract_id, offeror, contract_no, signing_date, input_by, check_by, inspector, total_price, crequest, customer_name, ship_date, import_num, instructions, print_style, old_state, contract_state, out_state, delivery_date, trade_clause, create_by, create_bept, create_time);
        this.product_num = product_num;
        this.cnumber = cnumber;
        this.packing_unit = packing_unit;
        this.factory_name = factory_name;
        this.accessories = accessories;
    }

    public ContractVO() {
    }

    @Override
    public String toString() {
        return "ContractVO{" +
                "product_num='" + product_num + '\'' +
                ", cnumber=" + cnumber +
                ", packing_unit='" + packing_unit + '\'' +
                ", factory_name='" + factory_name + '\'' +
                ", accessories='" + accessories + '\'' +
                '}';
    }

    public String getProduct_num() {
        return product_num;
    }

    public void setProduct_num(String product_num) {
        this.product_num = product_num;
    }

    public Integer getCnumber() {
        return cnumber;
    }

    public void setCnumber(Integer cnumber) {
        this.cnumber = cnumber;
    }

    public String getPacking_unit() {
        return packing_unit;
    }

    public void setPacking_unit(String packing_unit) {
        this.packing_unit = packing_unit;
    }

    public String getFactory_name() {
        return factory_name;
    }

    public void setFactory_name(String factory_name) {
        this.factory_name = factory_name;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }
}
