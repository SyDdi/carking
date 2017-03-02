package com.car.security;


import org.hashids.Hashids;

/**
 * Created by Administrator on 2016/12/17.
 * 生成类似ytb高逼格的ID
 */
public class HashIdsHelper {

    private static Hashids hashids = new Hashids("thisismysalt", 0, "0123456789abcdefghikmnoprstuvwxz");

    /**
     * 使用hashIds加密数字
     * @param num 需要加密的数值
     * @return
     */
    public static String encode(long num){
        return hashids.encode(num);
    }

    /**
     * 解密数字，
     * @param encrypt
     * @param defaultValue 当解密不出数值时，返回默认的数值
     * @return
     */

    public static long decode(String encrypt,long defaultValue){
        long numbers[] = hashids.decode(encrypt);
        if(numbers.length>0) return numbers[0];
        return defaultValue;
    }

    /**
     * @param encrypt
     * @return
     */
    public static long[] decode(String encrypt){
        return hashids.decode(encrypt);
    }



    public static void main(String[] args) {

        String hash = encode(123456789);
        System.out.println(hash);

        long result = decode("7ee4k2s",-1);

        System.out.println(result);
    }
}
