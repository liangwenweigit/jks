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
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        //分页资料
        Integer totalRecord = factoryService.selectCount();
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

        //其他资料
        logger.info("查询分页接口被调用了");
        List<Factory> factorys = factoryService.findPage(page);
        model.addAttribute("dataList",factorys);
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
            //设置可用状态为1
            factory.setState("1");
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
        System.out.println(factory_id);//如果前端选择多个ID会直接拼接成字符串，到数据库查找是没有数据的Parameters: 1,11(String)
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
            logger.info("更新厂家失败，id为空");
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

    /**
     * 批量/单个 停用
     * @return
     */
    @RequestMapping("/stop")
    public String stop(@RequestParam(value = "factory_id",required=false) Serializable[] ids)throws Exception{
        //长度为0 转跳主列表页面  @RequestParam(value = "factory_id",required=false) 注意：这里一定要加这个false 这样页面才可以不传id,下面才可以判断，处理BUG
        if (ids==null || ids.length==0){
            return "redirect:/api/factory/find_page";
        }
        factoryService.updateStopState(ids);
        //重定向到另一个action ,新增后重定向到列表页面
        return "redirect:/api/factory/find_page";
    }

    /**
     * 批量/单个 启用
     * @return
     */
    @RequestMapping("/start")
    public String start(@RequestParam(value = "factory_id",required=false) Serializable[] ids)throws Exception{
        //长度为0 转跳主列表页面  @RequestParam(value = "factory_id",required=false) 注意：这里一定要加这个false 这样页面才可以不传id,下面才可以判断，处理BUG
        if (ids==null || ids.length==0){
            return "redirect:/api/factory/find_page";
        }
        factoryService.updateStartState(ids);
        //重定向到另一个action ,新增后重定向到列表页面
        return "redirect:/api/factory/find_page";
    }

    /**
     * 批量/单个 启用
     * @return
     */
    @RequestMapping("/update_state")
    public String updateState(@RequestParam(value = "factory_id",required=false)String factory_id)throws Exception{
        //长度为0 转跳主列表页面  @RequestParam(value = "factory_id",required=false) 注意：这里一定要加这个false 这样页面才可以不传id,下面才可以判断，处理BUG
        Factory factory = factoryService.get(factory_id);
        if (factory==null){
            return "redirect:/api/factory/find_page";
        }
        factoryService.updateState(factory);
        //重定向到另一个action ,新增后重定向到列表页面
        return "redirect:/api/factory/find_page";
    }




    /**
     * 分页查询 json数据
     * http://localhost:8080/jks/api/factory/find_page_html?pageNo=1&pageSize=10
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping("/find_page_html")
    @ResponseBody
    public Object findPageHtml(Page<Factory> page) throws Exception {
        Map<String, Object> data = new HashMap<String, Object>(0);

        //分页资料
        Integer totalRecord = factoryService.selectCount();
        //设置总记录数
        page.setTotalRecord(totalRecord);
        System.out.println(totalRecord);
        //设置总页数
        page.setTotalPage(page.getTotalRecord()%page.getPageSize()==0?page.getTotalRecord()/page.getPageSize():page.getTotalRecord()/page.getPageSize()+1);
        System.out.println(page.getTotalPage());
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
        System.out.println(page.getPageIndex());

        //其他资料
        logger.info("查询分页接口被调用了");
        List<Factory> factorys = factoryService.findPage(page);

        //存进map
        data.put("page",page);
        data.put("factorys",factorys);
        return data;//json格式返回出去
        //return "/WEB-INF/pages/base/factory/j_factory_list.jsp";
    }
}
