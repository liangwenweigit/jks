package com.fly.jks.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.fly.jks.domain.Factory;
import com.fly.jks.mapper.FactoryMapper;
import com.fly.jks.pagination.Page;
import com.fly.jks.service.FactoryService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 生产厂家业务层实现类
 */
@Service//必须添加这个注解，注入spring中
public class FactoryServiceImpl implements FactoryService {

	@Autowired
	private FactoryMapper factoryMapper;



    public void setFactoryMapper(FactoryMapper factoryMapper) {
        this.factoryMapper = factoryMapper;
    }

    @Override
	public List<Factory> findPage(Page page)throws Exception{
		return factoryMapper.findPage(page);
	}

	@Override
	public List<Factory> find(HashMap<String, Object> paraMap)throws Exception{
		return factoryMapper.find(paraMap);
	}
	@Override
	public Factory get(Serializable factory_id)throws Exception {
		return factoryMapper.get(factory_id);
	}

	@Override
	public void insert(Factory factory)throws Exception {
		factoryMapper.insert(factory);
	}

	@Override
	public void update(Factory factory)throws Exception {
		factoryMapper.update(factory);
	}

	@Override
	public void deleteById(Serializable factory_id)throws Exception {
		factoryMapper.deleteById(factory_id);
	}

    /**
     * 批量删除 删除一个 拼接sql
     * @param ids
     * @throws Exception
     */
	@Override
	public void delete(Serializable[] ids)throws Exception {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < ids.length; i++) {
			if (i==(ids.length-1)){
				sb.append("'"+ids[i]+"'");
				break;//并且跳出当前循环
			}
			sb.append("'"+ids[i]+"'").append(", ");
		}
		System.out.println(sb.toString());
		factoryMapper.delete(sb.toString());
	}
}