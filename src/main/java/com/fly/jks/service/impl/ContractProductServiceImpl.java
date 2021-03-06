package com.fly.jks.service.impl;

import com.fly.jks.domain.ContractProduct;
import com.fly.jks.mapper.ContractProductMapper;
import com.fly.jks.pagination.Page;
import com.fly.jks.service.ContractProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 货物业务层实现类
 * @author liang
 * @date 2019/2/24 - 12:12
 */
@Service
public class ContractProductServiceImpl implements ContractProductService{
    @Autowired
    private ContractProductMapper contractProductMapper;

    @Override
    public List<ContractProduct> findPage(Page page) throws Exception {
        return contractProductMapper.findPage(page);
    }

    @Override
    public List<ContractProduct> findByCondition(Map<String, Object> paraMap) throws Exception {
        return contractProductMapper.findByCondition(paraMap);
    }

    @Override
    public ContractProduct get(Serializable contract_product_id) throws Exception {
        return contractProductMapper.get(contract_product_id);
    }

    @Override
    public void insert(ContractProduct contractProduct) throws Exception {
        //计算总金额 先判断传过来的 数量 和单价 是不是不为空,为空不计算
        if(contractProduct.getCnumber()!=null && !"".equals(contractProduct.getCnumber()) &&contractProduct.getPrice()!=null && !"".equals(contractProduct.getPrice())){
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
            BigDecimal price = new BigDecimal(contractProduct.getPrice()+"");
            BigDecimal cnunmber = new BigDecimal(contractProduct.getCnumber()+"");
            contractProduct.setAmount(price.multiply(cnunmber).doubleValue());
        }
        contractProductMapper.insert(contractProduct);
    }

    @Override
    public void update(ContractProduct contractProduct) throws Exception {
        //计算总金额 先判断传过来的 数量 和单价 是不是不为空,为空不计算
        if(contractProduct.getCnumber()!=null && !"".equals(contractProduct.getCnumber()) &&contractProduct.getPrice()!=null && !"".equals(contractProduct.getPrice())){
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
            BigDecimal price = new BigDecimal(contractProduct.getPrice()+"");
            BigDecimal cnunmber = new BigDecimal(contractProduct.getCnumber()+"");
            contractProduct.setAmount(price.multiply(cnunmber).doubleValue());
        }else{//说明上面2样有空，处理BUG，把总价也改成0.0,因为用的是mybatis，更新的时候不能设置成null，设置成null，动态sql不会更新这个字段，作用直接赋值0.0/0长度的字符串
            contractProduct.setAmount(0.0);
        }
        contractProductMapper.update(contractProduct);
    }

    @Override
    public void deleteById(Serializable contract_product_id) throws Exception {
        contractProductMapper.deleteById(contract_product_id);
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
        contractProductMapper.delete(sb.toString());
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
        contractProductMapper.updateStopState(sb.toString());
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
        contractProductMapper.updateStartState(sb.toString());
    }

    @Override
    public void updateState(ContractProduct contractProduct) throws Exception {

        if ("1".equals(contractProduct.getFinshed())){
            contractProduct.setFinshed("0");//出货完毕
        }else{
            contractProduct.setFinshed("1");//出货未完
        }
        contractProductMapper.updateState(contractProduct);
    }

    @Override
    public Integer selectCount(Map<String, Object> paraMap) throws Exception {
        return contractProductMapper.selectCount(paraMap);
    }

    /**
     * 根据合同号查询货物
     * @param id
     * @return
     */
    @Override
    public List<ContractProduct> selectContractProductByContractId(Serializable id)throws Exception {
        return contractProductMapper.selectContractProductByContractId(id);
    }
}
