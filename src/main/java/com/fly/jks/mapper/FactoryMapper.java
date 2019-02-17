package com.fly.jks.mapper;
import com.fly.jks.domain.Factory;
import com.fly.jks.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
/**
 * 生产厂家mapper接口类
 * @author liang
 * @date 2019/2/17 - 20:54
 */
@Mapper
public interface FactoryMapper{
    /**
     * 分页查询
     * @param page
     * @return
     */
    public List<Factory> findPage(Page page)throws Exception;

    /**
     * 带条件查询，条件可以为null，既没有条件；返回list对象集合
     * @param paraMap
     * @return
     */
    public List<Factory> find(HashMap<String, Object> paraMap)throws Exception;

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
    public void insert(Factory factory)throws Exception;

    /**
     * 修改，用实体作为参数
     * @param factory
     */
    public void update(Factory factory)throws Exception;

    /**
     * 按id删除，删除一条；支持整数型和字符串类型ID
     * @param factory_id
     */
    public void deleteById(Serializable factory_id)throws Exception;

    /**
     * 批量删除；支持整数型和字符串类型ID
     * @param ids
     */
    public void delete(Serializable[] ids)throws Exception;

}
