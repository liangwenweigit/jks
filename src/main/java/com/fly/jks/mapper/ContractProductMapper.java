package com.fly.jks.mapper;

import com.fly.jks.domain.Contract;
import com.fly.jks.domain.ContractProduct;
import com.fly.jks.mapper.provider.ContractMapperDynaSQLCreater;
import com.fly.jks.mapper.provider.ContractProductMapperDynaSQLCreater;
import com.fly.jks.pagination.Page;
import org.apache.ibatis.annotations.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 货物
 * @author liang
 * @date 2019/2/24 - 0:31
 */
public interface ContractProductMapper {

    /**
     * 分页查询
     * @param page
     * @return
     */
    @Select("select * from contract_product limit #{pageIndex},#{pageSize}")
    public List<ContractProduct> findPage(Page page)throws Exception;

    /**
     * 带条件查询，条件可以为null，既没有条件；返回list对象集合
     * @param paraMap
     * @return
     */
    public List<ContractProduct> find(Map<String, Object> paraMap)throws Exception;


    /**
     * 查询全部
     * @return
     * @throws Exception
     */
    @Select("select * from contract_product")
    public List<ContractProduct> findList()throws Exception;

    /**
     * 查询全部记录数
     * @return
     * @throws Exception
     */
    @Select("select count(*) from contract_product")
    public Integer selectCount()throws Exception;

    /**
     * 查询一条
     * @return
     * @throws Exception
     */
    @Select("select * from contract_product where contract_product_id= #{contract_id}")
    public Contract getContract(Serializable contract_product_id)throws Exception;

    /**
     * 真删除，删除一条，支持整数型和字符串类型ID
     * @param contract_product_id
     */
    @Delete("delete from contract_product where contract_product_id = #{contract_product_id}")
    public void deleteById(Serializable contract_product_id)throws Exception;

    /**
     * 批量删除/删除1条
     * @throws Exception
     */
    //@DeleteProvider(type = ContractProductMapperDynaSQLCreater.class,method = "deleteSQL")
    public void deleteContract(String sql)throws Exception;

    /**
     * 新增一条
     * @param contractProduct
     * @throws Exception
     */
    @InsertProvider(type = ContractProductMapperDynaSQLCreater.class,method = "insertSQL")
    public void insertContract(ContractProduct contractProduct)throws Exception;

    /**
     * 更新
     * @param contractProduct
     * @throws Exception
     */
    @UpdateProvider(type = ContractProductMapperDynaSQLCreater.class,method = "updateSQL")
    public void updateContract(ContractProduct contractProduct)throws Exception;

    /**
     * 更新1条合同状态  private String contract_state;//状态1未完成  0完成
     * @param contractProduct
     * @throws Exception
     */
    @Update("UPDATE contract SET contract_state = #{contract_state} WHERE contract_id = #{contract_id}")
    public void updateState(ContractProduct contractProduct) throws Exception;

    /**
     *批量/单 合同状态  private String contract_state;//状态1未完成  0完成
     * 设置成0==完成停止
     * @throws Exception
     */
    //@UpdateProvider(type = ContractProductMapperDynaSQLCreater.class,method = "updateStopStateSQL")
    public void updateStopState(String sql)throws Exception;

    /**
     *批量/单个 合同状态  private String contract_state;//状态1未完成  0完成
     * 设置成1==未完成继续
     * @throws Exception
     */
    //@UpdateProvider(type = ContractProductMapperDynaSQLCreater.class,method = "updateStartStateSQL")
    public void updateStartState(String sql)throws Exception;
}
