package com.fly.jks.domain;


import java.io.Serializable;

/**
 * 货物实体类
 * 一个合同下有多个货物
 */
public class ContractProduct implements Serializable{

  private String contract_product_id;//产品货物UUID
  //private Factory factory;//这里不用面向对象的思想,直接用下面的String 这样可以单表操作
  private String factory_id;//外键 厂家UUID（因为这里使用的是mybatis,这样单表操作速度更加）
  //private Contract contract;//这里不用面向对象的思想,直接用下面的String 这样可以单表操作
  private String contract_id;//外键 合同UUID（因为这里使用的是mybatis,这样单表操作速度更加）
  private String factory_name;//厂家简称 冗余字段
  private String product_ame;//产品名称
  private String product_num;//产品编号
  private String product_image;//产品图片
  private String product_desc;//产品描述
  private Integer cnumber;//产品数量
  private String loading_rate;//装率
  private String packing_unit;//包装单位 只pcs/套sets
  private double price;//单价/分 也可能不是分
  private double amount;//总额
  private Integer out_num;//实际出货数量
  private String finshed;//是否出货完毕1完毕 0未完
  private double gross_weigh;//毛重
  private double net_weight;//净重
  private String csize;//体积
  private double size_length;//尺寸长
  private double size_width;//尺寸宽
  private double size_weight;//尺寸高
  private String product_request;//要求
  private String cunit;//EL单位
  private Integer box_num;//件数
  private double ex_price;//出口单价
  private double ex_amount;//出口合计
  private String ex_unit;//出口价格单位
  private double no_tax;//不含税
  private double tax;//含税
  private double cost_price;//收购成本金额
  private double cost_tax;//收购成本税金
  private String accessories;//是否有附件1有 0没
  //一个合同下有多个货物，用户可以修改排序号 来排顺序
  private Integer orderNo;//排序号，测试驼峰命名 是否OK

  public ContractProduct() {
  }

  public ContractProduct(String contract_product_id, String factory_id, String contract_id, String factory_name, String product_ame, String product_num, String product_image, String product_desc, Integer cnumber, String loading_rate, String packing_unit, double price, double amount, Integer out_num, String finshed, double gross_weigh, double net_weight, String csize, double size_length, double size_width, double size_weight, String product_request, String cunit, Integer box_num, double ex_price, double ex_amount, String ex_unit, double no_tax, double tax, double cost_price, double cost_tax, String accessories, Integer orderNo) {
    this.contract_product_id = contract_product_id;
    this.factory_id = factory_id;
    this.contract_id = contract_id;
    this.factory_name = factory_name;
    this.product_ame = product_ame;
    this.product_num = product_num;
    this.product_image = product_image;
    this.product_desc = product_desc;
    this.cnumber = cnumber;
    this.loading_rate = loading_rate;
    this.packing_unit = packing_unit;
    this.price = price;
    this.amount = amount;
    this.out_num = out_num;
    this.finshed = finshed;
    this.gross_weigh = gross_weigh;
    this.net_weight = net_weight;
    this.csize = csize;
    this.size_length = size_length;
    this.size_width = size_width;
    this.size_weight = size_weight;
    this.product_request = product_request;
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
    this.orderNo = orderNo;
  }

  @Override
  public String toString() {
    return "ContractProduct{" +
            "contract_product_id='" + contract_product_id + '\'' +
            ", factory_id='" + factory_id + '\'' +
            ", contract_id='" + contract_id + '\'' +
            ", factory_name='" + factory_name + '\'' +
            ", product_ame='" + product_ame + '\'' +
            ", product_num='" + product_num + '\'' +
            ", product_image='" + product_image + '\'' +
            ", product_desc='" + product_desc + '\'' +
            ", cnumber=" + cnumber +
            ", loading_rate='" + loading_rate + '\'' +
            ", packing_unit='" + packing_unit + '\'' +
            ", price=" + price +
            ", amount=" + amount +
            ", out_num=" + out_num +
            ", finshed='" + finshed + '\'' +
            ", gross_weigh=" + gross_weigh +
            ", net_weight=" + net_weight +
            ", csize='" + csize + '\'' +
            ", size_length=" + size_length +
            ", size_width=" + size_width +
            ", size_weight=" + size_weight +
            ", product_request='" + product_request + '\'' +
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
            ", orderNo=" + orderNo +
            '}';
  }

  public String getContract_product_id() {
    return contract_product_id;
  }

  public void setContract_product_id(String contract_product_id) {
    this.contract_product_id = contract_product_id;
  }

  public String getFactory_id() {
    return factory_id;
  }

  public void setFactory_id(String factory_id) {
    this.factory_id = factory_id;
  }

  public String getContract_id() {
    return contract_id;
  }

  public void setContract_id(String contract_id) {
    this.contract_id = contract_id;
  }

  public String getFactory_name() {
    return factory_name;
  }

  public void setFactory_name(String factory_name) {
    this.factory_name = factory_name;
  }

  public String getProduct_ame() {
    return product_ame;
  }

  public void setProduct_ame(String product_ame) {
    this.product_ame = product_ame;
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

  public Integer getCnumber() {
    return cnumber;
  }

  public void setCnumber(Integer cnumber) {
    this.cnumber = cnumber;
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

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
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

  public double getGross_weigh() {
    return gross_weigh;
  }

  public void setGross_weigh(double gross_weigh) {
    this.gross_weigh = gross_weigh;
  }

  public double getNet_weight() {
    return net_weight;
  }

  public void setNet_weight(double net_weight) {
    this.net_weight = net_weight;
  }

  public String getCsize() {
    return csize;
  }

  public void setCsize(String csize) {
    this.csize = csize;
  }

  public double getSize_length() {
    return size_length;
  }

  public void setSize_length(double size_length) {
    this.size_length = size_length;
  }

  public double getSize_width() {
    return size_width;
  }

  public void setSize_width(double size_width) {
    this.size_width = size_width;
  }

  public double getSize_weight() {
    return size_weight;
  }

  public void setSize_weight(double size_weight) {
    this.size_weight = size_weight;
  }

  public String getProduct_request() {
    return product_request;
  }

  public void setProduct_request(String product_request) {
    this.product_request = product_request;
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

  public double getEx_price() {
    return ex_price;
  }

  public void setEx_price(double ex_price) {
    this.ex_price = ex_price;
  }

  public double getEx_amount() {
    return ex_amount;
  }

  public void setEx_amount(double ex_amount) {
    this.ex_amount = ex_amount;
  }

  public String getEx_unit() {
    return ex_unit;
  }

  public void setEx_unit(String ex_unit) {
    this.ex_unit = ex_unit;
  }

  public double getNo_tax() {
    return no_tax;
  }

  public void setNo_tax(double no_tax) {
    this.no_tax = no_tax;
  }

  public double getTax() {
    return tax;
  }

  public void setTax(double tax) {
    this.tax = tax;
  }

  public double getCost_price() {
    return cost_price;
  }

  public void setCost_price(double cost_price) {
    this.cost_price = cost_price;
  }

  public double getCost_tax() {
    return cost_tax;
  }

  public void setCost_tax(double cost_tax) {
    this.cost_tax = cost_tax;
  }

  public String getAccessories() {
    return accessories;
  }

  public void setAccessories(String accessories) {
    this.accessories = accessories;
  }

  public Integer getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(Integer orderNo) {
    this.orderNo = orderNo;
  }
}
