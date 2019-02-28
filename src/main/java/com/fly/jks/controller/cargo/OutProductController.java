package com.fly.jks.controller.cargo;

import com.fly.jks.domain.vo.ContractVO;
import com.fly.jks.service.ContractService;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
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

        //创建一个处理时间的对象。时间转成字符串
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        List<ContractVO> lists = contractService.selectOutProductByDate(inputDate);
        //理由POI创建 xxx.xls
        //1创建一个工作簿
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row nRow = null;//行对象
        Cell nCell = null;//列对象
        //变量定义行/列
        int rowNo = 0;//行号
        int colNo = 0;//列号

        /**
         * 设置列宽
         */
        sheet.setColumnWidth(0,1); //A列一般是固定的 打印的边 预留一个边
        sheet.setColumnWidth(1,26*300);
        sheet.setColumnWidth(2,12*300);
        sheet.setColumnWidth(3,29*300);
        sheet.setColumnWidth(4,12*300);
        sheet.setColumnWidth(5,12*300);
        sheet.setColumnWidth(6,10*300);
        sheet.setColumnWidth(7,10*300);
        sheet.setColumnWidth(8,8*300);

        /**
         * 第一行的大标题，是合并的单元格，在软件上是选中一个区域，然后合并的，程序合并如下
         */
        //合并单元格方法//四个参数 始启单元格坐标    //开始行0，结束行0坐标，开始列1坐标，结束列8坐标
        sheet.addMergedRegion(new CellRangeAddress(0,0,1,8));//这样就合并了一个单元格
        //合并单元格的内容 写在哪？直接写在合并单元格的第一个单元格坐标即可 就是上面的 开始行+开始列 的0，1坐标
        nRow = sheet.createRow(0);//0 表示第1行
        nRow.setHeightInPoints(36F);//设置行高
        nCell = nRow.createCell(1);// 1 表示当前第2列
        //设置标题值（第一种，显示的是2019年02月）
        //nCell.setCellValue(inputDate.replace("-","年")+"月份出货表");
        //设置标题值（第一种，显示的是2019年2月） 和上面差别0
        nCell.setCellValue(inputDate.replace("-0","-").replace("-","年")+"月份出货表");
        //设置样式
        nCell.setCellStyle(this.bigTitleStyle(wb));

        /**
         * 小标题
         */
        rowNo++;//1
        //初始化行对象
        nRow = sheet.createRow(rowNo);//1  表示第2行
        nRow.setHeightInPoints(26.25F);//设置行高
        //4处理标题:利用数据把报表标题列放进去
        String[] title = new String[]{"客户","订单号","货号","数量","工厂","工厂交期","船期","贸易条款"};	//标题数组
        //设置内容,通过for循环
        for (int i = 0; i < title.length; i++) {
            //行不用动，移动列就行
            nCell = nRow.createCell(1+i);//第一次进入循环当前列为1，移动列==创建列
            nCell.setCellValue(title[i]);
            //5上面内容设置好 设置样式：字体+大小，每次循环 每个单元格都需要设置
            nCell.setCellStyle(this.titleStyle(wb));
        }

        /**
         * 处理数据
         */
        //这里行不++，会把标题给覆盖
        rowNo++;
        //初始化colNo
        colNo = 1;
        //6处理数据库拿出来的数据
        for (int i = 0; i < lists.size() ; i++) {
            ContractVO cv = lists.get(i);
            //设置行，每次循环就一行 ++进入下一行
            nRow = sheet.createRow(rowNo++);
            nRow.setHeightInPoints(24F);//设置行高
            //设置 客户名称 +列每次都要动一次
            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(cv.getCustomer_name());
            nCell.setCellStyle(this.styles(wb));

            //设置 订单号/合同号 +列每次都要动一次
            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(cv.getContract_no());
            nCell.setCellStyle(this.styles(wb));

            //设置 货号 +列每次都要动一次
            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(cv.getProduct_num());
            nCell.setCellStyle(this.styles(wb));

            //设置 数量(显示是 数量+单位 例如：480PCS) +列每次都要动一次
            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(cv.getCnumber()+""+cv.getPacking_unit());
            nCell.setCellStyle(this.styles(wb));

            //设置 生成工厂厂家名字 +列每次都要动一次
            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(cv.getFactory_name());
            nCell.setCellStyle(this.styles(wb));

            //设置 工厂交期 +列每次都要动一次
            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(sdf.format(cv.getDelivery_date()));
            nCell.setCellStyle(this.styles(wb));

            //设置 船期 +列每次都要动一次
            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(sdf.format(cv.getShip_date()));
            nCell.setCellStyle(this.styles(wb));

            //设置 贸易条款 +列每次都要动一次
            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(cv.getTrade_clause());
            nCell.setCellStyle(this.styles(wb));
            //循环一次，必须把列号变回1，否则，就成楼梯形状
            colNo = 1;
        }

        //用IO保存
        OutputStream os = new FileOutputStream("c:\\出货报表.xls");//excel 2003
        wb.write(os);
        os.flush();
        os.close();


    }

    /**
     *大标题样式
     */
    //每次循环 每个单元格都需要设置，就是都要调用这个方法
    public CellStyle bigTitleStyle(Workbook wb){
        CellStyle style = wb.createCellStyle();//创建样式对象
        Font font = wb.createFont();//创建字体对象
        font.setFontName("宋体");//设置字体
        font.setFontHeightInPoints((short)16);//设置字体大小
        //font.setFontHeight(Font.SS_SUPER);//特大号加粗字体
        font.setBold(true);//加粗字体
        style.setFont(font);//绑定字体对象
        //这俩个必须在绑定字体之后
        style.setVerticalAlignment(VerticalAlignment.CENTER);//文本垂直居中
        style.setAlignment(HorizontalAlignment.CENTER);//文本水平居中
        return style;
    }


    /**
     *小题样式
     */
    //每次循环 每个单元格都需要设置，就是都要调用这个方法
    public CellStyle titleStyle(Workbook wb){
        CellStyle style = wb.createCellStyle();//创建样式对象
        Font font = wb.createFont();//创建字体对象
        font.setFontName("黑体");//设置字体
        font.setFontHeightInPoints((short)12);//设置字体大小
        style.setFont(font);//绑定字体对象
        //这俩个必须在绑定字体之后
        style.setVerticalAlignment(VerticalAlignment.CENTER);//文本垂直居中
        style.setAlignment(HorizontalAlignment.CENTER);//文本水平居中

        style.setBorderBottom(BorderStyle.THIN);//下边框    
        style.setBorderLeft(BorderStyle.THIN);//左边框    
        style.setBorderTop(BorderStyle.THIN);//上边框    
        style.setBorderRight(BorderStyle.THIN);//右边框    

        return style;
    }

    /**
     *文本数据样式
     */
    //每次循环 每个单元格都需要设置，就是都要调用这个方法
    public CellStyle styles(Workbook wb){
        CellStyle style = wb.createCellStyle();//创建样式对象
        Font font = wb.createFont();//创建字体对象
        font.setFontName("Times New Roman");//设置字体
        font.setFontHeightInPoints((short)10);//设置字体大小
        style.setFont(font);//绑定字体对象

        style.setBorderBottom(BorderStyle.THIN);//下边框    
        style.setBorderLeft(BorderStyle.THIN);//左边框    
        style.setBorderTop(BorderStyle.THIN);//上边框    
        style.setBorderRight(BorderStyle.THIN);//右边框

        return style;
    }

}
