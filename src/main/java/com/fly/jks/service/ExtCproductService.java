package com.fly.jks.service;

import com.fly.jks.domain.ExtCproduct;

import java.io.Serializable;

/**
 * @author liang
 * @date 2019/2/26 - 12:08
 */
public interface ExtCproductService extends BaseService<ExtCproduct>{
    /**
     * 级联删除 根据货物ID删除所属附件
     * @param
     */
    public void deleteByContractProductId(Serializable contract_product_id)throws Exception;


}
