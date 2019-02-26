package com.fly.jks.controller.cargo;

import com.fly.jks.controller.baseinfo.FactoryController;
import com.fly.jks.domain.ExtCproduct;
import com.fly.jks.domain.Factory;
import com.fly.jks.pagination.Page;
import com.fly.jks.service.ExtCproductService;
import com.fly.jks.service.FactoryService;
import com.fly.jks.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 附件controller层
 * @author liang
 * @date 2019/2/26 - 13:58
 */
@Controller
@RequestMapping("/api/extcproduct")
public class ExtCproductController {
    private static final Logger logger = LoggerFactory.getLogger(FactoryController.class);
    @Autowired
    private ExtCproductService extCproductService;
    @Autowired
    private FactoryService factoryService;

    @RequestMapping("/list")
    public String listPage(@RequestParam("contract_product_id") String contract_product_id, Model model, Page page) throws Exception {
        Map<String,Object> paraMap = new HashMap<>(0);

        //每次进来都去数据库查对应的合同下的全部货物=dataList，并且是分页的，需要带分页条件
        //分页资料
        paraMap.put("contract_product_id",contract_product_id);
        Integer totalRecord = extCproductService.selectCount(paraMap);//分页查询总数条件
        //设置总记录数
        page.setTotalRecord(totalRecord);
        //设置总页数
        page.setTotalPage(page.getTotalRecord()%page.getPageSize()==0?page.getTotalRecord()/page.getPageSize():page.getTotalRecord()/page.getPageSize()+1);
        //处理分页bug，当数据少于10  或者没有数据的时候 或者等于10(每页大小)
        if (page.getTotalRecord()<=page.getPageSize()){
            page.setTotalPage(1);
        }
        //处理传过来的 页数 BUG 当页数小于0时设置成1，当页数大于 总页数时 设置为总页数
        if (page.getPageNo()<=0){
            page.setPageNo(1);
        }
        if (page.getPageNo()>page.getTotalPage()){
            page.setPageNo(page.getTotalPage());
        }
        //设置查询数据库的下标 limit pageIndex,pageSize
        page.setPageIndex((page.getPageNo()-1)*page.getPageSize());
        model.addAttribute("page",page);
        //塞查询分页条件
        paraMap.put("page",page);
        //paraMap.put("contract_product_id",contract_product_id);这个上面已经封装了
        List<ExtCproduct> dataList = extCproductService.findByCondition(paraMap);//分页查询
        model.addAttribute("contract_product_id",contract_product_id);
        model.addAttribute("dataList",dataList);
        if (dataList.size()==0){
            model.addAttribute("msg","暂时没有货物附件信息,可以点击新增添加!");
        }
        return "/WEB-INF/pages/cargo/extcproduct/extcproduct_list.jsp";
    }

    /**
     * 转向新增页面 factoryList
     * @return
     * @throws Exception
     */
    @RequestMapping("/insert_page")
    public String insertPage(@RequestParam("contract_product_id") String contract_product_id,Model model,String again) throws Exception {
        logger.info("转向新增附件页面接口被调用了");
        //判断again参数是否为空，不为空说明是添加货物成功来到这里的，提示添加成功
        if (again!=null){ //提示客户添加货物成功,如果是第一次来转态的，不会进入
            model.addAttribute("msg","添加货物成功!可以继续添加!");
        }
        Map<String,Object> paraMap = new HashMap<>(0);
        paraMap.put("state","1");
        List<Factory> factoryList = factoryService.findByCondition(paraMap);
        model.addAttribute("factoryList",factoryList);
        model.addAttribute("contract_product_id",contract_product_id);
        return "/WEB-INF/pages/cargo/extcproduct/extcproduct_create.jsp";
    }

    /**
     * 新增货物附件
     *  private String accessories;//是否有附件1有 0没 这样要从页面传
     *  @RequestParam("factory_id") String factory_id厂家UUID
     *  @RequestParam("contract_id") String contract_product_id 货物UUID
     * @param ExtCproduct
     * @return
     * @throws Exception
     */
    @RequestMapping("/insert")
    public String insert(ExtCproduct ExtCproduct,String factory_id,@RequestParam("contract_product_id") String contract_product_id,Model model,Page page)throws Exception{
        logger.info("新增附件接口被调用了");
        if (factory_id==null || "".equals(factory_id)){
            //说明用户没有选择厂家，重新查询一次启用的厂家 +合同id 再一次传送回到页面
            //准备生产厂家的下拉列表,并且是启用状态1的
            Map<String,Object> paraMap = new HashMap<>(0);
            paraMap.put("state","1");
            List<Factory> factoryList = factoryService.findByCondition(paraMap);
            model.addAttribute("factoryList",factoryList);
            model.addAttribute("contract_product_id",contract_product_id);
            //并且提示用户没有选择厂家，添加货物失败，请重新添加
            model.addAttribute("msg","没有选择厂家,添加货物失败,请重新添加!");
            return "/WEB-INF/pages/cargo/extcproduct/extcproduct_create.jsp";
        }
        //根据传过来的factory_id查询一个厂家处理
        Factory factory = factoryService.get(factory_id);
        //设置厂家信息
        ExtCproduct.setFactory_id(factory.getFactory_id());
        ExtCproduct.setFactory_name(factory.getFactory_name());
        //设置货物UUID外键
        ExtCproduct.setContract_product_id(contract_product_id);
        //设置UUID（通常这些是业务，应该写在业务层）
        ExtCproduct.setExt_cproduct_id(CommonUtils.getUUID());
        //设置出货状态1未完，0完成
        ExtCproduct.setFinshed("1");
        //保存数据库
        extCproductService.insert(ExtCproduct);

        //重定向到另一个action ,新增后重定向到新增页面，用户可以继续新增，方便操作，因为货物有很多
        //这里还需要把合同id传到重定向的方法(因为方法上必须传这个参数)，由方法转发到页面,
        // 要是没有参数：下面的重定向后方法会爆错：Required String parameter 'contract_id' is not present
        model.addAttribute("contract_product_id",contract_product_id);// 或者在url后添加?contract_id="+contract_id
        //添加again标记,说明添加了货物
        model.addAttribute("again","again");
        return "redirect:/api/extcproduct/insert_page";
    }

    //转向修改页面
    @RequestMapping("/update_page")
    public String updatePage(String ext_cproduct_id, Model model) throws Exception {
        ExtCproduct cxtCproduct = extCproductService.get(ext_cproduct_id);
        //记得合同UUID也要传回去
        model.addAttribute("cxtCproduct", cxtCproduct);
        //准备生产厂家的下拉列表,并且是启用状态1的
        Map<String,Object> paraMap = new HashMap<>(0);
        paraMap.put("state","1");
        List<Factory> factoryList = factoryService.findByCondition(paraMap);
        model.addAttribute("factoryList", factoryList);
        return "/WEB-INF/pages/cargo/extcproduct/extcproduct_update.jsp";
    }

    //修改保存
    @RequestMapping("/update")
    public String update(ExtCproduct extCproduct,Model model) throws Exception {
        //这个参数是传给下一个方法的
        model.addAttribute("contract_product_id", extCproduct.getContract_product_id());
        //放在用户修改了厂家，所以在这里要把厂家名字也修改了
        Factory factory = factoryService.get(extCproduct.getFactory_id());
        extCproduct.setFactory_name(factory.getFactory_name());
        extCproductService.update(extCproduct);
        //修改完回到货物列表，记得合同UUID也要传回去
        return "redirect:/api/extcproduct/list";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("ext_cproduct_id") String ext_cproduct_id,@RequestParam("contract_product_id")String contract_product_id,Model model) throws Exception {
        //这个参数是传给下一个方法的
        model.addAttribute("contract_product_id", contract_product_id);
        //删除一条
        extCproductService.deleteById(ext_cproduct_id);
        //修改完回到新增页面，记得合同UUID也要传回去
        return "redirect:/api/extcproduct/list";
    }
}
