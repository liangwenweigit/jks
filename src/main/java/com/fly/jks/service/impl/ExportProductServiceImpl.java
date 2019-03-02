package com.fly.jks.service.impl;

import com.fly.jks.domain.ExportProduct;
import com.fly.jks.mapper.ExportProductMapper;
import com.fly.jks.pagination.Page;
import com.fly.jks.service.ExportProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 报运下的货物业务层实现类
 * @author liang
 * @date 2019/3/3 - 0:46
 */
@Service
public class ExportProductServiceImpl implements ExportProductService {
    @Autowired
    private ExportProductMapper exportProductMapper;

    @Override
    public List<ExportProduct> findPage(Page page) throws Exception {
        return null;
    }

    @Override
    public List<ExportProduct> findByCondition(Map<String, Object> paraMap) throws Exception {
        return null;
    }

    @Override
    public ExportProduct get(Serializable id) throws Exception {
        return null;
    }

    @Override
    public void insert(ExportProduct entity) throws Exception {
        exportProductMapper.insert(entity);
    }

    @Override
    public void update(ExportProduct entity) throws Exception {

    }

    @Override
    public void deleteById(Serializable id) throws Exception {

    }

    @Override
    public void delete(Serializable[] ids) throws Exception {

    }

    @Override
    public void updateStopState(Serializable[] ids) throws Exception {

    }

    @Override
    public void updateStartState(Serializable[] ids) throws Exception {

    }

    @Override
    public void updateState(ExportProduct entity) throws Exception {

    }

    @Override
    public Integer selectCount(Map<String, Object> paraMap) throws Exception {
        return null;
    }
}
