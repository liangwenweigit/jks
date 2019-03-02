package com.fly.jks.domain;


import java.io.Serializable;
import java.util.Date;

/**
 * 货运表实体类
 */
public class Export implements Serializable{

  private String export_id;//货运UUID
  private String packing_list_id;//
  private String contract_id;//这个作用未知，未知暂时可以不要 不使用
  private java.util.Date input_date;//制单日期
  //这个是把用户选择的全部合同 的UUID拼串。以‘,’号分隔，设置到里面来
  private String contract_ids;//合同UUID集合 打断设计
  //这个是把用户选择的全部合同 的合同号拼接成字符串（例子：x，y），报运号=是由合同的合同号组成的 空格隔开，设置到里面来
  private String customer_contract;//customer_contract合同或确认书号
  private String lcno;//信用证号
  private String consignee;//收货人及地址
  private String marks;//唛头
  private String shipment_port;//装运港
  private String destination_port;//目的港
  private String transport_mode;//运输方式
  private String price_condition;//价格条件
  private String remark;//备注
  private Integer box_num;
  private Integer cnumber;
  private String packing_unit;
  private Double gross_weight;
  private Double net_weight;
  private Double size_length;
  private Double size_width;
  private Double size_height;
  private Double csize;
  private Double amount;
  private Double no_tax;
  private Double tax;
  private Double cost_price;
  private Double cost_tax;
  private String state;//0-草稿 1-已上报 2-装箱 3-委托 4-发票 5-财务
  private String create_by;
  private String create_dept;
  private java.util.Date create_time;

  public String getExport_id() {
    return export_id;
  }

  public void setExport_id(String export_id) {
    this.export_id = export_id;
  }

  public String getPacking_list_id() {
    return packing_list_id;
  }

  public void setPacking_list_id(String packing_list_id) {
    this.packing_list_id = packing_list_id;
  }

  public String getContract_id() {
    return contract_id;
  }

  public void setContract_id(String contract_id) {
    this.contract_id = contract_id;
  }

  public Date getInput_date() {
    return input_date;
  }

  public void setInput_date(Date input_date) {
    this.input_date = input_date;
  }

  public String getContract_ids() {
    return contract_ids;
  }

  public void setContract_ids(String contract_ids) {
    this.contract_ids = contract_ids;
  }

  public String getCustomer_contract() {
    return customer_contract;
  }

  public void setCustomer_contract(String customer_contract) {
    this.customer_contract = customer_contract;
  }

  public String getLcno() {
    return lcno;
  }

  public void setLcno(String lcno) {
    this.lcno = lcno;
  }

  public String getConsignee() {
    return consignee;
  }

  public void setConsignee(String consignee) {
    this.consignee = consignee;
  }

  public String getMarks() {
    return marks;
  }

  public void setMarks(String marks) {
    this.marks = marks;
  }

  public String getShipment_port() {
    return shipment_port;
  }

  public void setShipment_port(String shipment_port) {
    this.shipment_port = shipment_port;
  }

  public String getDestination_port() {
    return destination_port;
  }

  public void setDestination_port(String destination_port) {
    this.destination_port = destination_port;
  }

  public String getTransport_mode() {
    return transport_mode;
  }

  public void setTransport_mode(String transport_mode) {
    this.transport_mode = transport_mode;
  }

  public String getPrice_condition() {
    return price_condition;
  }

  public void setPrice_condition(String price_condition) {
    this.price_condition = price_condition;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Integer getBox_num() {
    return box_num;
  }

  public void setBox_num(Integer box_num) {
    this.box_num = box_num;
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

  public Double getGross_weight() {
    return gross_weight;
  }

  public void setGross_weight(Double gross_weight) {
    this.gross_weight = gross_weight;
  }

  public Double getNet_weight() {
    return net_weight;
  }

  public void setNet_weight(Double net_weight) {
    this.net_weight = net_weight;
  }

  public Double getSize_length() {
    return size_length;
  }

  public void setSize_length(Double size_length) {
    this.size_length = size_length;
  }

  public Double getSize_width() {
    return size_width;
  }

  public void setSize_width(Double size_width) {
    this.size_width = size_width;
  }

  public Double getSize_height() {
    return size_height;
  }

  public void setSize_height(Double size_height) {
    this.size_height = size_height;
  }

  public Double getCsize() {
    return csize;
  }

  public void setCsize(Double csize) {
    this.csize = csize;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Double getNo_tax() {
    return no_tax;
  }

  public void setNo_tax(Double no_tax) {
    this.no_tax = no_tax;
  }

  public Double getTax() {
    return tax;
  }

  public void setTax(Double tax) {
    this.tax = tax;
  }

  public Double getCost_price() {
    return cost_price;
  }

  public void setCost_price(Double cost_price) {
    this.cost_price = cost_price;
  }

  public Double getCost_tax() {
    return cost_tax;
  }

  public void setCost_tax(Double cost_tax) {
    this.cost_tax = cost_tax;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCreate_by() {
    return create_by;
  }

  public void setCreate_by(String create_by) {
    this.create_by = create_by;
  }

  public String getCreate_dept() {
    return create_dept;
  }

  public void setCreate_dept(String create_dept) {
    this.create_dept = create_dept;
  }

  public Date getCreate_time() {
    return create_time;
  }

  public void setCreate_time(Date create_time) {
    this.create_time = create_time;
  }
}
