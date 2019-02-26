package com.fly.jks.domain;


import java.io.Serializable;

/**
 * 附件实体类
 */
public class ExtCproduct implements Serializable{

  private String ext_cproduct_id;//附件UUID
  private String factory_id;//生产厂家UUID
  private String contract_product_id;//产品UUID
  private Integer ctype;//类型[系统下拉列表]
  private String product_name;//产品名称
  private String product_num;//产品编号/货号
  private String product_image;//产品图片
  private String product_desc;//产品描述
  private String loading_rate;//装率
  private String packing_unit;//包装单位 只pcs/套sets
  private Double price;//单价
  private Double amount;//总额
  private Integer cnumber;//产品数量
  private Integer out_num;//实际出货数量
  private String finshed;//出货状态0完毕 1未完
  private Double gross_weight;//毛重
  private Double net_weight;//净重
  private Double size_length;//尺寸长
  private Double size_width;//尺寸宽
  private Double size_higt;//尺寸高
  private String crequest;//要求
  private String factory_name;//厂家名称 冗余字段
  private String cunit;//EL单位
  private Integer box_num;//件数
  private Double ex_price;//出口单价
  private Double ex_amount;//出口合计
  private String ex_unit;//出口价格单位
  private Double no_tax;//不含税
  private Double tax;//含税
  private Double cost_price;//收购成本金额
  private Double cost_tax;//收购成本税金
  private String accessories;//是否有附件1有 0没
  private Integer order_no;//排序号

  public ExtCproduct() {
  }

  public ExtCproduct(String ext_cproduct_id, String factory_id, String contract_product_id, Integer ctype, String product_name, String product_num, String product_image, String product_desc, String loading_rate, String packing_unit, Double price, Double amount, Integer cnumber, Integer out_num, String finshed, Double gross_weight, Double net_weight, Double size_length, Double size_width, Double size_higt, String crequest, String factory_name, String cunit, Integer box_num, Double ex_price, Double ex_amount, String ex_unit, Double no_tax, Double tax, Double cost_price, Double cost_tax, String accessories, Integer order_no) {
    this.ext_cproduct_id = ext_cproduct_id;
    this.factory_id = factory_id;
    this.contract_product_id = contract_product_id;
    this.ctype = ctype;
    this.product_name = product_name;
    this.product_num = product_num;
    this.product_image = product_image;
    this.product_desc = product_desc;
    this.loading_rate = loading_rate;
    this.packing_unit = packing_unit;
    this.price = price;
    this.amount = amount;
    this.cnumber = cnumber;
    this.out_num = out_num;
    this.finshed = finshed;
    this.gross_weight = gross_weight;
    this.net_weight = net_weight;
    this.size_length = size_length;
    this.size_width = size_width;
    this.size_higt = size_higt;
    this.crequest = crequest;
    this.factory_name = factory_name;
    this.cunit = cunit;
    this.box_num = box_num;
    this.ex_price = ex_price;
    this.ex_amount = ex_amount;
    this.ex_unit = ex_unit;
    this.no_tax = no_tax;
    this.tax = tax;
    this.cost_price = cost_price;
    this.cost_tax = cost_tax;
    this.accessories = accessories;
    this.order_no = order_no;
  }

  @Override
  public String toString() {
    return "ExtCproduct{" +
            "ext_cproduct_id='" + ext_cproduct_id + '\'' +
            ", factory_id='" + factory_id + '\'' +
            ", contract_product_id='" + contract_product_id + '\'' +
            ", ctype=" + ctype +
            ", product_name='" + product_name + '\'' +
            ", product_num='" + product_num + '\'' +
            ", product_image='" + product_image + '\'' +
            ", product_desc='" + product_desc + '\'' +
            ", loading_rate='" + loading_rate + '\'' +
            ", packing_unit='" + packing_unit + '\'' +
            ", price=" + price +
            ", amount=" + amount +
            ", cnumber=" + cnumber +
            ", out_num=" + out_num +
            ", finshed='" + finshed + '\'' +
            ", gross_weight=" + gross_weight +
            ", net_weight=" + net_weight +
            ", size_length=" + size_length +
            ", size_width=" + size_width +
            ", size_higt=" + size_higt +
            ", crequest='" + crequest + '\'' +
            ", factory_name='" + factory_name + '\'' +
            ", cunit='" + cunit + '\'' +
            ", box_num=" + box_num +
            ", ex_price=" + ex_price +
            ", ex_amount=" + ex_amount +
            ", ex_unit='" + ex_unit + '\'' +
            ", no_tax=" + no_tax +
            ", tax=" + tax +
            ", cost_price=" + cost_price +
            ", cost_tax=" + cost_tax +
            ", accessories='" + accessories + '\'' +
            ", order_no=" + order_no +
            '}';
  }

  public String getExt_cproduct_id() {
    return ext_cproduct_id;
  }

  public void setExt_cproduct_id(String ext_cproduct_id) {
    this.ext_cproduct_id = ext_cproduct_id;
  }

  public String getFactory_id() {
    return factory_id;
  }

  public void setFactory_id(String factory_id) {
    this.factory_id = factory_id;
  }

  public String getContract_product_id() {
    return contract_product_id;
  }

  public void setContract_product_id(String contract_product_id) {
    this.contract_product_id = contract_product_id;
  }

  public Integer getCtype() {
    return ctype;
  }

  public void setCtype(Integer ctype) {
    this.ctype = ctype;
  }

  public String getProduct_name() {
    return product_name;
  }

  public void setProduct_name(String product_name) {
    this.product_name = product_name;
  }

  public String getProduct_num() {
    return product_num;
  }

  public void setProduct_num(String product_num) {
    this.product_num = product_num;
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

  public Integer getCnumber() {
    return cnumber;
  }

  public void setCnumber(Integer cnumber) {
    this.cnumber = cnumber;
  }

  public Integer getOut_num() {
    return out_num;
  }

  public void setOut_num(Integer out_num) {
    this.out_num = out_num;
  }

  public String getFinshed() {
    return finshed;
  }

  public void setFinshed(String finshed) {
    this.finshed = finshed;
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

  public Double getSize_higt() {
    return size_higt;
  }

  public void setSize_higt(Double size_higt) {
    this.size_higt = size_higt;
  }

  public String getCrequest() {
    return crequest;
  }

  public void setCrequest(String crequest) {
    this.crequest = crequest;
  }

  public String getFactory_name() {
    return factory_name;
  }

  public void setFactory_name(String factory_name) {
    this.factory_name = factory_name;
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

  public Double getEx_amount() {
    return ex_amount;
  }

  public void setEx_amount(Double ex_amount) {
    this.ex_amount = ex_amount;
  }

  public String getEx_unit() {
    return ex_unit;
  }

  public void setEx_unit(String ex_unit) {
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
