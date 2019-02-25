package com.fly.jks.controller.cargo;

import com.fly.jks.controller.BaseController;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 合同controller层
 * @author liang
 * @date 2019/2/22 - 19:25
 */
@Controller
@RequestMapping("/api/contract")
public class ContractController extends BaseController{
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
        Integer totalRecord = contractService.selectCount(null);//没有查询条件直接传null
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
        //设置可用状态为1 草稿/未上报
        contract.setContract_state("1");
        //设置创建时间
        contract.setCreate_time(new Date());
        contractService.insert(contract);
        //重定向到另一个action ,新增后重定向到列表页面
        return "redirect:/api/contract/find_page";
    }

    /**
     * 转向修改页面,需要传一个id参数进来,到数据库查出来，然后放到页面
     * @return
     */
    @RequestMapping("/update_page")
    public String updatePage(String contract_id,Model model)throws Exception{
        logger.info("转向修改页面接口被调用了");
        //System.out.println(contract_id);//如果前端选择多个ID会直接拼接成字符串，到数据库查找是没有数据的Parameters: 1,11(String)
        Contract contract = contractService.get(contract_id);
        model.addAttribute("contract",contract);
        return "/WEB-INF/pages/cargo/contract/contract_update.jsp";
    }

    /**
     * 修改保存合同信息
     * @param contract
     * @return
     * @throws Exception
     */
    @RequestMapping("/update")
    public String update(Contract contract)throws Exception{
        logger.info("更新厂家接口被调用了");
        //id为空 转跳主列表页面
        if (contract.getContract_id()==null || "".equals(contract.getContract_id())){
            logger.info("更新厂家失败，id为空");
            return "redirect:/api/contract/find_page";
        }
        contractService.update(contract);
        //重定向到另一个action ,新增后重定向到列表页面
        return "redirect:/api/contract/find_page";
    }

    /**
     * 删除1个/或者批量删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "contract_id",required=false) Serializable[] ids)throws Exception{
        //长度为0 转跳主列表页面  @RequestParam(value = "contract_id",required=false) 注意：这里一定要加这个false 这样页面才可以不传id,下面才可以判断，处理BUG
        if (ids==null || ids.length==0){
            return "redirect:/api/contract/find_page";
        }
        contractService.delete(ids);
        //重定向到另一个action ,新增后重定向到列表页面
        return "redirect:/api/contract/find_page";
    }


    /**
     * 批量/单个 上报
     * @return
     */
    @RequestMapping("/stop")
    public String stop(@RequestParam(value = "contract_id",required=false) Serializable[] ids)throws Exception{
        //长度为0 转跳主列表页面  @RequestParam(value = "contract_id",required=false) 注意：这里一定要加这个false 这样页面才可以不传id,下面才可以判断，处理BUG
        if (ids==null || ids.length==0){
            return "redirect:/api/contract/find_page";
        }
        contractService.updateStopState(ids);
        //重定向到另一个action ,新增后重定向到列表页面
        return "redirect:/api/contract/find_page";
    }

    /**
     * 批量/单个 取消上报
     * @return
     */
    @RequestMapping("/start")
    public String start(@RequestParam(value = "contract_id",required=false) Serializable[] ids)throws Exception{
        //长度为0 转跳主列表页面  @RequestParam(value = "contract_id",required=false) 注意：这里一定要加这个false 这样页面才可以不传id,下面才可以判断，处理BUG
        if (ids==null || ids.length==0){
            return "redirect:/api/contract/find_page";
        }
        contractService.updateStartState(ids);
        //重定向到另一个action ,新增后重定向到列表页面
        return "redirect:/api/contract/find_page";
    }

    /**
     * 单个更新上报状态
     * @return
     */
    @RequestMapping("/update_state")
    public String updateState(@RequestParam(value = "contract_id",required=false)String contract_id)throws Exception{
        //长度为0 转跳主列表页面  @RequestParam(value = "contract_id",required=false) 注意：这里一定要加这个false 这样页面才可以不传id,下面才可以判断，处理BUG
        Contract contract = contractService.get(contract_id);
        if (contract==null){
            return "redirect:/api/contract/find_page";
        }
        contractService.updateState(contract);
        //重定向到另一个action ,新增后重定向到列表页面
        return "redirect:/api/contract/find_page";
    }


    /**
     * 查看详情
     * @return
     */
    @RequestMapping("/showview")
    public String showView(@RequestParam("contract_id") String contract_id,Model model) throws Exception {
        Contract contract = contractService.get(contract_id);
        model.addAttribute("contract",contract);
        return "/WEB-INF/pages/cargo/contract/contract_view.jsp";
    }
}
