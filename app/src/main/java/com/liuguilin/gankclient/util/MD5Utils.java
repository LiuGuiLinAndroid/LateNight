package com.liuguilin.gankclient.util;

/*
 *  项目名：  GankClient 
 *  包名：    com.liuguilin.gankclient.util
 *  文件名:   MD5Utils
 *  创建者:   LGL
 *  创建时间:  2016/10/20 12:26
 *  描述：    MD5加密
 */

import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    public static String encode(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            // 进行加密运算,返回加密后的字节数组
            byte[] bytes = digest.digest(password.getBytes());
            StringBuffer sb = new StringBuffer();
            for (byte b : bytes) {
                int i = b & 0xff;
                String hexString = Integer.toHexString(i);
                // System.out.println(hexString);
                if (hexString.length() == 1) {
                    hexString = "0" + hexString;
                }
                sb.append(hexString);
            }
            String md5 = sb.toString();
            return md5;
        } catch (NoSuchAlgorithmException e) {
            // 没有此算法异常
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 计算文件md5
     *
     * @param filePath
     * @return
     */
    public static String encodeFile(String filePath) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            FileInputStream in = new FileInputStream(filePath);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }
            byte[] bytes = digest.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : bytes) {
                int i = b & 0xff;
                String hexString = Integer.toHexString(i);
                // System.out.println(hexString);
                if (hexString.length() == 1) {
                    hexString = "0" + hexString;
                }
                sb.append(hexString);
            }
            String md5 = sb.toString();
            return md5;
        } catch (Exception e) {
            // 没有此算法异常
            e.printStackTrace();
        }
        return null;
    }
}
