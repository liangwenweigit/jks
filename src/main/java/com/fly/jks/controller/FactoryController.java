package com.fly.jks.controller;

import com.fly.jks.domain.Factory;
import com.fly.jks.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * @author liang
 * @date 2019/2/17 - 23:35
 */
@Controller
@RequestMapping("/api/factory")
public class FactoryController {
    @Autowired
    private FactoryService factoryService;

    /**
     * http://localhost:8080/jks/api/factory/get_by_id?factory_id=111
     * @param factory_id
     * @return
     * @throws Exception
     */
    @RequestMapping("/get_by_id")
    @ResponseBody                                     //这里改成String也可以，Integer也可以
    public Factory getById(@RequestParam("factory_id")Serializable factory_id) throws Exception {
        Factory factory = factoryService.get(factory_id);
        System.out.println(factory);
        return factory;

    }
}
