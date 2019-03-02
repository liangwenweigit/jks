package com.fly.jks.service;

import com.fly.jks.domain.Export;

import java.io.Serializable;

/**
 * 报运下的业务层接口
 * @author liang
 * @date 2019/3/1 - 23:27
 */
public interface ExportService extends BaseService<Export>{

    /**
     * 报运=新增==直接进行后台保存==保存后可以在报运列表看到已经报运的货物
     * @param ids
     */
    public void inserts(String[] ids)throws Exception;
}
