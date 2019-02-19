package com.fly.jks.controller;

import com.fly.jks.domain.Factory;
import com.fly.jks.pagination.Page;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.Serializable;
import java.util.List;

/**
 * 生成厂家controller层
 * @author liang
 * @date 2019/2/17 - 23:35
 */
@Controller
@RequestMapping("/api/factory")
public class FactoryController {

    private static final Logger logger = LoggerFactory.getLogger(FactoryController.class);
    @Autowired
    private FactoryService factoryService;

    /**
     * 查询一个
     * http://localhost:8080/jks/api/factory/get_by_id?factory_id=111
     * @param factory_id
     * @return
     * @throws Exception
     */
    @RequestMapping("/get_by_id")
    @ResponseBody                         //这里改成String也可以，Integer也可以
    public Factory getById(@RequestParam("factory_id")Serializable factory_id) throws Exception {
        logger.info("查询一个接口被调用了");
        Factory factory = factoryService.get(factory_id);
        System.out.println(factory);
        return factory;
    }

    /**
     * 分页查询
     * http://localhost:8080/jks/api/factory/find_page?pageNo=1&pageSize=10
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping("/find_page")
    public String findPage(Page<Factory> page, Model model) throws Exception {
        logger.info("查询分页接口被调用了");
        List<Factory> factorys = factoryService.findPage(page);
        model.addAttribute("dataList",factorys);
        System.out.println(factorys);
        return "/WEB-INF/pages/base/factory/j_factory_list.jsp";
    }

    /**
     * 转向新增页面
     * @return
     * @throws Exception
     */
    @RequestMapping("/insert_page")
    public String insertPage() throws Exception {
        logger.info("转向新增页面接口被调用了");
        return "/WEB-INF/pages/base/factory/j_factory_create.jsp";
    }

    /**
     * 新增保存厂家信息
     * @param factory
     * @return
     * @throws Exception
     */
    @RequestMapping("/insert")
    public String insert(Factory factory)throws Exception{
        logger.info("新增厂家接口被调用了");
        //设置UUID（通常这些是业务，应该写在业务层）
        factory.setFactory_id(CommonUtils.getUUID());
        factoryService.insert(factory);
        //重定向到另一个action ,新增后重定向到列表页面
        return "redirect:/api/factory/find_page";
    }

    /**
     * 转向修改页面,需要传一个id参数进来,到数据库查出来，然后放到页面
     * @return
     */
    @RequestMapping("/update_page")
    public String updatePage(String factory_id,Model model)throws Exception{
        logger.info("转向修改页面接口被调用了");
        Factory factory = factoryService.get(factory_id);
        model.addAttribute("factory",factory);
        System.out.println(factory);
        return "/WEB-INF/pages/base/factory/j_factory_update.jsp";
    }
    /**
     * http://localhost:8080/jks/api/factory/update?factory_id=111&phone=88888888
     * 修改保存厂家信息
     * @param factory
     * @return
     * @throws Exception
     */
    @RequestMapping("/update")
    public String update(Factory factory)throws Exception{
        logger.info("更新厂家接口被调用了");
        //id为空 转跳主列表页面
        if (factory.getFactory_id()==null || "".equals(factory.getFactory_id())){
            return "redirect:/api/factory/find_page";
        }
        factoryService.update(factory);
        //重定向到另一个action ,新增后重定向到列表页面
        return "redirect:/api/factory/find_page";
    }

    /**
     * 删除1个/或者批量删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "factory_id",required=false) Serializable[] ids)throws Exception{
        //长度为0 转跳主列表页面  @RequestParam(value = "factory_id",required=false) 注意：这里一定要加这个false 这样页面才可以不传id,下面才可以判断，处理BUG
        if (ids==null || ids.length==0){
            return "redirect:/api/factory/find_page";
        }
        System.out.println(ids);
        factoryService.delete(ids);
        //重定向到另一个action ,新增后重定向到列表页面
        return "redirect:/api/factory/find_page";
    }

    /**
     * 查看详情
     * @return
     */
    @RequestMapping("/showview")
    public String showView(@RequestParam("factory_id") String factory_id,Model model) throws Exception {
        Factory factory = factoryService.get(factory_id);
        model.addAttribute("factory",factory);
        return "/WEB-INF/pages/base/factory/j_factory_view.jsp";
    }
}
