package com.fly.jks.service;

import com.fly.jks.domain.Contract;
import com.fly.jks.domain.vo.ContractVO;

import java.io.Serializable;
import java.util.List;

/**
 * 合同业务层接口
 * @author liang
 * @date 2019/2/22 - 18:25
 */
public interface ContractService extends BaseService<Contract>{
    /**
     * 设置总金额
     * @param contract_id
     */
    public void updateTotal(Serializable contract_id)throws Exception;

    /**
     * 出货统计表方法
     */
    public List<ContractVO> selectOutProductByDate(String inputDate)throws Exception;
}
