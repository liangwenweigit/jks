package com.fly.jks.service.impl;

import com.fly.jks.domain.*;
import com.fly.jks.domain.vo.ContractVO;
import com.fly.jks.mapper.*;
import com.fly.jks.pagination.Page;
import com.fly.jks.service.ContractService;
import com.fly.jks.service.ExportService;
import com.fly.jks.utils.CommonUtils;
import com.fly.jks.utils.UtilFuns;
import org.springframework.beans.BeanUtils;
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
    @Autowired
    private ContractProductMapper contractProductMapper;
    @Autowired
    private ExportProductMapper exportProductMapper;
    @Autowired
    private ExtCproductMapper extCproductMapper;
    @Autowired
    private ExportExtEproductMapper exportExtEproductMapper;

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
        //1）新增一个报运单 添加信息
        // 拼接报运对象的customer_contract合同或确认书号，拼接成字符串（例子：x，y），报运号。是由合同的合同号组成的 空格隔开
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
        //保存一个报运单
        exportMapper.insert(export);

        //2）循环处理报运货物信息，根据合同号查询全部货物，然后把合同下的货物数据循环搬家到 报运下的货物下去
        for (int i = 0; i <ids.length ; i++) {
            //循环某一个合同出来
            Contract contract = contractMapper.get(ids[i]);
            //根据合同号查询当前合同下的所以货物
            List<ContractProduct> contractProducts = contractProductMapper.selectContractProductByContractId(contract.getContract_id());
            //再通过循环把货物信息一个一个拿出来 //然后把合同下的货物信息 复制到 报运当下的货物里面，最后插入数据库
            for (ContractProduct c :contractProducts) {
                ExportProduct exportProduct = new ExportProduct();//新建一个报运下的货物
                exportProduct.setExport_product_id(CommonUtils.getUUID());//设置自身UUID=主键/这个不能设置c里面的UUID/因为有一个业务：当第一次报运数量一个合同可能一次报运不完，还要报运第二次，id就重复了
                //设置普通字段合同UUID，这个只是当普通字段被设置进来，后期业务，更新合同走货状态，更新合同下的货物实际出货数量/是否出货完毕 要使用
                exportProduct.setContract_id(c.getContract_id());
                exportProduct.setExport_id(export.getExport_id());//绑定报运单UUID=外键//这个报运货运属于哪个报运单
                //数据搬家，将合同下的对应的货物信息写入到报运下的货物信息中
                exportProduct.setFactory_id(c.getFactory_id());//厂家UUID=外键
                exportProduct.setFactory_name(c.getFactory_name());//厂家名称 冗余字段
                exportProduct.setProduct_no(c.getProduct_num());//货号// private String product_num;//货物编号
                exportProduct.setPacking_unit(c.getPacking_unit());//包装单位
                exportProduct.setCnumber(c.getCnumber());//数量
                exportProduct.setBox_num(c.getBox_num());//件数
                exportProduct.setPrice(c.getPrice());//单价
                //排序号，不需要，因为不一样，它是属于报运的 和合同的不一样
                //保存进去数据库=报运下的货物表
                exportProductMapper.insert(exportProduct);

                //3)循环处理报运下的货物的附件信息，根据上面循环出来的合同下的货物的UUID，查询货物下的附件，把信息搬家到 报运下的货物下的附件
                //这样原来的方法 带有分页 所以要重新在 合同下的货物下的附件新增一个根据，附件的货物外键查询所有附件的方法
                //接口方法：public List<ExtCproduct> selectByContractProductId(String contract_product_id)throws Exception;
                List<ExtCproduct> extCproducts = extCproductMapper.selectByContractProductId(c.getContract_product_id());
                //再循环把附件信息一个个拿出来 //然后把附件信息 复制到 报运下的货物下的附件里面，最后插入数据库
                for (ExtCproduct e :extCproducts) {
                    ExportExtEproduct exportExtEproduct = new ExportExtEproduct();//新建一个报运下的货物下的附件

                    //copyProperties spring 数据拷贝
                    BeanUtils.copyProperties(e,exportExtEproduct);//spring工具类，数据的拷贝
                    //下面替换和补一些字段
                    exportExtEproduct.setExt_eproduct_id(CommonUtils.getUUID());//设置自身UUID=主键
                    //外键字段必须补
                    exportExtEproduct.setExport_product_id(exportProduct.getExport_product_id());//绑定报运下的货物UUID=外键
                    //下面俩个字段和其他字段被上面spring的工具类补了
                    //exportExtEproduct.setFactory_id(e.getFactory_id());//工厂UUID=外键
                    //exportExtEproduct.setFactory_name(e.getFactory_name());//厂家名称 冗余字段

                    //保存数据库=保存到报运下的货物下的附件里面去
                    exportExtEproductMapper.insert(exportExtEproduct);
                }
            }
        }
    }
}
