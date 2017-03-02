package com.car.mp.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/9/22.
 */
public class CommonUtil {

    private final static String MONTH_FORMAT = "yyyy-MM";

    public static String getSpecifiedOffsetMonth(String specifiedDate, int offset) throws ParseException {
        Date date = DateUtils.parseDate(specifiedDate, MONTH_FORMAT);
        Date nextDate = DateUtils.addMonths(date,offset);
//
//        Calendar cal = DateUtils.toCalendar(date);
//        cal.add(Calendar.MONTH, offset);
        String returnDate = DateFormatUtils.format(nextDate,MONTH_FORMAT);
        return returnDate;
    }

    /**
     * 替换 Emoji 表情为*
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
        if(source != null)
        {
            Pattern emoji = Pattern.compile ("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",Pattern.UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;
            Matcher emojiMatcher = emoji.matcher(source);
            if ( emojiMatcher.find())
            {
                source = emojiMatcher.replaceAll("*");
                return source ;
            }
            return source;
        }
        return source;
    }


    public static void main(String[] args){
        try{
            System.out.println(getSpecifiedOffsetMonth("2016-08",6));
        }catch (Exception e){

        }

    }

}
