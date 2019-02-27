package com.fly.jks;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author liang
 * @date 2019/2/27 - 19:12
 */
public class TestPOI {

    @Test
    public void HSSF()throws Exception{
        //1创建一个工作簿
        /**
         * import org.apache.poi.hssf.usermodel.HSSFWorkbook;
         * import org.apache.poi.ss.usermodel.Workbook;
         */
        Workbook wb = new HSSFWorkbook(); //HSSF操作2003以下版本
        //2创建一个工作表sheet
        Sheet sheet = wb.createSheet();
        //3定位单元格，创建行对象Row    /要在第5行写，但是坐标java是从0开始的所有是4
        Row nRow = sheet.createRow(4);
        //4创建一个单元格对象，指定列Cell /第4列
        Cell nCell = nRow.createCell(3);
        //5给单元格设置内容
        nCell.setCellValue("飞飞最帅！");
        //6保存
        OutputStream os = new FileOutputStream(new File("c:\\testpoi.xls"));//excel 2003
        wb.write(os);
        os.flush();
        os.close();
    }
    //带样式
    @Test
    public void testHSSFStyle() throws IOException {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();

        //内容
        Row nRow = sheet.createRow(7);	//第八行
        Cell nCell = nRow.createCell(4);//第五列
        nCell.setCellValue("飞飞最帅！");//设置内容

        //设置样式
        CellStyle style = wb.createCellStyle();//创建单元格样式
        Font font = wb.createFont();//创建一个字体对象
        nCell.setCellStyle(this.styles(style,font,"华文隶书",26));//设置单元格样式


        //继续添加内容  //下面的nRow+nCell对象是重用上面的。样式和字体对象 需要重新初始化创建 但是接收的还是原对象。
        nRow = sheet.createRow(8);//9行
        nCell = nRow.createCell(6);//7列
        nCell.setCellValue("java");//设置内容

        //设置样式
        //这俩个对象需要重新初始化创建
        style = wb.createCellStyle();
        font = wb.createFont();
        nCell.setCellStyle(this.styles(style,font,"Times New Roman",14));//设置单元格样式

        //用IO保存
        OutputStream os = new FileOutputStream("c:\\testpoi.xls");//excel 2003
        wb.write(os);
        os.flush();
        os.close();
    }

    //抽取样式和字体处理 需要参数 样式对象、字体对象、字体、字体大小
    //使用： nCell.setCellStyle(style(style,font,"Times New Roman",14));//设置单元格样式
    public CellStyle styles(CellStyle cellStyle,Font font,String fontType,int fontSize){
        font.setFontName(fontType);//设置字体
        font.setFontHeightInPoints((short)fontSize);//设置字体大小
        cellStyle.setFont(font);//绑定字体对象
        return cellStyle;
    }

}
