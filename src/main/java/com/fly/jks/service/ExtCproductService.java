package com.fly.jks.service;

import com.fly.jks.domain.ExtCproduct;

import java.io.Serializable;
import java.util.List;

/**
 * 货物下的附件业务层接口
 * @author liang
 * @date 2019/2/26 - 12:08
 */
public interface ExtCproductService extends BaseService<ExtCproduct>{
    /**
     * 级联删除 根据货物ID删除所属附件
     * @param
     */
    public void deleteByContractProductId(Serializable contract_product_id)throws Exception;

    /**
     * 这个方法是为了报运数据搬家写的，因为前面的方法查询是根据分页查询的，这个方法是查询全部，然后循环数据搬家
     * @param contract_product_id
     * @return
     * @throws Exception
     */
    public List<ExtCproduct> selectByContractProductId(String contract_product_id)throws Exception;


}
