package com.fly.jks.mapper;

import com.fly.jks.domain.ExportExtEproduct;
import com.fly.jks.domain.ExportProduct;
import com.fly.jks.mapper.provider.ExportProductMapperDynaSQLCreater;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;

/**
 * 报运下的货物
 * @author liang
 * @date 2019/3/3 - 0:11
 */
@Mapper
//redis缓存
@CacheNamespace(implementation=com.fly.jks.cache.RedisCache.class)
public interface ExportProductMapper {

    @InsertProvider(type =ExportProductMapperDynaSQLCreater.class,method ="insert")
    public void insert(ExportProduct exportProduct) throws Exception;


}
