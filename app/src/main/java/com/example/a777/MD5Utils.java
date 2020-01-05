package com.example.a777;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by lt on 2017/12/26.
 */

public class MD5Utils {

    /**
     * md5加密算法
     * @param text
     * @return
     */
    public static String md5(String text){
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");//获取数据指纹对象
            byte[] result = digest.digest(text.getBytes());//字节数组
            StringBuilder sb = new StringBuilder();//16进制转换
            for (byte b :result){//获取所有字节进行转换
                int number = b & 0xff;//使用『与算法』，java使用unicode字符，所以每个字符占位两个，则需要与两位16进制最大值进行与运算，获取number值
                String hex = Integer.toHexString(number);//number值转换字符串
                if (hex.length()==1){//若转换后的字符长度等于1则进行字符串拼接
                    sb.append("0" + hex);
                }else {
                    sb.append(hex);
                }
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";//发送异常return空字符串
        }
    }
}


