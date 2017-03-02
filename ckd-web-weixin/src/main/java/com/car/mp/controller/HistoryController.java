package com.car.mp.controller;

import com.car.domain.User;
import com.car.mp.domain.HistoryEntity;
import com.car.mp.service.HistoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by x201 on 2016/9/19.
 */
@Controller
@RequestMapping("/pinggu/history")
public class HistoryController extends GenericController{
    @Resource
    private HistoryService historyService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String historyList( ModelMap modelMap,HttpServletRequest request) {
        try{
            User user = getUser(request);
            if(user!=null) {
                String openId = user.getOpenId();
                List<HistoryEntity> list = historyService.getOperationRecorders(openId);
                modelMap.put("list", list);

                modelMap.put("openId",openId);
                modelMap.put("url",wxMpService.oauth2buildAuthorizationUrl("http://m.andaxauto.com/getUserInfo?lang=zh_CN&openid=oquT2wBdzhZRCknQRAY4so4x0xXE", "snsapi_userinfo", ""));

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "pinggu/history_list";
    }
}
