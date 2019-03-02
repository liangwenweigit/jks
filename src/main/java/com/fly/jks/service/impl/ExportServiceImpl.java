package com.fly.jks.service.impl;

import com.fly.jks.domain.Contract;
import com.fly.jks.domain.Export;
import com.fly.jks.domain.vo.ContractVO;
import com.fly.jks.mapper.ContractMapper;
import com.fly.jks.mapper.ExportMapper;
import com.fly.jks.pagination.Page;
import com.fly.jks.service.ContractService;
import com.fly.jks.service.ExportService;
import com.fly.jks.utils.CommonUtils;
import com.fly.jks.utils.UtilFuns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 报运下的业务层接口实现类
 * @author liang
 * @date 2019/3/1 - 23:28
 */
@Service
public class ExportServiceImpl implements ExportService{

    @Autowired
    private ExportMapper exportMapper;
    @Autowired
    private ContractMapper contractMapper;

    @Override
    public List<Export> findPage(Page page) throws Exception {
        return exportMapper.findPage(page);
    }

    @Override
    public List<Export> findByCondition(Map<String, Object> paraMap) throws Exception {
        return exportMapper.findByCondition(paraMap);
    }

    @Override
    public Export get(Serializable id) throws Exception {
        return exportMapper.get(id);
    }

    @Override
    public void insert(Export entity) throws Exception {
        exportMapper.insert(entity);
    }

    @Override
    public void update(Export entity) throws Exception {
        exportMapper.update(entity);
    }

    @Override
    public void deleteById(Serializable id) throws Exception {
        exportMapper.deleteById(id);
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
        exportMapper.delete(sb.toString());
    }

    @Override
    public void updateStopState(Serializable[] ids) throws Exception {
        //TODO
    }

    @Override
    public void updateStartState(Serializable[] ids) throws Exception {
        //TODO
    }

    @Override
    public void updateState(Export entity) throws Exception {
        exportMapper.update(entity);
    }

    @Override
    public Integer selectCount(Map<String, Object> paraMap) throws Exception {
        return exportMapper.selectCount(paraMap);
    }

    /**
     * 报运=新增==直接进行后台保存==保存后可以在报运列表看到已经报运的货物
     * @param ids
     */
    @Override
    public void inserts(String[] ids)throws Exception {
        /*
         * 步骤：
         * 1、根据合同id获得合同对象，获取合同号
         * 2、将合同下的货物信息搬家到报运下的货物表中
         * 3、将合同下的附件信息搬家到报运下的附件表中
         */
        //拼接报运对象的customer_contract合同或确认书号，拼接成字符串（例子：x，y），报运号。是由合同的合同号组成的 空格隔开
        String contractNos = "";
        for(int i=0;i<ids.length;i++){
            Contract contract = contractMapper.get(ids[i]);
            contractNos += contract.getContract_no() + " ";//以空格作为分隔符
        }
        contractNos = UtilFuns.delLastChar(contractNos);//工具类，删除最后一个字符

        //构建报运对象
        Export export = new Export();
        //补全报运UUID
        export.setExport_id(CommonUtils.getUUID());
        //x,y//contract_ids;//合同UUID集合 打断设计
        export.setContract_ids(UtilFuns.joinStr(ids, ","));//工具类，拼串。以‘,’号分隔
        //设置合同或者确认号，上面一开始已经拼接好了
        export.setCustomer_contract(contractNos);
        //状态：0-草稿 1-已上报 2-装箱 3-委托 4-发票 5-财务
        export.setState("0");//初始状态就是0草稿



        exportMapper.insert(export);

    }
}
