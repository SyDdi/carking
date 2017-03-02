package com.car.service;

/**
 * Created by Administrator on 2016/12/7.
 */
public interface SensitiveWordService {

    /**
     * 是否包含敏感词
     * @param text
     * @return
     */
    public boolean isContain(String text);

    /**
     * 合法化过滤
     * @param text 原始文本
     * @param replacement 敏感词替换字符
     * @return
     */
    public String filter(String text,String replacement);


}
