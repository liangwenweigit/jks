package com.fly.jks.controller.cargo;

import com.fly.jks.controller.baseinfo.FactoryController;
import com.fly.jks.domain.Contract;
import com.fly.jks.domain.Factory;
import com.fly.jks.pagination.Page;
import com.fly.jks.service.ContractService;
import com.fly.jks.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

/**
 * 合同controller层
 * @author liang
 * @date 2019/2/22 - 19:25
 */
@Controller
@RequestMapping("/api/contract")
public class ContractController {
    private static final Logger logger = LoggerFactory.getLogger(FactoryController.class);
    @Autowired
    private ContractService contractService;
    /**
     * 分页查询
     * http://localhost:8080/jks/api/factory/find_page?pageNo=1&pageSize=10
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping("/find_page")
    public String findPage(Page<Contract> page, Model model) throws Exception {
        //分页资料
        Integer totalRecord = contractService.selectCount();
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
        List<Contract> contracts = contractService.findPage(page);
        model.addAttribute("dataList",contracts);
        return "/WEB-INF/pages/cargo/contract/contract_list.jsp";
    }

    /**
     * 转向新增页面
     * @return
     * @throws Exception
     */
    @RequestMapping("/insert_page")
    public String insertPage() throws Exception {
        logger.info("转向新增页面接口被调用了");
        return "/WEB-INF/pages/cargo/contract/contract_create.jsp";
    }

    /**
     * 新增保存厂家信息
     * @param contract
     * @return
     * @throws Exception
     */
    @RequestMapping("/insert")
    public String insert(Contract contract)throws Exception{
        logger.info("新增厂家接口被调用了");
        //设置UUID（通常这些是业务，应该写在业务层）
        contract.setContract_id(CommonUtils.getUUID());
        //设置可用状态为1 草稿/
        contract.setContract_state("1");
        contractService.insert(contract);
        //重定向到另一个action ,新增后重定向到列表页面
        return "redirect:/api/contract/find_page";
    }
}
