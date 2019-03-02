package com.fly.jks.domain;


import java.io.Serializable;

/**
 * 货运货物实体类
 */
public class ExportProduct implements Serializable{

  private String export_product_id;//报运的货物UUID
  private String factory_id;//工厂UUID外键
  private String export_id;//报运的UUID外键
  private String contract_product_id;//这个货物UUID 没有用到,想到的业务 可能也是后期的更新 像更新合同下的货物一样
  private String contract_id;//合同UUID，这个只是当普通字段被设置进来，后期业务，更新合同走货状态，更新合同下的货物实际出货数量/是否出货完毕 要使用
  private String contract_no;
  private String product_name;
  private String product_no;
  private String product_image;
  private String product_desc;
  private String loading_rate;
  private String packing_unit;
  private Integer cnumber;
  private Integer out_number;
  private String finished;
  private Double gross_weight;
  private Double net_weight;
  private Double size_length;
  private Double size_width;
  private Double size_height;
  private String product_request;
  private String factory_name;
  private Double price;
  private Double amount;
  private String cunit;
  private Integer box_num;
  private Double ex_price;
  private Double ex_unit;
  private Double no_tax;
  private Double tax;
  private Double cost_price;
  private Double cost_tax;
  private String accessories;
  private Integer order_no;

  public String getExport_product_id() {
    return export_product_id;
  }

  public void setExport_product_id(String export_product_id) {
    this.export_product_id = export_product_id;
  }

  public String getFactory_id() {
    return factory_id;
  }

  public void setFactory_id(String factory_id) {
    this.factory_id = factory_id;
  }

  public String getExport_id() {
    return export_id;
  }

  public void setExport_id(String export_id) {
    this.export_id = export_id;
  }

  public String getContract_product_id() {
    return contract_product_id;
  }

  public void setContract_product_id(String contract_product_id) {
    this.contract_product_id = contract_product_id;
  }

  public String getContract_id() {
    return contract_id;
  }

  public void setContract_id(String contract_id) {
    this.contract_id = contract_id;
  }

  public String getContract_no() {
    return contract_no;
  }

  public void setContract_no(String contract_no) {
    this.contract_no = contract_no;
  }

  public String getProduct_name() {
    return product_name;
  }

  public void setProduct_name(String product_name) {
    this.product_name = product_name;
  }

  public String getProduct_no() {
    return product_no;
  }

  public void setProduct_no(String product_no) {
    this.product_no = product_no;
  }

  public String getProduct_image() {
    return product_image;
  }

  public void setProduct_image(String product_image) {
    this.product_image = product_image;
  }

  public String getProduct_desc() {
    return product_desc;
  }

  public void setProduct_desc(String product_desc) {
    this.product_desc = product_desc;
  }

  public String getLoading_rate() {
    return loading_rate;
  }

  public void setLoading_rate(String loading_rate) {
    this.loading_rate = loading_rate;
  }

  public String getPacking_unit() {
    return packing_unit;
  }

  public void setPacking_unit(String packing_unit) {
    this.packing_unit = packing_unit;
  }

  public Integer getCnumber() {
    return cnumber;
  }

  public void setCnumber(Integer cnumber) {
    this.cnumber = cnumber;
  }

  public Integer getOut_number() {
    return out_number;
  }

  public void setOut_number(Integer out_number) {
    this.out_number = out_number;
  }

  public String getFinished() {
    return finished;
  }

  public void setFinished(String finished) {
    this.finished = finished;
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

  public String getProduct_request() {
    return product_request;
  }

  public void setProduct_request(String product_request) {
    this.product_request = product_request;
  }

  public String getFactory_name() {
    return factory_name;
  }

  public void setFactory_name(String factory_name) {
    this.factory_name = factory_name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getCunit() {
    return cunit;
  }

  public void setCunit(String cunit) {
    this.cunit = cunit;
  }

  public Integer getBox_num() {
    return box_num;
  }

  public void setBox_num(Integer box_num) {
    this.box_num = box_num;
  }

  public Double getEx_price() {
    return ex_price;
  }

  public void setEx_price(Double ex_price) {
    this.ex_price = ex_price;
  }

  public Double getEx_unit() {
    return ex_unit;
  }

  public void setEx_unit(Double ex_unit) {
    this.ex_unit = ex_unit;
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

  public String getAccessories() {
    return accessories;
  }

  public void setAccessories(String accessories) {
    this.accessories = accessories;
  }

  public Integer getOrder_no() {
    return order_no;
  }

  public void setOrder_no(Integer order_no) {
    this.order_no = order_no;
  }
}
