package com.fly.jks.service.impl;

import com.fly.jks.domain.Contract;
import com.fly.jks.domain.vo.ContractVO;
import com.fly.jks.mapper.ContractMapper;
import com.fly.jks.pagination.Page;
import com.fly.jks.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;
import java.util.List;

/**
 * 合同实现类
 * @author liang
 * @date 2019/2/22 - 18:26
 */
@Service
public class ContractServiceImpl  implements ContractService{

    @Autowired
    private ContractMapper contractMapper;

    @Override
    public List<Contract> findPage(Page page) throws Exception {
        return contractMapper.findPage(page);
    }

    @Override
    public List<Contract> findByCondition(Map<String, Object> paraMap) throws Exception {
        return contractMapper.findByCondition(paraMap);
    }

    @Override
    public Contract get(Serializable contract_id) throws Exception {
        return contractMapper.get(contract_id);
    }

    @Override
    public void insert(Contract contract) throws Exception {
        contractMapper.insert(contract);
    }

    @Override
    public void update(Contract contract) throws Exception {
        contractMapper.update(contract);

    }

    @Override
    public void deleteById(Serializable contract_id) throws Exception {
        contractMapper.deleteById(contract_id);
    }

    @Override
    public void delete(Serializable[] ids) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ids.length; i++) {
            if (i==(ids.length-1)){
                sb.append("'"+ids[i]+"'");
                break;//并且跳出当前循环
            }
            sb.append("'"+ids[i]+"'").append(", ");
        }
        contractMapper.delete(sb.toString());
    }

    /**
     *  private String contract_state;//状态1未完成  0完成
     * @param ids
     * @throws Exception
     */
    @Override
    public void updateStopState(Serializable[] ids) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ids.length; i++) {
            if (i==(ids.length-1)){
                sb.append("'"+ids[i]+"'");
                break;//并且跳出当前循环
            }
            sb.append("'"+ids[i]+"'").append(", ");
        }
        contractMapper.updateStopState(sb.toString());
    }

    /**
     *  private String contract_state;//状态1未完成  0完成
     * @param ids
     * @throws Exception
     */
    @Override
    public void updateStartState(Serializable[] ids) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ids.length; i++) {
            if (i==(ids.length-1)){
                sb.append("'"+ids[i]+"'");
                break;//并且跳出当前循环
            }
            sb.append("'"+ids[i]+"'").append(", ");
        }
        contractMapper.updateStartState(sb.toString());
    }

    /**
     *  private String contract_state;//状态1未完成  0完成
     * @param contract
     * @throws Exception
     */
    @Override
    public void updateState(Contract contract) throws Exception {
        if ("1".equals(contract.getContract_state())){
            contract.setContract_state("0");;//完成
        }else{
            contract.setContract_state("1");;//未完成
        }
        contractMapper.updateState(contract);
    }

    @Override
    public Integer selectCount(Map<String, Object> paraMap) throws Exception {
        return contractMapper.selectCount(paraMap);
    }

    /**
     * 设置合同总金额
     * 1先查出来 货物+附件 总金额=有子查询
     * 2再设置进去，更新合同总金额
     * @param contract_id
     */
    @Override
    public void updateTotal(Serializable contract_id)throws Exception {
        //这里必须不是包装类
        Double total = contractMapper.selectTotal(contract_id);
        Contract contract = new Contract();
        contract.setContract_id(contract_id+"");
        contract.setTotal_price(total);
        update(contract);
    }

    /**
     * 出货统计表方法
     * @param inputDate
     * @return
     * @throws Exception
     */
    @Override
    public List<ContractVO> selectOutProductByDate(String inputDate) throws Exception {
        return contractMapper.selectOutProductByDate(inputDate);
    }
}
