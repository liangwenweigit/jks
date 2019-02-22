package com.fly.jks.mapper;
import com.fly.jks.domain.Factory;
import com.fly.jks.mapper.provider.FactoryMapperDynaSQLCreater;
import com.fly.jks.pagination.Page;
import org.apache.ibatis.annotations.*;
import java.io.Serializable;
import java.util.Map;
import java.util.List;
/**
 * 生产厂家mapper接口类
 * @author liang
 * @date 2019/2/17 - 20:54
 */
@Mapper
//redis缓存
@CacheNamespace(implementation=com.fly.jks.cache.RedisCache.class)
public interface FactoryMapper{
    /**
     * 分页查询
     * @param page
     * @return
     */
    @Select("select * from factory limit #{pageIndex},#{pageSize}")
    public List<Factory> findPage(Page page)throws Exception;

    /**
     * 带条件查询，条件可以为null，既没有条件；返回list对象集合
     * @param paraMap
     * @return
     */
    public List<Factory> find(Map<String, Object> paraMap)throws Exception;
    //TODO
    /**
     *只查询一个，常用于修改
     * @param factory_id
     * @return
     */
    @Select("select * from factory where factory_id = #{factory_id}")
    public Factory get(Serializable factory_id)throws Exception;

    /**
     * 插入，用实体作为参数
     * @param factory
     */
    @InsertProvider(type = FactoryMapperDynaSQLCreater.class,method = "insertSQL")
    public void insert(Factory factory)throws Exception;

    /**
     * 修改，用实体作为参数
     * @param factory
     */
    @UpdateProvider(type = FactoryMapperDynaSQLCreater.class,method = "updateSQL")
    public void update(Factory factory)throws Exception;

    /**
     * 真删除，删除一条，支持整数型和字符串类型ID
     * @param factory_id
     */
    @Delete("delete from factory where factory_id = #{factory_id}")
    public void deleteById(Serializable factory_id)throws Exception;

    /**
     * 真批量删除/删除一个。支持整数型和字符串类型ID
     * @param sql
     */
    @DeleteProvider(type = FactoryMapperDynaSQLCreater.class,method = "deleteSQL")
    public void delete(String sql)throws Exception;

    /**
     *批量/单个停用，类似假删除，业务删除
     * @throws Exception
     */
    @UpdateProvider(type = FactoryMapperDynaSQLCreater.class,method = "updateStopStateSQL")
    public void updateStopState(String sql)throws Exception;

    /**
     *批量/单个启用
     * @throws Exception
     */
    @UpdateProvider(type = FactoryMapperDynaSQLCreater.class,method = "updateStartStateSQL")
    public void updateStartState(String sql)throws Exception;

    /**
     * 更新 启用/停用
     * @param factory
     * @throws Exception
     */
    @Update("UPDATE factory SET state = #{state} WHERE factory_id = #{factory_id}")
    public void updateState(Factory factory) throws Exception;

    /**
     * 查询总条数
     * @return
     * @throws Exception
     */
    @Select("select count(*) from factory")
    public Integer selectCount() throws Exception;
}
