package com.fly.jks.service.impl;

import com.fly.jks.domain.Contract;
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
    public List<Contract> find(Map<String, Object> paraMap) throws Exception {
        return contractMapper.find(paraMap);
    }

    @Override
    public Contract get(Serializable id) throws Exception {
        return contractMapper.getContract(id);
    }

    @Override
    public void insert(Contract contract) throws Exception {
        contractMapper.insertContract(contract);
    }

    @Override
    public void update(Contract contract) throws Exception {
        contractMapper.updateContract(contract);

    }

    @Override
    public void deleteById(Serializable id) throws Exception {
        contractMapper.deleteById(id);
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
        contractMapper.deleteContract(sb.toString());

    }

    @Override
    public void updateStopState(Serializable[] ids) throws Exception {
        //TODO
    }

    @Override
    public void updateStartState(Serializable[] ids) throws Exception {
        //TODO
    }

    @Override
    public void updateState(Contract entity) throws Exception {
        //TODO
    }

    @Override
    public Integer selectCount() throws Exception {
        return contractMapper.selectCount();
    }
}
