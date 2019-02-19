package com.fly.jks.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 生产厂家实体类对象
 * @author liang
 * @date 2019/2/17 - 19:40
 *
 */
public class Factory implements Serializable{
    /**
     * 主键uuid
     */
    private String factory_id;
    private String full_name;
    private String factory_name;
    private String contacts;
    private String phone;
    private String mobile;
    private String fax;
    private String cnote;
    private String inspector;
    private Integer order_no;
    private String order_by;
    private String create_dept;
    private java.util.Date create_time;
    /**
     * 1表示启动，0表示停用
     */
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Factory() {
    }

    public Factory(String factory_id, String full_name, String factory_name, String contacts, String phone, String mobile, String fax, String cnote, String inspector, Integer order_no, String order_by, String create_dept, Date create_time) {
        this.factory_id = factory_id;
        this.full_name = full_name;
        this.factory_name = factory_name;
        this.contacts = contacts;
        this.phone = phone;
        this.mobile = mobile;
        this.fax = fax;
        this.cnote = cnote;
        this.inspector = inspector;
        this.order_no = order_no;
        this.order_by = order_by;
        this.create_dept = create_dept;
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "Factory{" +
                "factory_id='" + factory_id + '\'' +
                ", full_name='" + full_name + '\'' +
                ", factory_name='" + factory_name + '\'' +
                ", contacts='" + contacts + '\'' +
                ", phone='" + phone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", fax='" + fax + '\'' +
                ", cnote='" + cnote + '\'' +
                ", inspector='" + inspector + '\'' +
                ", order_no=" + order_no +
                ", order_by='" + order_by + '\'' +
                ", create_dept='" + create_dept + '\'' +
                ", create_time=" + create_time +
                '}';
    }

    public String getFactory_id() {
        return factory_id;
    }

    public void setFactory_id(String factory_id) {
        this.factory_id = factory_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getFactory_name() {
        return factory_name;
    }

    public void setFactory_name(String factory_name) {
        this.factory_name = factory_name;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCnote() {
        return cnote;
    }

    public void setCnote(String cnote) {
        this.cnote = cnote;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public Integer getOrder_no() {
        return order_no;
    }

    public void setOrder_no(Integer order_no) {
        this.order_no = order_no;
    }

    public String getOrder_by() {
        return order_by;
    }

    public void setOrder_by(String order_by) {
        this.order_by = order_by;
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
