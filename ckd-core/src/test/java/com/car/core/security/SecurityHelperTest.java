package com.car.core.security;

import com.car.core.utils.PrettyDateFormat;
import org.junit.Test;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/12/17.
 */
public class SecurityHelperTest {

    @Test
    public void test(){
        String encryptTxt = "";
        String plainTxt = "hello oh my god";
        try {
            System.out.println(plainTxt);
            encryptTxt = SecurityHelper.encrypt("mypassword01", plainTxt);
            plainTxt = SecurityHelper.decrypt("mypassword01", encryptTxt);
            System.out.println(encryptTxt);
            System.out.println(plainTxt);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
