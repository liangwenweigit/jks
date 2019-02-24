package com.fly.jks.service;

import com.fly.jks.pagination.Page;
import java.io.Serializable;
import java.util.Map;
import java.util.List;

/**
 * 封装通用方法到baseService接口
 * @author liang
 * @date 2019/2/17 - 21:19
 */
public interface BaseService<T> {

    public List<T> findPage(Page page)throws Exception;				     //分页查询
    public List<T> findByCondition(Map<String, Object> paraMap)throws Exception;//带条件查询，条件可以为null，既没有条件；返回list对象集合
    public T get(Serializable id)throws Exception;					     //只查询一个，常用于修改
    public void insert(T entity)throws Exception;					     //插入，用实体作为参数
    public void update(T entity)throws Exception;					     //修改，用实体作为参数
    public void deleteById(Serializable id)throws Exception;		     //按id删除，删除一条；支持整数型和字符串类型ID
    public void delete(Serializable[] ids)throws Exception;			     //批量删除；支持整数型和字符串类型ID
    public void updateStopState(Serializable[] ids)throws Exception;     //批量/单个停用，类似假删除，业务删除
    public void updateStartState(Serializable[] ids)throws Exception;    //批量/单个启用
    public void updateState(T entity)throws Exception;                   //单个更新状态 启用/停用，需要在业务层判断
    public Integer selectCount()throws Exception;                        //查询总条数
}
