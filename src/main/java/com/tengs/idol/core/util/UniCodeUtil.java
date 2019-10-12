package com.tengs.idol.core.util;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;

/**
 * @FileName: UniCodeUtil
 * @Author: ips$
 * @Description: //
 * 生成30位id
 */
public abstract class UniCodeUtil implements Serializable {  
  
    private static final long serialVersionUID = -5628028113003022356L;  
  
    private static final int MAX_SIZE = 31;
    private static final int MIN_SIZE = 14;  
    private static final int BASE_SIZE = 2;  

    /** 
     * 默认生成RCID 30位
     *  
     * @return 
     */  
    public static String rand() {  
        return rand(16);
    }  

    /** 
     * 根据传入值生成相应位数随机码 
     *  
     * @param offset 最大生成40位，最小生成14位 (位权基础系数为4) 4 6 8 1 2 3 
     *  
     * @return 
     */  
    public static String rand(int offset) {  
  
        if (offset > MAX_SIZE || offset < MIN_SIZE) {  
            return "";  
        }  
        int div = offset / 10;  
        int bitSize = BASE_SIZE + (div << 2 >> 1);  
        int baseSize = offset - bitSize;  
  
        String baseID = randomBaseID(baseSize);  
  
        StringBuilder rcid = new StringBuilder(baseID);  
        String rv = bitWeight(bitSize);  
        char[] ch = rv.toCharArray();  
        for (char c : ch) {  
            rcid.insert(Character.getNumericValue(c), c);  
        }  
        return rcid.toString().toUpperCase();  
    }  
  
    /** 
     * 根据UUID生成随机指定位数位ID 
     *  
     * @return 
     */  
    public static String randomBaseID(int baseSize) {  
        UUID uid = UUID.randomUUID();  
        long idx = uid.getLeastSignificantBits();  
        StringBuilder buff = new StringBuilder();  
        for (int i = 0; i < 12; i++) {  
            buff.append(toa(0x1F & idx));  
            idx >>>= 5;  
        }  
        long idxb = uid.getMostSignificantBits();  
        buff.append(toa((idxb & 0x1) == 0 ? idx : idx + 0x10));  
        idx = (idxb >>> 1);  
        for (int i = 0; i < 13; i++) {  
            buff.append(toa(0x1F & idx));  
            idx >>>= 5;  
        }  
        return buff.reverse().toString().substring(0, baseSize);  
    }  
  
    /** 
     * 获取位权数 
     *  
     * @return 
     */  
    private static String bitWeight(int bitSize) {  
        String str = String.valueOf(Math.random()).substring(2);  
        while (str.length() < bitSize || str.indexOf('-') > 0) {  
            str = String.valueOf(Math.random()).substring(2);  
        }  
        return str.substring(0, bitSize);  
    }  
  
    /** 
     * 生成可见字符 
     *  
     * @param lidx 
     * @return 
     */  
    private static char toa(long lidx) {  
        if (lidx < 10) {  
            return (char) (0x30 + lidx);  
        }  
        lidx += (0x41 - 10);  
        int[] skips = { 0x49, 0x4f, 0x57, 0x5a };  
        for (int ch : skips) {  
            if (lidx < ch) {  
                break;  
            }  
            lidx++;  
        }  
        return (char) lidx;  
    }

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割
     * (128Bits)
     */
    public static String createUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String create16LenUUID() {
        return randomString(16, false);
    }
    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间有-分割
     */
    public static String create36LenUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成指定位数的随机字符串
     * @param length
     * @param isNumber
     * @return
     */
    public static String randomString(int length, boolean isNumber){
        StringBuilder builder = new StringBuilder(isNumber ? RANDOM_NUMBER_RANGE : RANDOM_STRING_RANGE);
        StringBuilder resultBuilder = new StringBuilder();
        Random r = new Random();
        int range = builder.length();
        for(int i = 0; i < length; i++){
            resultBuilder.append(builder.charAt(r.nextInt(range)));
        }
        return resultBuilder.toString();
    }


    private final static String RANDOM_STRING_RANGE = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final static String RANDOM_NUMBER_RANGE = "0123456789";
}