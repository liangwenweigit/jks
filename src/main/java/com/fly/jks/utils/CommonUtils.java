package com.fly.jks.utils;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * 常用工具uuid md5
 * @author liang
 * @date 2019/1/28 - 23:03
 */
public class CommonUtils {

    private CommonUtils(){}

    /**
     *普通MD5加密，只能加密一次。要和微信的一样，因为微信回调也是加密一次
     * 这个适用于微信的
     * @param
     * @return
     */
    public static String getMd5(String data) {
        try {

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte [] array = md5.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString().toUpperCase();

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    /**
     * MD5盐值加密，当用户密码一样的时候，我们在用户密码后面添加用户名做为盐值然后进行加密N次，加密多少次我们自己设置
     * @param source 需要加密的密码
     * @param salt 盐值 一般用用户名作为盐值
     * @param hashIterations 加密多少次,数值必须传
     * @return
     */
    public static String getMD5Salt(String source,String salt,int hashIterations){
        //加密信息+盐值 开始加密
        String str = source + salt;
        for (int i = 0; i < hashIterations; i++) {
            str = getMd5(str);
            //每次加密都添加盐值进行再加密
            if (i<hashIterations-1){
                str += salt;
            }
        }
        return str.toUpperCase();
    }


    /**
     * 生成uuid
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }


    /**
     * 测试盐值MD5加密
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(getMd5("123456"));
        System.out.println("盐值加密兼10次:"+getMD5Salt("123456","root",10));
        System.out.println("盐值加密兼5次:"+getMD5Salt("123456","root",5));
    }
}
