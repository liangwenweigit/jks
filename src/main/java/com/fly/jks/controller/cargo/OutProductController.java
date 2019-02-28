package com.fly.jks.controller.cargo;

import com.fly.jks.domain.vo.ContractVO;
import com.fly.jks.service.ContractService;
import com.fly.jks.utils.DownloadUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 出货表
 * 下面有2个打印方法，2个都成功的
 * 1个是模板打印
 * 1个是自己写样式的打印
 * 推荐使用模板打印，样式不用自己写
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

    /**
     * 升级版：XSSF
     * 这个方法是模板方法升级版，打开一个模板获取样式，不在是新建
     * 要求读取的文件是：.xlsx开头，打印出来的是.xlsx文件 2007以上版本，数据量处理百万级别
     * @param inputDate
     * @param response
     * @throws Exception
     */
    @RequestMapping("/printx")
    public void printx(String inputDate, HttpServletResponse response, HttpServletRequest request) throws Exception {//inputDate 格式：yyyy-MM

        //创建一个处理时间的对象。时间转成字符串 txOutProduct.xls
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //数据库获取信息
        List<ContractVO> lists = contractService.selectOutProductByDate(inputDate);
        //获取xls模板
        //linux下jdk1.8 方法获取时，不会拼接自己写的目录,所以兼容windows/linux写下面这个种，获取根路径在人为添加/make/xlsprint/
        String path = request.getSession().getServletContext().getRealPath("/") + "/make/xlsprint/";
        InputStream is = new FileInputStream(new File(path+"tOUTPRODUCT.xlsx"));
        Workbook wb = new XSSFWorkbook(is);//打开一个模板文件=工作簿
        Sheet sheet = wb.getSheetAt(0);//获取第一个工作表
        Row nRow = null;//行对象
        Cell nCell = null;//列对象
        //变量定义行/列
        int rowNo = 0;//行号
        int colNo = 1;//列号
        /**
         * 第一行的大标题，是合并的单元格，在软件上是选中一个区域，然后合并的，程序合并如下
         */
        //获取大标题的行列
        nRow = sheet.getRow(rowNo);//第1行 这里必须是数字
        nCell = nRow.getCell(colNo);//第2列 这里必须是数字，如果使用++  那么++必须在前面   colNo++
        //设置标题值（第1种，显示的是2019年02月）
        //nCell.setCellValue(inputDate.replace("-","年")+"月份出货表");
        //设置标题值（第2种，显示的是2019年2月） 和上面差别0
        nCell.setCellValue(inputDate.replace("-0","-").replace("-","年")+"月份出货表");
        //设置样式：用了模板不用设置

        /**
         * 小标题，都是固定的静态问题，不用写了
         */
        rowNo++;//第2行 静态小标题空间，++跳过了

        /**
         * 处理数据
         */
        //这里行不++，会把标题给覆盖
        rowNo++;//第3行
        //初始化colNo
        colNo = 1;//第2列（第1列是空的，所以从第二列开始）
        /**
         * 分别获取第3行上每列单元格的样式对象
         */
        //去到第3行
        nRow = sheet.getRow(rowNo);//值是2坐标，第3行

        //获取客户单元格数据的样式
        nCell = nRow.getCell(colNo++);//第2列
        CellStyle customerStyle = nCell.getCellStyle();//获取到模板数据单元格样式

        //获取订单号单元格数据的样式
        nCell = nRow.getCell(colNo++);//第3列
        CellStyle contractNoStyle = nCell.getCellStyle();//获取到模板数据单元格样式

        //获取货号单元格数据的样式
        nCell = nRow.getCell(colNo++);//第4列
        CellStyle productNoStyle = nCell.getCellStyle();//获取到模板数据单元格样式

        //获取数量单元格数据的样式
        nCell = nRow.getCell(colNo++);//第5列
        CellStyle cnumberStyle = nCell.getCellStyle();//获取到模板数据单元格样式

        //获取厂家名称单元格数据的样式
        nCell = nRow.getCell(colNo++);//第6列
        CellStyle factoryNameStyle = nCell.getCellStyle();//获取到模板数据单元格样式

        //获取交期单元格数据的样式
        nCell = nRow.getCell(colNo++);//第7列
        CellStyle getDataStyle = nCell.getCellStyle();//获取到模板数据单元格样式

        //获取船期单元格数据的样式
        nCell = nRow.getCell(colNo++);//第8列
        CellStyle shipDataStyle = nCell.getCellStyle();//获取到模板数据单元格样式

        //获取贸易条款单元格数据的样式
        nCell = nRow.getCell(colNo++);//第9列
        CellStyle extrStyle = nCell.getCellStyle();//获取到模板数据单元格样式

        colNo = 1;//初始化列号
        rowNo = 2;//初始化行号
        //注意下面的++ 都是先用再++的
        //处理数据库拿出来的数据。下面的nRow/nCell都必须创建了，不能get,get会爆错
        for (int i = 0; i < lists.size() ; i++) {
            ContractVO cv = lists.get(i);
            //设置行，每次循环就一行 ++进入下一行
            nRow = sheet.createRow(rowNo++);
            //设置 客户名称 +列每次都要动一次
            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(cv.getCustomer_name());
            nCell.setCellStyle(customerStyle);//客户样式设置进去

            //设置 订单号/合同号 +列每次都要动一次
            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(cv.getContract_no());
            nCell.setCellStyle(contractNoStyle);//合同号样式设置进去

            //设置 货号 +列每次都要动一次
            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(cv.getProduct_num());
            nCell.setCellStyle(productNoStyle);//货号样式设置进去

            //设置 数量(显示是 数量+单位 例如：480PCS) +列每次都要动一次
            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(cv.getCnumber()+""+cv.getPacking_unit());
            nCell.setCellStyle(cnumberStyle);//数量样式设置进去

            //设置 生成工厂厂家名字 +列每次都要动一次
            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(cv.getFactory_name());
            nCell.setCellStyle(factoryNameStyle);//厂家名称样式设置进去

            //设置 工厂交期 +列每次都要动一次
            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(sdf.format(cv.getDelivery_date()));
            nCell.setCellStyle(getDataStyle);//工厂交期样式设置进去

            //设置 船期 +列每次都要动一次
            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(sdf.format(cv.getShip_date()));
            nCell.setCellStyle(shipDataStyle);//船期样式设置进去

            //设置 贸易条款 +列每次都要动一次
            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(cv.getTrade_clause());
            nCell.setCellStyle(extrStyle);//贸易条款样式设置进去
            //循环一次，必须把列号变回1，否则，就成楼梯形状
            colNo = 1;//第2列（第1列是空的，所以从第二列开始）
        }
        //先写到流里面
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        wb.write(os);
        //下载：用到工具类
        DownloadUtil downloadUtil = new DownloadUtil();//直接弹出下载框，用户可以打开，可以保存
        downloadUtil.download(os, response, "出货报表.xlsx");
        //关闭
        os.flush();
        os.close();

    }


    /**
     * 低版本：HSSF
     * 这个方法是模板方法低版本，打开一个模板获取样式，不在是新建
     * 要求读取的文件是：.xls开头，打印出来的是.xls文件 2003版本，数据量处理4/5W左右
     * @param inputDate
     * @param response
     * @throws Exception
     */
    @RequestMapping("/print")
    public void print(String inputDate, HttpServletResponse response, HttpServletRequest request) throws Exception {//inputDate 格式：yyyy-MM

        //创建一个处理时间的对象。时间转成字符串 txOutProduct.xls
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //数据库获取信息
        List<ContractVO> lists = contractService.selectOutProductByDate(inputDate);
        //获取xls模板
        //linux下jdk1.8 方法获取时，不会拼接自己写的目录,所以兼容windows/linux写下面这个种，获取根路径在人为添加/make/xlsprint/
        String path = request.getSession().getServletContext().getRealPath("/") + "/make/xlsprint/";
        InputStream is = new FileInputStream(new File(path+"tOUTPRODUCT.xls"));
        Workbook wb = new HSSFWorkbook(is);//打开一个模板文件=工作簿
        Sheet sheet = wb.getSheetAt(0);//获取第一个工作表
        Row nRow = null;//行对象
        Cell nCell = null;//列对象
        //变量定义行/列
        int rowNo = 0;//行号
        int colNo = 1;//列号

        /**
         * 第一行的大标题，是合并的单元格，在软件上是选中一个区域，然后合并的，程序合并如下
         */
        //获取大标题的行列
        nRow = sheet.getRow(rowNo);//第1行 这里必须是数字
        nCell = nRow.getCell(colNo);//第2列 这里必须是数字，如果使用++  那么++必须在前面   colNo++
        //设置标题值（第1种，显示的是2019年02月）
        //nCell.setCellValue(inputDate.replace("-","年")+"月份出货表");
        //设置标题值（第2种，显示的是2019年2月） 和上面差别0
        nCell.setCellValue(inputDate.replace("-0","-").replace("-","年")+"月份出货表");
        //设置样式：用了模板不用设置

        /**
         * 小标题，都是固定的静态问题，不用写了
         */
        rowNo++;//第2行 静态小标题空间，++跳过了

        /**
         * 处理数据
         */
        //这里行不++，会把标题给覆盖
        rowNo++;//第3行
        //初始化colNo
        colNo = 1;//第2列（第1列是空的，所以从第二列开始）
        /**
         * 分别获取第3行上每列单元格的样式对象
         */
        //去到第3行
        nRow = sheet.getRow(rowNo);//值是2坐标，第3行

        //获取客户单元格数据的样式
        nCell = nRow.getCell(colNo++);//第2列
        CellStyle customerStyle = nCell.getCellStyle();//获取到模板数据单元格样式

        //获取订单号单元格数据的样式
        nCell = nRow.getCell(colNo++);//第3列
        CellStyle contractNoStyle = nCell.getCellStyle();//获取到模板数据单元格样式

        //获取货号单元格数据的样式
        nCell = nRow.getCell(colNo++);//第4列
        CellStyle productNoStyle = nCell.getCellStyle();//获取到模板数据单元格样式

        //获取数量单元格数据的样式
        nCell = nRow.getCell(colNo++);//第5列
        CellStyle cnumberStyle = nCell.getCellStyle();//获取到模板数据单元格样式

        //获取厂家名称单元格数据的样式
        nCell = nRow.getCell(colNo++);//第6列
        CellStyle factoryNameStyle = nCell.getCellStyle();//获取到模板数据单元格样式

        //获取交期单元格数据的样式
        nCell = nRow.getCell(colNo++);//第7列
        CellStyle getDataStyle = nCell.getCellStyle();//获取到模板数据单元格样式

        //获取船期单元格数据的样式
        nCell = nRow.getCell(colNo++);//第8列
        CellStyle shipDataStyle = nCell.getCellStyle();//获取到模板数据单元格样式

        //获取贸易条款单元格数据的样式
        nCell = nRow.getCell(colNo++);//第9列
        CellStyle extrStyle = nCell.getCellStyle();//获取到模板数据单元格样式

        colNo = 1;//初始化列号
        rowNo = 2;//初始化行号
        //注意下面的++ 都是先用再++的
        //处理数据库拿出来的数据。下面的nRow/nCell都必须创建了，不能get,get会爆错
        for (int i = 0; i < lists.size() ; i++) {
            ContractVO cv = lists.get(i);
            //设置行，每次循环就一行 ++进入下一行
            nRow = sheet.createRow(rowNo++);
            //设置 客户名称 +列每次都要动一次
            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(cv.getCustomer_name());
            nCell.setCellStyle(customerStyle);//客户样式设置进去

            //设置 订单号/合同号 +列每次都要动一次
            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(cv.getContract_no());
            nCell.setCellStyle(contractNoStyle);//合同号样式设置进去

            //设置 货号 +列每次都要动一次
            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(cv.getProduct_num());
            nCell.setCellStyle(productNoStyle);//货号样式设置进去

            //设置 数量(显示是 数量+单位 例如：480PCS) +列每次都要动一次
            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(cv.getCnumber()+""+cv.getPacking_unit());
            nCell.setCellStyle(cnumberStyle);//数量样式设置进去

            //设置 生成工厂厂家名字 +列每次都要动一次
            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(cv.getFactory_name());
            nCell.setCellStyle(factoryNameStyle);//厂家名称样式设置进去

            //设置 工厂交期 +列每次都要动一次
            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(sdf.format(cv.getDelivery_date()));
            nCell.setCellStyle(getDataStyle);//工厂交期样式设置进去

            //设置 船期 +列每次都要动一次
            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(sdf.format(cv.getShip_date()));
            nCell.setCellStyle(shipDataStyle);//船期样式设置进去

            //设置 贸易条款 +列每次都要动一次
            nCell = nRow.createCell(colNo++);
            nCell.setCellValue(cv.getTrade_clause());
            nCell.setCellStyle(extrStyle);//贸易条款样式设置进去
            //循环一次，必须把列号变回1，否则，就成楼梯形状
            colNo = 1;//第2列（第1列是空的，所以从第二列开始）
        }
        //先写到流里面
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        wb.write(os);
        //下载：用到工具类
        DownloadUtil downloadUtil = new DownloadUtil();//直接弹出下载框，用户可以打开，可以保存
        downloadUtil.download(os, response, "出货报表.xls");
        //关闭
        os.flush();
        os.close();

    }




    /**
     * 这个方法是原始的方法，需要自己写样式
     * @param inputDate
     * @param response
     * @throws Exception
     */
    @RequestMapping("/printNotemplate")
    public void printNotemplate(String inputDate, HttpServletResponse response) throws Exception {//inputDate 格式：yyyy-MM

        //创建一个处理时间的对象。时间转成字符串
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        List<ContractVO> lists = contractService.selectOutProductByDate(inputDate);
        //1创建一个工作簿
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();//获取工作表
        Row nRow = null;//行对象
        Cell nCell = null;//列对象
        //变量定义行/列
        int rowNo = 0;//行 第1行
        int colNo = 0;//列 第1列

        /**
         * 设置列宽
         */
        sheet.setColumnWidth(0,1); //A列一般是固定的 打印的边 预留一个边
        sheet.setColumnWidth(1,26*268);//本来是*256的 但是有BUG不精准，268接近精准
        sheet.setColumnWidth(2,12*268);
        sheet.setColumnWidth(3,29*268);
        sheet.setColumnWidth(4,12*268);
        sheet.setColumnWidth(5,12*268);
        sheet.setColumnWidth(6,10*268);
        sheet.setColumnWidth(7,10*268);
        sheet.setColumnWidth(8,8*268);

        /**
         * 第一行的大标题，是合并的单元格，在软件上是选中一个区域，然后合并的，程序合并如下
         */
        //合并单元格方法//四个参数 始启单元格坐标    //开始第1行0坐标，结束1行0坐标，开始第2列1坐标，结束第9列8坐标
        sheet.addMergedRegion(new CellRangeAddress(0,0,1,8));//这样就合并了一个单元格
        //合并单元格的内容 写在哪？直接写在合并单元格的第一个单元格坐标即可 就是上面的 开始行+开始列 的0，1坐标
        nRow = sheet.createRow(0);//第1行
        nRow.setHeightInPoints(36F);//设置行高
        nCell = nRow.createCell(1);//第2列 （第1列是空的，所以从第二列开始）
        //设置标题值（第1种，显示的是2019年02月）
        //nCell.setCellValue(inputDate.replace("-","年")+"月份出货表");
        //设置标题值（第2种，显示的是2019年2月） 和上面差别0
        nCell.setCellValue(inputDate.replace("-0","-").replace("-","年")+"月份出货表");
        //设置样式
        nCell.setCellStyle(this.bigTitleStyle(wb));

        /**
         * 小标题
         */
        rowNo++;//1 第2行
        //初始化行对象
        nRow = sheet.createRow(rowNo);//1 第2行
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
        //初始化colNo //注意下面的++ 都是先用再++的
        colNo = 1;//第2列（第1列是空的，所以从第二列开始）
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
            colNo = 1;//第2列（第1列是空的，所以从第二列开始）
        }
        //先写到流里面
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        wb.write(os);
        //下载：用到工具类
        DownloadUtil downloadUtil = new DownloadUtil();//直接弹出下载框，用户可以打开，可以保存
        downloadUtil.download(os, response, "出货报表.xls");
        //关闭
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
