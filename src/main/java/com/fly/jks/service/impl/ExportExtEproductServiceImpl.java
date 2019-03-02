package com.fly.jks.service.impl;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.fly.jks.domain.ExportExtEproduct;
import com.fly.jks.mapper.ExportExtEproductMapper;
import com.fly.jks.pagination.Page;
import com.fly.jks.service.ExportExtEproductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *报运下的货物下的附件的业务层实现类
 * @author liang
 * @date 2019/3/3 - 0:48
 */
@Service
public class ExportExtEproductServiceImpl implements ExportExtEproductService {

    @Autowired
    private ExportExtEproductMapper exportExtEproductMapper;

    @Override
    public List<ExportExtEproduct> findPage(Page page) throws Exception {
        return null;
    }

    @Override
    public List<ExportExtEproduct> findByCondition(Map<String, Object> paraMap) throws Exception {
        return null;
    }

    @Override
    public ExportExtEproduct get(Serializable id) throws Exception {
        return null;
    }

    @Override
    public void insert(ExportExtEproduct entity) throws Exception {
        exportExtEproductMapper.insert(entity);
    }

    @Override
    public void update(ExportExtEproduct entity) throws Exception {

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
    public void updateState(ExportExtEproduct entity) throws Exception {

    }

    @Override
    public Integer selectCount(Map<String, Object> paraMap) throws Exception {
        return null;
    }
}
