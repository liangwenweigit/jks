package com.fly.jks.controller.cargo;

import com.fly.jks.controller.baseinfo.FactoryController;
import com.fly.jks.domain.ContractProduct;
import com.fly.jks.domain.Factory;
import com.fly.jks.service.ContractProductService;
import com.fly.jks.service.FactoryService;
import com.fly.jks.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 货物controller层
 * @author liang
 * @date 2019/2/24 - 12:43
 */
@Controller
@RequestMapping("/api/product")
public class ContractProductController {
    private static final Logger logger = LoggerFactory.getLogger(FactoryController.class);
    @Autowired
    private ContractProductService contractProductService;
    @Autowired
    private FactoryService factoryService;

    @RequestMapping("/get")
    @ResponseBody
    public Object get() throws Exception {
        return contractProductService.get("1");
    }

    /**
     * 转向新增页面 factoryList
     * @return
     * @throws Exception
     */
    @RequestMapping("/insert_page")
    public String insertPage(@RequestParam("contract_id") String contract_id, Model model) throws Exception {
        logger.info("转向新增页面接口被调用了");
        Map<String,Object> paraMap = new HashMap<>(0);
        paraMap.put("state","1");
        List<Factory> factoryList = factoryService.findByCondition(paraMap);
        model.addAttribute("factoryList",factoryList);
        model.addAttribute("contract_id",contract_id);
        return "/WEB-INF/pages/cargo/product/product_create.jsp";
    }

    /**
     * 新增合同货物
     *  private String accessories;//是否有附件1有 0没 这样要从页面传
     *  @RequestParam("factory_id") String factory_id厂家UUID
     *  @RequestParam("contract_id") String contract_id合同UUID
     * @param contractProduct
     * @return
     * @throws Exception
     */
    @RequestMapping("/insert")
    public String insert(ContractProduct contractProduct,String factory_id,@RequestParam("contract_id") String contract_id,Model model)throws Exception{
        logger.info("新增合同货物接口被调用了");
        if (factory_id==null || "".equals(factory_id)){
            return "redirect:/api/contract/find_page";
        }
        //根据传过来的factory_id查询一个厂家处理
System.out.println(factory_id);
        Factory factory = factoryService.get(factory_id);
        //设置厂家信息
        contractProduct.setFactory_id(factory.getFactory_id());
        contractProduct.setFactory_name(factory.getFactory_name());
        //设置货物合同UUID外键
        contractProduct.setContract_id(contract_id);
        //设置UUID（通常这些是业务，应该写在业务层）
        contractProduct.setContract_product_id(CommonUtils.getUUID());
        //设置出货状态1未完，0完成
        contractProduct.setFinshed("1");
System.out.println(contractProduct);
        contractProductService.insert(contractProduct);
        //重定向到另一个action ,新增后重定向到新增页面，用户可以继续新增，方便操作，因为货物有很多
        //这里还需要把合同id传到重定向的方法(因为方法上必须传这个参数)，由方法转发到页面,
        // 要是没有参数：下面的重定向后方法会爆错：Required String parameter 'contract_id' is not present
        model.addAttribute("contract_id",contract_id);// 或者在url后添加?contract_id="+contract_id
        return "redirect:/api/product/insert_page";
    }
}
