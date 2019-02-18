package com.fly.jks.controller;

import com.fly.jks.domain.Factory;
import com.fly.jks.pagination.Page;
import com.fly.jks.service.FactoryService;
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
     * http://localhost:8080/jks/api/factory/get_by_id.action?factory_id=111
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
     * http://localhost:8080/jks/api/factory/find_page.action?pageNo=1&pageSize=10
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping("/find_page")
    public String findPage(Page<Factory> page, Model model) throws Exception {
        logger.info("查询分页接口被调用了");
        List<Factory> factorys = factoryService.findPage(page);
        model.addAttribute("factorys",factorys);
        System.out.println(factorys);
        return "/pages/base/factory/j_factory_list.jsp";
    }
}
