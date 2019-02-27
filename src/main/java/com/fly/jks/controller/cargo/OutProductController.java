package com.fly.jks.controller.cargo;

import com.fly.jks.domain.vo.ContractVO;
import com.fly.jks.service.ContractService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * 出货表
 * @author liang
 * @date 2019/2/27 - 20:44
 */
@Controller
@RequestMapping("/api/outproduct")
public class OutProductController {
    @Autowired
    private ContractService contractService;
    /**
     * 转向输入年月的页面，因为根据需求要根据年月查询，我们可以把年月当成字符串封装进来
     * ，所以不需要继承日期处理的controller
     * @return
     */
    @RequestMapping("/toedit")
    public String toedit(){
        return "/WEB-INF/pages/cargo/outproduct/outproduct.jsp";
    }

    @RequestMapping("/print")
    public void print(String inputDate) throws Exception {//inputDate 格式：yyyy-MM
        List<ContractVO> lists = contractService.selectOutProductByDate(inputDate);
        //理由POI创建 xxx.xls
        //1创建一个工作簿
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row nRow = null;//行对象
        Cell nCell = null;//列对象
        CellStyle style = null;//字体对象
        Font font = null;//字体大小对象
        //变量定义行/列
        int rowNo = 0;//行号
        int colNo = 1;//列号
        rowNo++;
        colNo++;

        //2全局 样式+字体 对象
        style = wb.createCellStyle();//创建单元格样式
        font = wb.createFont();//创建一个字体对象

        //3全局 行+列 对象
        nRow = sheet.createRow(rowNo);//第1行
        nCell = nRow.createCell(colNo);//第1列

        //4处理标题:利用数据把报表标题列放进去
        String[] title = new String[]{"客户","订单号","货号","数量","工厂","工厂交期","船期","贸易条款"};	//标题数组
        //设置内容,通过for循环
        for (int i = 0; i < title.length; i++) {
            //行不用动，移动列就行
            nCell = nRow.createCell(1+i);//移动列==创建列
            nCell.setCellValue(title[i]);
            //5上面内容设置好 设置样式：字体+大小，每次循环 每个单元格都需要设置
            nCell.setCellStyle(this.styles(style,font,"黑体",12));
        }

        //6处理数据



        //用IO保存
        OutputStream os = new FileOutputStream("c:\\出货报表.xls");//excel 2003
        wb.write(os);
        os.flush();
        os.close();


    }

    //每次循环 每个单元格都需要设置，就是都要调用这个方法
    //抽取样式和字体处理 需要参数 样式对象、字体对象、字体、字体大小
    //使用： nCell.setCellStyle(style(style,font,"Times New Roman",14));//设置单元格样式
    public CellStyle styles(CellStyle cellStyle, Font font, String fontType, int fontSize){
        font.setFontName(fontType);//设置字体
        font.setFontHeightInPoints((short)fontSize);//设置字体大小
        cellStyle.setFont(font);//绑定字体对象
        return cellStyle;
    }

}
