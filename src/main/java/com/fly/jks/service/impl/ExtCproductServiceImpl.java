package com.fly.jks.service.impl;

import com.fly.jks.domain.ExtCproduct;
import com.fly.jks.mapper.ExtCproductMapper;
import com.fly.jks.pagination.Page;
import com.fly.jks.service.ExtCproductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author liang
 * @date 2019/2/26 - 12:10
 */
@Service
public class ExtCproductServiceImpl implements ExtCproductService{

    @Autowired
    private ExtCproductMapper extCproductMapper;

    @Override
    public List<ExtCproduct> findPage(Page page) throws Exception {
        return extCproductMapper.findPage(page);
    }

    @Override
    public List<ExtCproduct> findByCondition(Map<String, Object> paraMap) throws Exception {
        return extCproductMapper.findByCondition(paraMap);
    }

    @Override
    public ExtCproduct get(Serializable ext_cproduct_id) throws Exception {
        return extCproductMapper.get(ext_cproduct_id);
    }

    @Override
    public void insert(ExtCproduct ExtCproduct) throws Exception {
        //计算总金额 先判断传过来的 数量 和单价 是不是不为空,为空不计算
        if(ExtCproduct.getCnumber()!=null && !"".equals(ExtCproduct.getCnumber()) &&ExtCproduct.getPrice()!=null && !"".equals(ExtCproduct.getPrice())){
            //计算，因为误差问题需要转换计算
            /**
             Double计算BigDecimal
             BigDecimal price = new BigDecimal(book.getPrice()+"");
             BigDecimal counts = new BigDecimal(count+"");
             乘法price.multiply(counts).doubleValue();
             加法price.add(counts).doubleValue();
             减法price.subtract(counts).doubleValue();
             时间存字符串
             */
            BigDecimal price = new BigDecimal(ExtCproduct.getPrice()+"");
            BigDecimal cnunmber = new BigDecimal(ExtCproduct.getCnumber()+"");
            ExtCproduct.setAmount(price.multiply(cnunmber).doubleValue());
        }
        extCproductMapper.insert(ExtCproduct);
    }

    @Override
    public void update(ExtCproduct ExtCproduct) throws Exception {
        //计算总金额 先判断传过来的 数量 和单价 是不是不为空,为空不计算
        if(ExtCproduct.getCnumber()!=null && !"".equals(ExtCproduct.getCnumber()) &&ExtCproduct.getPrice()!=null && !"".equals(ExtCproduct.getPrice())){
            //计算，因为误差问题需要转换计算
            /**
             Double计算BigDecimal
             BigDecimal price = new BigDecimal(book.getPrice()+"");
             BigDecimal counts = new BigDecimal(count+"");
             乘法price.multiply(counts).doubleValue();
             加法price.add(counts).doubleValue();
             减法price.subtract(counts).doubleValue();
             时间存字符串
             */
            BigDecimal price = new BigDecimal(ExtCproduct.getPrice()+"");
            BigDecimal cnunmber = new BigDecimal(ExtCproduct.getCnumber()+"");
            ExtCproduct.setAmount(price.multiply(cnunmber).doubleValue());
        }else{//说明上面2样有空，处理BUG，把总价也改成0.0,因为用的是mybatis，更新的时候不能设置成null，设置成null，动态sql不会更新这个字段，作用直接赋值0.0/0长度的字符串
            ExtCproduct.setAmount(0.0);
        }
        extCproductMapper.update(ExtCproduct);
    }

    @Override
    public void deleteById(Serializable ext_cproduct_id) throws Exception {
        extCproductMapper.deleteById(ext_cproduct_id);
    }

    @Override
    public void delete(Serializable[] ids) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ids.length; i++) {
            if (i==(ids.length-1)){
                sb.append("'"+ids[i]+"'");
                break;//并且跳出当前循环
            }
            sb.append("'"+ids[i]+"'").append(", ");
        }
        extCproductMapper.delete(sb.toString());
    }

    @Override
    public void updateStopState(Serializable[] ids) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ids.length; i++) {
            if (i==(ids.length-1)){
                sb.append("'"+ids[i]+"'");
                break;//并且跳出当前循环
            }
            sb.append("'"+ids[i]+"'").append(", ");
        }
        extCproductMapper.updateStopState(sb.toString());
    }

    @Override
    public void updateStartState(Serializable[] ids) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ids.length; i++) {
            if (i==(ids.length-1)){
                sb.append("'"+ids[i]+"'");
                break;//并且跳出当前循环
            }
            sb.append("'"+ids[i]+"'").append(", ");
        }
        extCproductMapper.updateStartState(sb.toString());
    }

    @Override
    public void updateState(ExtCproduct ExtCproduct) throws Exception {

        if ("1".equals(ExtCproduct.getFinshed())){
            ExtCproduct.setFinshed("0");//出货完毕
        }else{
            ExtCproduct.setFinshed("1");//出货未完
        }
        extCproductMapper.updateState(ExtCproduct);
    }

    @Override
    public Integer selectCount(Map<String, Object> paraMap) throws Exception {
        return extCproductMapper.selectCount(paraMap);
    }

    /**
     * 级联删除 根据货物ID删除所属附件
     * @param contract_product_id
     */
    @Override
    public void deleteByContractProductId(Serializable contract_product_id)throws Exception {
        extCproductMapper.deleteByContractProductId(contract_product_id);
    }
}
