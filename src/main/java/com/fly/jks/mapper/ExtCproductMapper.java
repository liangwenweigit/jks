package com.fly.jks.mapper;

import com.fly.jks.domain.ContractProduct;
import com.fly.jks.mapper.provider.ContractProductMapperDynaSQLCreater;
import com.fly.jks.mapper.provider.ExtCproductMapperDynaSQLCreater;
import com.fly.jks.pagination.Page;
import org.apache.ibatis.annotations.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author liang
 * @date 2019/2/26 - 2:46
 */
@Mapper
//redis缓存
@CacheNamespace(implementation=com.fly.jks.cache.RedisCache.class)
public interface ExtCproductMapper {

    /**
     * 分页查询
     * @param page
     * @return
     */
    @Select("select * from contract_product limit #{pageIndex},#{pageSize}")
    public List<ContractProduct> findPage(Page page)throws Exception;

    /**
     * 带条件查询，并且分页，下面三个参数必传
     * @param paraMap
     * @return
     */
    @Select("select * from contract_product where contract_id = #{contract_id} limit #{page.pageIndex},#{page.pageSize}")
    public List<ContractProduct> findByCondition(Map<String, Object> paraMap)throws Exception;

    /**
     * 查询全部
     * @return
     * @throws Exception
     */
    @Select("select * from contract_product")
    public List<ContractProduct> findList()throws Exception;

    /**
     * 查询全部记录数 带条件查询
     * @return
     * @throws Exception
     */
    @Select("select count(*) from contract_product where contract_id = #{contract_id}")
    public Integer selectCount(Map<String, Object> paraMap)throws Exception;

    /**
     * 查询一条
     * @return
     * @throws Exception
     */
    @Select("select * from contract_product where contract_product_id= #{contract_product_id}")
    public ContractProduct get(Serializable contract_product_id)throws Exception;

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
    @DeleteProvider(type = ExtCproductMapperDynaSQLCreater.class,method = "deleteSQL")
    public void delete(String sql)throws Exception;

    /**
     * 新增一条
     * @param contractProduct
     * @throws Exception
     */
    @InsertProvider(type = ExtCproductMapperDynaSQLCreater.class,method = "insertSQL")
    public void insert(ContractProduct contractProduct)throws Exception;

    /**
     * 更新
     * @param contractProduct
     * @throws Exception
     */
    @UpdateProvider(type = ExtCproductMapperDynaSQLCreater.class,method = "updateSQL")
    public void update(ContractProduct contractProduct)throws Exception;

    /**
     * 更新1条出货状态 private String finshed;//是否出货完毕1未完 0完毕
     * @param contractProduct
     * @throws Exception
     */
    @Update("UPDATE contract SET finshed = #{finshed} WHERE contract_product_id = #{contract_product_id}")
    public void updateState(ContractProduct contractProduct) throws Exception;

    /**
     * 批量/单 出货状态 private String finshed;//是否出货完毕1未完 0完毕
     * 设置成0==完毕
     * @throws Exception
     */
    @UpdateProvider(type = ExtCproductMapperDynaSQLCreater.class,method = "updateStopStateSQL")
    public void updateStopState(String sql)throws Exception;

    /**
     * 批量/单 出货状态 private String finshed;//是否出货完毕1未完 0完毕
     * 设置成1==未完
     * @throws Exception
     */
    @UpdateProvider(type = ExtCproductMapperDynaSQLCreater.class,method = "updateStartStateSQL")
    public void updateStartState(String sql)throws Exception;

}
