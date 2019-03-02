package com.fly.jks.controller.cargo;

import com.fly.jks.controller.BaseController;
import com.fly.jks.domain.Contract;
import com.fly.jks.domain.Export;
import com.fly.jks.pagination.Page;
import com.fly.jks.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.List;

/**
 * 出货报运控制层
 * @author liang
 * @date 2019/3/2 - 2:11
 */
@Controller
@RequestMapping("/api/export")
public class ExportController extends BaseController{

    @Autowired
    private ExportService exportService;

    /**
     * 查询出口报运列表
     * @param page
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/find_page")
    public String list(Page page, Model model) throws Exception {
        //分页资料
        Integer totalRecord = exportService.selectCount(null);//没有查询条件直接传null
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
        List<Export> dataList = exportService.findPage(page);
        model.addAttribute("dataList",dataList);
        return "/WEB-INF/pages/cargo/export/export_list.jsp";
    }

    /**
     * 报运=新增==直接进行后台保存==保存后可以在报运列表看到已经报运的货物
     */
    @RequestMapping("/insert") //可以为空
    public String inserts(@RequestParam(value = "contract_id",required=false) String[] ids) throws Exception {
        //长度为0 转跳主列表页面  @RequestParam(value = "contract_id",required=false) 注意：这里一定要加这个false 这样页面才可以不传id,下面才可以判断，处理BUG
        if (ids==null || ids.length==0){
            return "redirect:/api/contract/find_page?state=1";
        }
       exportService.inserts(ids);

        return null;
    }

}
