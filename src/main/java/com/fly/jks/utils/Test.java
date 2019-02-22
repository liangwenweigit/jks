package com.fly.jks.utils;

import javax.xml.bind.SchemaOutputResolver;

/**
 * @author liang
 * @date 2019/2/20 - 16:39
 */
public class Test {
    public static void main(String[] args) {
//        System.out.println(8/10);//0
//        System.out.println(0/10);//0
//        System.out.println(11/10);//1
//        System.out.println(10/10);//1
//        System.out.println(8%10);//8
//        System.out.println(0%10);//0
//        System.out.println(11%10);//1
//        System.out.println(10%10);//0
        //Double计算精度问题
        Double a = 2.0;
        Double b = 1.1;
        System.out.println(a-b);//结果多少0.8999999999999999
        /**
         BigDecimal price = new BigDecimal(book.getPrice()+"");
         BigDecimal counts = new BigDecimal(count+"");
         return price.multiply(counts).doubleValue();
         Double计算俩个BigDecimal
         BigDecimal price = new BigDecimal(book.getPrice()+"");
         BigDecimal counts = new BigDecimal(count+"");
         乘法price.multiply(counts).doubleValue();
         加法price.add(counts).doubleValue();
         减法price.subtract(counts).doubleValue();
         */
    }
}
