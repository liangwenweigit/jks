package com.fly.jks.domain;


import java.io.Serializable;
import java.util.Date;

/**
 * 购销合同 实体类
 */
public class Contract implements Serializable{

  private String contract_id;//合同UUID
  private String offeror;//收购方
  private String contract_no;//合同号/订单号
  private java.util.Date signing_date;//签单日期
  private String input_by;//制单人
  private String check_by;//审单人
  private String inspector;//验货员
  //必须是包装类
  private Double total_price;//合同总计金额
  private String crequest;//要求
  private String customer_name;//客户名称
  private java.util.Date ship_date;//船期/运输出口时间
  private Integer import_num;//重要程度+ 页面显示星星
  private String instructions;//说明
  private String print_style;//打印版式，一个页面打印一款 还是几款商品，根据客户要求而定
  private String old_state;//归档前状态
  private String contract_state;//状态1未完成  0完成  //1草稿  0已上报
  private String out_state;//走货状态
  private java.util.Date delivery_date;//交货日期
  private String trade_clause;//贸易条款
  private String create_by;//创建人
  private String create_bept;//创建部门
  private java.util.Date create_time;//创建时间

  public String getContract_id() {
    return contract_id;
  }

  public void setContract_id(String contract_id) {
    this.contract_id = contract_id;
  }

  public String getOfferor() {
    return offeror;
  }

  public void setOfferor(String offeror) {
    this.offeror = offeror;
  }

  public String getContract_no() {
    return contract_no;
  }

  public void setContract_no(String contract_no) {
    this.contract_no = contract_no;
  }

  public Date getSigning_date() {
    return signing_date;
  }

  public void setSigning_date(Date signing_date) {
    this.signing_date = signing_date;
  }

  public String getInput_by() {
    return input_by;
  }

  public void setInput_by(String input_by) {
    this.input_by = input_by;
  }

  public String getCheck_by() {
    return check_by;
  }

  public void setCheck_by(String check_by) {
    this.check_by = check_by;
  }

  public String getInspector() {
    return inspector;
  }

  public void setInspector(String inspector) {
    this.inspector = inspector;
  }

  public Double getTotal_price() {
    return total_price;
  }

  public void setTotal_price(double total_price) {
    this.total_price = total_price;
  }

  public String getCrequest() {
    return crequest;
  }

  public void setCrequest(String crequest) {
    this.crequest = crequest;
  }

  public String getCustomer_name() {
    return customer_name;
  }

  public void setCustomer_name(String customer_name) {
    this.customer_name = customer_name;
  }

  public Date getShip_date() {
    return ship_date;
  }

  public void setShip_date(Date ship_date) {
    this.ship_date = ship_date;
  }

  public Integer getImport_num() {
    return import_num;
  }

  public void setImport_num(Integer import_num) {
    this.import_num = import_num;
  }

  public String getInstructions() {
    return instructions;
  }

  public void setInstructions(String instructions) {
    this.instructions = instructions;
  }

  public String getPrint_style() {
    return print_style;
  }

  public void setPrint_style(String print_style) {
    this.print_style = print_style;
  }

  public String getOld_state() {
    return old_state;
  }

  public void setOld_state(String old_state) {
    this.old_state = old_state;
  }

  public String getContract_state() {
    return contract_state;
  }

  public void setContract_state(String contract_state) {
    this.contract_state = contract_state;
  }

  public String getOut_state() {
    return out_state;
  }

  public void setOut_state(String out_state) {
    this.out_state = out_state;
  }

  public Date getDelivery_date() {
    return delivery_date;
  }

  public void setDelivery_date(Date delivery_date) {
    this.delivery_date = delivery_date;
  }

  public String getTrade_clause() {
    return trade_clause;
  }

  public void setTrade_clause(String trade_clause) {
    this.trade_clause = trade_clause;
  }

  public String getCreate_by() {
    return create_by;
  }

  public void setCreate_by(String create_by) {
    this.create_by = create_by;
  }

  public String getCreate_bept() {
    return create_bept;
  }

  public void setCreate_bept(String create_bept) {
    this.create_bept = create_bept;
  }

  public Date getCreate_time() {
    return create_time;
  }

  public void setCreate_time(Date create_time) {
    this.create_time = create_time;
  }

  @Override
  public String toString() {
    return "Contract{" +
            "contract_id='" + contract_id + '\'' +
            ", offeror='" + offeror + '\'' +
            ", contract_no='" + contract_no + '\'' +
            ", signing_date=" + signing_date +
            ", input_by='" + input_by + '\'' +
            ", check_by='" + check_by + '\'' +
            ", inspector='" + inspector + '\'' +
            ", total_price=" + total_price +
            ", crequest='" + crequest + '\'' +
            ", customer_name='" + customer_name + '\'' +
            ", ship_date=" + ship_date +
            ", import_num=" + import_num +
            ", instructions='" + instructions + '\'' +
            ", print_style='" + print_style + '\'' +
            ", old_state='" + old_state + '\'' +
            ", contract_state='" + contract_state + '\'' +
            ", out_state='" + out_state + '\'' +
            ", delivery_date=" + delivery_date +
            ", trade_clause='" + trade_clause + '\'' +
            ", create_by='" + create_by + '\'' +
            ", create_bept='" + create_bept + '\'' +
            ", create_time=" + create_time +
            '}';
  }

  public Contract() {
  }

  public Contract(String contract_id, String offeror, String contract_no, Date signing_date, String input_by, String check_by, String inspector, double total_price, String crequest, String customer_name, Date ship_date, Integer import_num, String instructions, String print_style, String old_state, String contract_state, String out_state, Date delivery_date, String trade_clause, String create_by, String create_bept, Date create_time) {
    this.contract_id = contract_id;
    this.offeror = offeror;
    this.contract_no = contract_no;
    this.signing_date = signing_date;
    this.input_by = input_by;
    this.check_by = check_by;
    this.inspector = inspector;
    this.total_price = total_price;
    this.crequest = crequest;
    this.customer_name = customer_name;
    this.ship_date = ship_date;
    this.import_num = import_num;
    this.instructions = instructions;
    this.print_style = print_style;
    this.old_state = old_state;
    this.contract_state = contract_state;
    this.out_state = out_state;
    this.delivery_date = delivery_date;
    this.trade_clause = trade_clause;
    this.create_by = create_by;
    this.create_bept = create_bept;
    this.create_time = create_time;
  }
}
