package com.car.nlp.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.car.service.SensitiveWordService;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.io.IOUtil;
import com.hankcs.hanlp.corpus.util.StringUtils;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */
@Service
public class SensitiveWordServiceImpl implements SensitiveWordService {

//    @Value("${dictionary.root}")
//    private static String root;

    //初始化敏感词字典
    static {
        String txt = IOUtil.readTxt("data/words.txt");
        String words[] = txt.split("\\|");
        System.out.println(System.currentTimeMillis());
        for(String word:words) {
            word = StringUtils.cleanBlankOrDigit(word);
            CustomDictionary.add(word.trim(),"bad 1");
        }
        System.out.println(System.currentTimeMillis());
    }

    @Override
    public boolean isContain(String text) {
        return false;
    }

    @Override
    public String filter(String text, String replacement) {
        List<Term> terms = HanLP.segment(text);
        if(terms==null) return text;
        StringBuffer sb = new StringBuffer();
        for(Term t:terms){
            if("bad".equalsIgnoreCase(t.nature.name())){
                sb.append(replacement);
            }else {
                sb.append(t.word);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SensitiveWordServiceImpl s = new SensitiveWordServiceImpl();
        String text = "我是一个java程序员，单不是一个法轮功分子,我通过了三级英语考试";
        System.out.println(s.filter(text,"**"));
    }
}
