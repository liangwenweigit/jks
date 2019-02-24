package com.fly.jks.mapper;

import com.fly.jks.domain.Contract;
import com.fly.jks.mapper.provider.ContractMapperDynaSQLCreater;
import com.fly.jks.pagination.Page;
import org.apache.ibatis.annotations.*;
import java.io.Serializable;
import java.util.Map;
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
     * 分页查询
     * @param page
     * @return
     */
    @Select("select * from contract limit #{pageIndex},#{pageSize}")
    public List<Contract> findPage(Page page)throws Exception;

    /**
     * 带条件查询，条件可以为null，既没有条件；返回list对象集合
     * @param paraMap
     * @return
     */
    public List<Contract> findByCondition(Map<String, Object> paraMap)throws Exception;
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
    public Contract get(Serializable contract_id)throws Exception;

    /**
     * 真删除，删除一条，支持整数型和字符串类型ID
     * @param factory_id
     */
    @Delete("delete from contract where contract_id = #{contract_id}")
    public void deleteById(Serializable factory_id)throws Exception;

    /**
     * 批量删除/删除1条
     * @throws Exception
     */
    @DeleteProvider(type = ContractMapperDynaSQLCreater.class,method = "deleteSQL")
    public void delete(String sql)throws Exception;

    /**
     * 新增一条
     * @param contract
     * @throws Exception
     */
    @InsertProvider(type = ContractMapperDynaSQLCreater.class,method = "insertSQL")
    public void insert(Contract contract)throws Exception;

    /**
     * 更新
     * @param contract
     * @throws Exception
     */
    @UpdateProvider(type = ContractMapperDynaSQLCreater.class,method = "updateSQL")
    public void update(Contract contract)throws Exception;

    /**
     * 更新1条合同状态  private String contract_state;//状态1未完成  0完成
     * @param contract
     * @throws Exception
     */
    @Update("UPDATE contract SET contract_state = #{contract_state} WHERE contract_id = #{contract_id}")
    public void updateState(Contract contract) throws Exception;

    /**
     *批量/单 合同状态  private String contract_state;//状态1未完成  0完成
     * 设置成0==完成停止
     * @throws Exception
     */
    @UpdateProvider(type = ContractMapperDynaSQLCreater.class,method = "updateStopStateSQL")
    public void updateStopState(String sql)throws Exception;

    /**
     *批量/单个 合同状态  private String contract_state;//状态1未完成  0完成
     * 设置成1==未完成继续
     * @throws Exception
     */
    @UpdateProvider(type = ContractMapperDynaSQLCreater.class,method = "updateStartStateSQL")
    public void updateStartState(String sql)throws Exception;
}
