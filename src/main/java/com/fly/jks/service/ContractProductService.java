package com.fly.jks.service;

import com.fly.jks.domain.ContractProduct;

import java.io.Serializable;
import java.util.List;

/**
 * 货物业务层接口
 * @author liang
 * @date 2019/2/24 - 12:10
 */
public interface ContractProductService extends BaseService<ContractProduct>{
    /**
     * 根据合同号查询货物
     * @param id
     */
    public List<ContractProduct> selectContractProductByContractId(Serializable id)throws Exception;
}
