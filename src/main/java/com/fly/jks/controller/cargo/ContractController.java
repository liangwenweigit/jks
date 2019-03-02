package com.fly.jks.controller.cargo;

import com.fly.jks.controller.BaseController;
import com.fly.jks.controller.baseinfo.FactoryController;
import com.fly.jks.domain.Contract;
import com.fly.jks.domain.ContractProduct;
import com.fly.jks.pagination.Page;
import com.fly.jks.service.ContractProductService;
import com.fly.jks.service.ContractService;
import com.fly.jks.service.ExtCproductService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private ContractProductService contractProductService;

    @Autowired
    private ExtCproductService extCproductService;


    /**
     * 这个接口作用1：分页查询合同总条数。作用2：当state参数不为空就是为报运人员查询已经上报的合同
     *                                      注意，作用2分页的时候需要把 state参数也传过来
     * http://localhost:8080/jks/api/factory/find_page?pageNo=1&pageSize=10
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping("/find_page")
    public String findPage(Page<Contract> page, Model model,@RequestParam(value = "state",required=false) String state) throws Exception {
        boolean isState = false;//定义一个变量 判断是什么人员操作
        Map<String,Object> paraMap = new HashMap<>();
        //如果不等于空，证明这是需要查询已经上报的合同，是报运人员的操作
        if (state!=null && !"".equals(state)){
            paraMap.put("state",state);//查询页数的信息条件
            page.getParams().put("state",state);//查询数据的信息条件
            //下面到sql下去判断 修改sql
            isState = true;//证明是报运人员
        }
        //分页资料
        Integer totalRecord = contractService.selectCount(paraMap);//没有查询条件直接传null...因为这里复用报运人员查询 所以有条件了，并且传了才会使用，下面sql判断
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

        //状跳页面也不一样，需要判断，当state不是null，证明是报运人员操作的，需要转到报运人员页面。否则转到原来页面
        if (isState){//这个变量上面定义的 判断状态
            //状态到报运人员的列表页面
            return "/WEB-INF/pages/cargo/export/export_contract_list.jsp";
        }
        //转跳到原来的页面的位置
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
     * 删除1个/或者批量删除 合同
     * @param ids
     * @return
     */
    @RequestMapping("/delete") //这个参数不传的话就什么要不删 直接回到合同列表页面
    public String delete(@RequestParam(value = "contract_id",required=false) Serializable[] ids)throws Exception{
        //长度为0 转跳主列表页面  @RequestParam(value = "contract_id",required=false) 注意：这里一定要加这个false 这样页面才可以不传id,下面才可以判断，处理BUG
        if (ids==null || ids.length==0){
            return "redirect:/api/contract/find_page";
        }
        //级联删除 循环1个/多个合同
        for (int i = 0; i <ids.length ; i++) {
            //先查询出这个合同下的全部货物
            List<ContractProduct> contractProducts = contractProductService.selectContractProductByContractId(ids[i]);
            //查出一个合同下的全部货物，通过循环删除某个货物下的全部附件，再删除这个货物
            for (int j = 0; j <contractProducts.size() ; j++) {//重点：这里注意i/j 不要弄错了
                //删除一个货物下的全部附件
                extCproductService.deleteByContractProductId(contractProducts.get(j).getContract_product_id());
                //最后把这个货物也删除
                contractProductService.deleteById(contractProducts.get(j).getContract_product_id());
            }
        }
        //删除1个/多个合同
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
