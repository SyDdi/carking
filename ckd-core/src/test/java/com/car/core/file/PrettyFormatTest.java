package com.car.core.file;

import com.car.core.utils.PrettyDateFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/2.
 */

public class PrettyFormatTest {

    @Test
    public void test(){
        PrettyDateFormat format =new PrettyDateFormat("@", "MM-dd");
        Calendar now = Calendar.getInstance();

        for(int i = 0;i<20;i++){
            now.add(Calendar.SECOND,-600*i);
            System.out.println(format.format(now.getTime()));
        }
    }
}
