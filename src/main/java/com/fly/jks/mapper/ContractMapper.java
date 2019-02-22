package com.fly.jks.mapper;

import com.fly.jks.domain.Contract;
import com.fly.jks.mapper.provider.ContractMapperDynaSQLCreater;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 购销合同mapper接口
 * @author liang
 * @date 2019/2/22 - 15:10
 */
@Mapper
//redis缓存
@CacheNamespace(implementation=com.fly.jks.cache.RedisCache.class)
public interface ContractMapper {
    /**
     * 查询全部
     * @return
     * @throws Exception
     */
    @Select("select * from contract")
    public List<Contract> findList()throws Exception;

    /**
     * 查询全部记录数
     * @return
     * @throws Exception
     */
    @Select("select count(*) from contract")
    public Integer selectCount()throws Exception;

    /**
     * 查询一条
     * @return
     * @throws Exception
     */
    @Select("select * from contract where contract_id= #{contract_id}")
    public Contract getContract(String contract_id)throws Exception;

    /**
     * 删除1条/批量删除
     * @throws Exception
     */
    public void deleteContract()throws Exception;
    //TODO

    /**
     * 新增一条
     * @param contract
     * @throws Exception
     */
    @InsertProvider(type = ContractMapperDynaSQLCreater.class,method = "insertContractSQL")
    public void insertContract(Contract contract)throws Exception;
}
