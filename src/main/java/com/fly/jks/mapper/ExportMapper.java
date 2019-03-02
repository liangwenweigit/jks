package com.fly.jks.mapper;

import com.fly.jks.domain.Export;
import com.fly.jks.mapper.provider.ExportMapperDynaSQLCreater;
import com.fly.jks.pagination.Page;
import org.apache.ibatis.annotations.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author liang
 * @date 2019/3/1 - 22:24
 */
@Mapper
//redis缓存
@CacheNamespace(implementation=com.fly.jks.cache.RedisCache.class)
public interface ExportMapper {

    /**
     * 分页查询
     * @param page
     * @return
     */
    @Select("select * from export limit #{pageIndex},#{pageSize}")
    public List<Export> findPage(Page page)throws Exception;

    /**
     * 带条件查询，并且分页，下面三个参数必传
     * @param paraMap
     * @return
     */
    @Select("select * from export where export_id = #{export_id} limit #{page.pageIndex},#{page.pageSize}")
    public List<Export> findByCondition(Map<String, Object> paraMap)throws Exception;

    /**
     * 查询全部
     * @return
     * @throws Exception
     */
    @Select("select * from export")
    public List<Export> findList()throws Exception;

    /**
     * 查询全部记录数 带条件查询
     * @return
     * @throws Exception
     */
    @Select("select count(*) from export where export_id = #{export_id}")
    public Integer selectCount(Map<String, Object> paraMap)throws Exception;

    /**
     * 查询一条
     * @return
     * @throws Exception
     */
    @Select("select * from export where export_id= #{export_id}")
    public Export get(Serializable export_id)throws Exception;

    /**
     * 真删除，删除一条，支持整数型和字符串类型ID
     * @param export_id
     */
    @Delete("delete from export where export_id = #{export_id}")
    public void deleteById(Serializable export_id)throws Exception;

    /**
     * 批量删除/删除1条
     * @throws Exception
     */
    @DeleteProvider(type = ExportMapperDynaSQLCreater.class,method = "deleteSQL")
    public void delete(String sql)throws Exception;

    /**
     * 新增一条
     * @param Export
     * @throws Exception
     */
    @InsertProvider(type = ExportMapperDynaSQLCreater.class,method = "insertSQL")
    public void insert(Export Export)throws Exception;

    /**
     * 更新
     * @param Export
     * @throws Exception
     */
    @UpdateProvider(type = ExportMapperDynaSQLCreater.class,method = "updateSQL")
    public void update(Export Export)throws Exception;

    /**
     * 更新1条0-草稿 1-已上报 2-装箱 3-委托 4-发票 5-财务
     * @param Export
     * @throws Exception
     */
    @Update("UPDATE export SET state = #{state} WHERE export_id = #{export_id}")
    public void updateState(Export Export) throws Exception;


    @Delete("delete from export where export_id = #{export_id}")
    public void deleteByContractProductId(Serializable export_id)throws Exception;
}
