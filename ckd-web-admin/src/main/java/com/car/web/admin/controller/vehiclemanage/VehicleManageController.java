package com.car.web.admin.controller.vehiclemanage;


import com.alibaba.dubbo.config.annotation.Reference;
import com.car.Page;
import com.car.core.utils.FileUtils;
import com.car.domain.*;
import com.car.domain.Collection;
import com.car.domain.dto.ResultDto;
import com.car.domain.dto.UploadFileDto;
import com.car.enums.Color;
import com.car.exception.CommonException;
import com.car.security.HashIdsHelper;
import com.car.service.*;
import com.car.web.admin.controller.BaseController;
import com.car.web.admin.core.config.IConfig;
import com.car.web.admin.core.shiro.token.manager.TokenManager;
import com.car.web.admin.domain.UUser;
import com.car.web.admin.service.RoleService;
import com.car.web.admin.service.UUserService;
import com.car.web.admin.utils.WeiXinUtil;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/vehiclemanage")
public class VehicleManageController extends BaseController {

    Logger log = Logger.getLogger(VehicleManageController.class);
    @Resource
    private UUserService uUserService;
    @Resource
    RoleService roleService;
    @Reference
    private IVehicleService iVehicleService;
    @Reference
    private IBrandService iBrandService;
    @Reference
    private IFamilyService iFamilyService;
    @Reference
    private IModelService iModelService;
    @Reference
    private IUserService iUserService;
    @Reference
    private ICollectionService iCollectionService;
    @Reference
    private IPictureService iPictureService;
    @Reference
    private CommonUploadFileService uploadService;
    @Reference
    private IChoiceService iChoiceService;
    @Reference
    private IReportService iReportService;
    @Reference
    private IIssueService iIssueService;
    @Reference
    private IQuoteService iQuoteService;
    @Reference
    private IMessageService iMessageService;
    @Reference
    IWechatService wechatService;
    @Reference
    IWeixinShareService iWeixinShareService;
    @Reference
    private IWeixinPushMsgService iWeixinPushMsgService;


    @RequestMapping(value = "/init")
    public ModelAndView init(Vehicle vhcl, ModelMap modelMap, HttpServletRequest request, Integer pageNo) {
        pageNo = pageNo == null ? 1 : pageNo;
        Map<String, Object> map = new HashMap<>();

        if (vhcl.getId() != null && vhcl.getId() > 0) {
            map.put("id", vhcl.getId());
        }
        if (vhcl.getStartDate() != null && !"".equals(vhcl.getStartDate())) {
            map.put("stratDate", vhcl.getStartDate()+" 00:00:00");
        }
        if (vhcl.getEndDate() != null && !"".equals(vhcl.getEndDate())) {
            map.put("endDate", vhcl.getEndDate()+" 23:59:59");
        }
        if (vhcl.getBrandId() != null && vhcl.getBrandId() > 0) {
            map.put("brandId", vhcl.getBrandId());
        }
        if (vhcl.getCarYear() != null && !"".equals(vhcl.getCarYear())) {
            map.put("carYear", vhcl.getCarYear());
        }
        if (vhcl.getFamilyId() != null && vhcl.getFamilyId() > 0) {
            map.put("familyId", vhcl.getFamilyId());
        }
        if (vhcl.getModelId() != null && vhcl.getModelId() > 0) {
            map.put("modelId", vhcl.getModelId());
        }
        if (vhcl.getQuoteCountSmall() != null && vhcl.getQuoteCountSmall() > 0) {
            map.put("quoteCountSmall", vhcl.getQuoteCountSmall());
        }
        if (vhcl.getQuoteCountBig() != null && vhcl.getQuoteCountBig() > 0) {
            map.put("quoteCountBig", vhcl.getQuoteCountBig());
        }
        if (vhcl.getLastQuoteDateStart() != null && !"".equals(vhcl.getLastQuoteDateStart())) {
            map.put("lastQuoteDateStart", vhcl.getLastQuoteDateStart()+" 00:00:00");
        }
        if (vhcl.getLastQuoteDateEnd() != null && !"".equals(vhcl.getLastQuoteDateEnd())) {
            map.put("lastQuoteDateEnd", vhcl.getLastQuoteDateEnd()+" 23:59:59");
        }

        modelMap.putAll(map);//回显到页面参数
        modelMap.put("orderByColumn", (vhcl.getOrderByColumn()!=null&&!"".equals(vhcl.getOrderByColumn()) ?vhcl.getOrderByColumn():"create_date "));
        map.put("orderByColumn", (vhcl.getOrderByColumn()!=null&&!"".equals(vhcl.getOrderByColumn()) ?vhcl.getOrderByColumn():"create_date ") +" desc");
        Page<Vehicle> list = iVehicleService.findVehicle(map, pageNo, pageSize);
        for (int i = 0; i < list.getList().size(); i++) {
            Vehicle vehicle = list.getList().get(i);
            if (vehicle != null) {
                if (vehicle.getBrandId() != null && vehicle.getBrandId() > 0) {
                    Brand brand = iBrandService.selectByBrandId(vehicle.getBrandId());
                    vehicle.setBrand(brand);
                }
                if (vehicle.getFamilyId() != null && vehicle.getFamilyId() > 0) {
                    Family family = iFamilyService.selectByKey(vehicle.getFamilyId());
                    vehicle.setFamily(family);
                }
                if (vehicle.getModelId() != null && vehicle.getModelId() > 0) {
                    Model model = iModelService.selectByKey(Integer.parseInt(vehicle.getModelId() + ""));
                    vehicle.setModel(model);
                }
            }
        }
        return new ModelAndView("vehiclemanage/vehiclemanage", "page", list);
    }

    /**
     * 跳转到车辆编辑页面
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/carEdit", method = RequestMethod.GET)
    public ModelAndView carEdit(ModelMap modelMap, HttpServletRequest request, Integer vhclId) {
        return new ModelAndView("vehiclemanage/caredit", "vhclId", vhclId);
    }

    /**
     * 查询车主信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/findOwner")
    @ResponseBody
    public Map<String, Object> findOwner(ModelMap modelMap, HttpServletRequest request, Long vhclId) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("result", 1);

        Vehicle vehicle = iVehicleService.findVehicleById(vhclId);
        if (vehicle != null ) {
            User user = iUserService.selectByKey(vehicle.getUserId());
            vehicle.setUser(user);
            vehicle.setUpdateUserName("--");
            if(vehicle.getUpdateUser() != null && vehicle.getUpdateUser() > 0){
                UUser uuser = uUserService.selectByPrimaryKey(vehicle.getUpdateUser());
                if(uuser!= null){
                    vehicle.setUpdateUserName(uuser.getNickname());
                }
            }
            model.put("data", vehicle);
        } else {
            model.put("result", 0);
        }
        return model;
    }


    /**
     * 保存车主信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/saveOwner")
    @ResponseBody
    public Map<String, Object> saveOwner(HttpServletRequest request, Long vhclId, String telephone) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("result", 1);
        model.put("data", "修改成功！");
        Map map = new HashMap();
        UUser aggUser = TokenManager.getToken();
        if (aggUser != null) {

            Vehicle vehicle = iVehicleService.findVehicleById(vhclId);
            if(vehicle!=null) {
                Vehicle vhcl = new Vehicle();
                vhcl.setId(vhclId);
                vhcl.setTelephone(telephone);
                vhcl.setPublicDate(new Date());
                vhcl.setUpdateUser(aggUser.getId());
                vhcl.setUpdateDate(new Date());
                int x = iVehicleService.updateNotNull(vhcl);
                if (x < 1) {
                    model.put("data", "修改失败，请联系管理员！");
                    model.put("result", 0);
                }
            } else {
                model.put("data", "修改失败，请联系管理员！");
                model.put("result", 0);
            }
        } else {
            model.put("data", "快登录后台，没登录就别想操作 !-_-!");
            model.put("result", 0);
        }
        return model;
    }

    /**
     * 查询车主信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/findVehicle")
    @ResponseBody
    public Map<String, Object> findVehicle(ModelMap modelMap, HttpServletRequest request, Long vhclId) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("result", 1);

        Vehicle vehicle = iVehicleService.findVehicleById(vhclId);
        if (vehicle != null) {
            Collection con = new Collection();
            con.setVehicleId(vhclId);
            int count = iCollectionService.findCollCount(con);
            WeixinShare weixinShare = new WeixinShare();
            weixinShare.setVehicleId(vhclId);
            List<WeixinShare> list = iWeixinShareService.select(weixinShare);
            int direct = 0 ,indirect = 0;
            for(WeixinShare wxs : list){
                if(wxs.getType() == 1){
                    direct++;//直接分享
                }else{
                    indirect++;//简介分享
                }
            }
            String HashvhclId = HashIdsHelper.encode(vhclId) ;
            model.put("HashvhclId", HashvhclId);
            model.put("direct", direct);
            model.put("indirect", indirect);
            model.put("shareCount", count);
            model.put("data", vehicle);
        } else {
            model.put("result", 0);
        }
        return model;
    }

    /**
     * 保存车主信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/saveShare")
    @ResponseBody
    public Map<String, Object> saveShare(HttpServletRequest request, Long vhclId, Integer isDelete) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("result", 1);
        model.put("data", "修改成功！");
        Map map = new HashMap();
        UUser aggUser = TokenManager.getToken();
        if (aggUser != null) {
            Vehicle vehicle = iVehicleService.findVehicleById(vhclId);
            if (vehicle != null) {
                Vehicle vhcl = new Vehicle();
                vhcl.setId(vhclId);
                vhcl.setIsDelete(isDelete);
                vhcl.setPublicDate(new Date());
                vhcl.setUpdateDate(new Date());
                vhcl.setUpdateUser(aggUser.getId());
                int x = iVehicleService.updateNotNull(vhcl);
                if (x < 1) {
                    model.put("data", "修改失败，请联系管理员！");
                    model.put("result", 0);
                }
            } else {
                model.put("data", "修改失败，请联系管理员！");
                model.put("result", 0);
            }
        } else {
            model.put("data", "快登录后台，没登录就别想操作 !-_-!");
            model.put("result", 0);
        }
        return model;
    }


    /**
     * 保存车主信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/imgUpload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveOrUpdate(@RequestParam("photo") MultipartFile file, HttpServletRequest request, Integer bizType, Long vhclId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (!file.isEmpty()) {
            String filename = file.getOriginalFilename();    //得到上传时的文件名
            UploadFileDto uploadFileDto = new UploadFileDto(FileUtils.getFileType(filename), file.getBytes());
            try {
                ResultDto result = uploadService.uploadFile(uploadFileDto);
                if (result != null) {
                    Picture pic = new Picture();
                    pic.setType(bizType);
                    pic.setImg(result.getFilePath());
                    pic.setId(null);
                    pic.setVehicleId(vhclId);
                    List<Picture> list = iPictureService.findImgByVehiclId(vhclId, bizType);
                    if (list.size() > 0) {
                        pic.setUpdateDate(new Date());
                        pic.setId(list.get(0).getId());
                        int x = iPictureService.updateAll(pic);
                    } else {
                        pic.setCreateDate(new Date());
                        pic.setUpdateDate(new Date());
                        int x = iPictureService.save(pic);
                    }
                }
                map.put("data", result.getFilePath());

                UUser aggUser = TokenManager.getToken();
                Vehicle vehicle = new Vehicle();
                vehicle.setId(vhclId);
                vehicle.setUpdateDate(new Date());
                vehicle.setUpdateUser(aggUser.getId());
                vehicle.setPublicDate(new Date());
                iVehicleService.updateNotNull(vehicle);
            } catch (CommonException e) {
                //异常处理
                map.put("data", "上传图片错误！");
            }
        }
        return map;
    }

    /**
     * 查询车主信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/findCarImage")
    @ResponseBody
    public Map<String, Object> findCarImage(ModelMap modelMap, HttpServletRequest request, Long vhclId) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("result", 1);
        Collection con = new Collection();
        con.setVehicleId(vhclId);
        List<Picture> list = iPictureService.findImgByVehiclId(vhclId, 0);
        model.put("data", list);
        return model;
    }

    /**
     * 查询车辆基本信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/findReport")
    @ResponseBody
    public Map<String, Object> findReport(Long vhclId) {
        Map<String, Object> map = new HashMap<>();
        map.put("result", 1);
        Report re = new Report();
        re.setVehicleId(vhclId);
        re.setStatus(1);
        List<Report> reportList = iReportService.select(re);
        if (reportList.size() > 0) {
            Report report = this.findIssue(reportList.get(0));
            map.put("data", report);
        } else {
            Report report = this.findIssue(new Report());
            map.put("data", report);
        }
        return map;
    }

    public Report findIssue(Report report) {
        String[] issueStr = report.getIssueIds() != null && !"".equals(report.getIssueIds()) ? report.getIssueIds().split(",") : null;
        List<Long> longList = new ArrayList<>();
        if (issueStr != null) {
            for (String str : issueStr) {
                longList.add(Long.parseLong(StringUtils.isEmpty(str) ? "0" : str));
            }
        }
        IssueExample issue1 = new IssueExample();
        IssueExample.Criteria criteria = issue1.createCriteria();
        criteria.andStatusEqualTo(1);
        if (longList.size() > 0) {
            criteria.andIdIn(longList);
        }
        List<Issue> list = iIssueService.selectByExample(issue1);
        report.setIssue(list);
        for (Issue isu : list) {
            ChoiceExample ce = new ChoiceExample();
            ce.createCriteria().andIssueIdEqualTo(isu.getId());
            List<Choice> choiceList = iChoiceService.selectByExample(ce);
            isu.setChoice(choiceList);
        }
        Long userId = report.getUpdateUser();
        if (userId == null || userId <= 0) {
            userId = report.getCreateUser();
        }
        UUser uuser = uUserService.selectByPrimaryKey(userId);
        if (uuser != null) {
            report.setUpdateUserName(uuser.getNickname());
        }
        return report;
    }

    /**
     * 修改车辆基本信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/saveReport")
    @ResponseBody
    public Map<String, Object> saveReport(Report report, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("result", 1);
        model.put("data", "车辆车况信息保存失败！");
        UUser aggUser = TokenManager.getToken();
        Report re = new Report();
        re.setVehicleId(report.getVehicleId());
        List<Report> list = iReportService.select(re);
        report.setUpdateDate(new Date());
        report.setUpdateUser(aggUser.getId());
        if (list.size() > 0) {
            report.setId(list.get(0).getId());
            int x = iReportService.updateNotNull(report);
        } else {
            report.setStatus(1);
            report.setCreateDate(new Date());
            report.setCreateUser(aggUser.getId());
            Report x = iReportService.insert(report);
        }
        model.put("data", "车辆车况信息保存成功！");
        return model;
    }

    /**
     * 修改车辆基本信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/updCarInfo")
    @ResponseBody
    public Map<String, Object> updCarInfo(Vehicle vehicle, HttpServletRequest request, HttpServletResponse response) {
        UUser aggUser = TokenManager.getToken();
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("result", 1);
        String models = vehicle.getStrModel();
        String series = vehicle.getSeries();

        if (!StringUtils.isEmpty(models)) {
            vehicle.setModelId(Long.parseLong(models.substring(models.indexOf(",") + 1)));
        }
        if (!StringUtils.isEmpty(series)) {
            Model model = iModelService.selectByKey(Integer.parseInt(vehicle.getModelId() + ""));
            if (model != null) {
                vehicle.setFamilyId(Long.parseLong(model.getFamilyId() + ""));
            }
        }
        vehicle.setExpectPrice(Long.parseLong(new DecimalFormat("###########").format(vehicle.getCarPrice() * 10000)));
        vehicle.setUpdateUser(aggUser.getId());
        vehicle.setUpdateDate(new Date());
        vehicle.setPublicDate(new Date());
        int x = iVehicleService.updateNotNull(vehicle);
        if (x > 0) {
            modelMap.put("data", "修改成功！");
        } else {
            modelMap.put("result", 0);
            modelMap.put("data", "修改失败，请联系管理员！");
        }
        return modelMap;
    }

    /**
     * 查询车商报价
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/findCarQuote")
    @ResponseBody
    public Map<String, Object> findCarQuote(ModelMap modelMap, Long vhclId) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("result", 1);
        Quote quote = new Quote();
        quote.setVehicleId(vhclId);
        quote.setType(0);
        List<Quote> list = iQuoteService.select(quote);
        for (Quote qt : list) {
            UUser uuser = uUserService.selectByPrimaryKey(qt.getUpdateUser());
            if (uuser != null) {
                qt.setUpdateUserName(uuser.getNickname());
            }
            User user = iUserService.selectByKey(qt.getDealerId());
            if (user != null) {
                qt.setDealerName(user.getNickName());
            }
        }
        model.put("data", list);
        return model;
    }

    /**
     * 查询车商列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/findAddQuote")
    @ResponseBody
    public Map<String, Object> findAddQuote(ModelMap modelMap, Long vhclId) {
        Quote tempQuote=new Quote();
        tempQuote.setVehicleId(vhclId);
        tempQuote.setType(0);
        List<Quote> quotes=iQuoteService.select(tempQuote);
        List<Long> userIds = new ArrayList<>();
        userIds.add(0L);
        for(int i = 0 ; i<quotes.size() ; i++ ){
            userIds.add(quotes.get(i).getDealerId());
        }
        Map<String, Object> model = new HashMap<>();
        List<User> list = iUserService.selectUserByInOrNotInUserId(userIds,2,1);

        model.put("result", 1);
        model.put("data", list);
        return model;
    }

    /**
     * 新增车商报价
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/addQquote")
    @ResponseBody
    public Map<String, Object> addQquote(Quote quote ,Double bidPrice) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("result", 0);
        model.put("data", "报价失败！");
        UUser aggUser = TokenManager.getToken();

        Quote tempQuote=new Quote();
        tempQuote.setDealerId(quote.getDealerId());
        tempQuote.setVehicleId(quote.getVehicleId());
        tempQuote.setType(0);
        List<Quote> quotes=iQuoteService.select(tempQuote);
        if(quotes.size()!=0){
            model.put("result", 0);
            model.put("data", "该款车源该车商已报过价！");
            return model;
        }
        if(bidPrice!=null){
        	quote.setQuote(Long.parseLong(new DecimalFormat("######").format(bidPrice * 10000)));
        }else{
            quote.setQuote(0L);
        }
        quote.setType(0);
        quote.setUpdateUser(aggUser.getId());
        quote.setUpdateDate(new Date());
        quote.setCreateDate(new Date());
        Quote x = iQuoteService.insert(quote);

        Vehicle vehicle = iVehicleService.findVehicleById(quote.getVehicleId());
        if(vehicle.getQuoteCount() == null){
            vehicle.setQuoteCount(0);
        }
        vehicle.setQuoteCount(vehicle.getQuoteCount()+1);
        vehicle.setLastQuoteDate(new Date());
        iVehicleService.updateNotNull(vehicle);

        if (x != null) {
            model.put("result", 1);
            model.put("data", "报价成功！");
            sendQuoteMessage(x);
        }
        return model;
    }

    /**
     * 新增车商报价
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/pushMsg")
    @ResponseBody
    public Map<String, Object> pushMsg(Long vhclId ,String content ,String title,String remark) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("result", 0);
        model.put("data", "消息推送失败！");
        UUser aggUser = TokenManager.getToken();
        String result =  messagePush(vhclId,title,content,remark);
        if(!StringUtils.isEmpty(result)){
            model.put("result", 1);
            model.put("data", "消息推送成功！");
        }
        return model;
    }

    /*
     * 发送车商报价微信公众号推送消息
     */
    public void sendQuoteMessage(Quote quote){
        try {
            UUser aggUser = TokenManager.getToken();
            Vehicle vehicle = iVehicleService.selectByKey(quote.getVehicleId());
            Model model1 = iModelService.selectByKey(Integer.valueOf(vehicle.getModelId() + ""));//由于重载，要转成Integer类型
            User ownerUser = iUserService.selectByKey(vehicle.getUserId());//车主信息
            User bidUser = iUserService.selectByKey(quote.getDealerId());

            WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
            templateMessage.setToUser(ownerUser.getOpenId());
            templateMessage.setTemplateId(IConfig.get("UPDATE_ORDER_TO_REMIND"));
//            templateMessage.setUrl(IConfig.get("weixinURL")+"/car/"+quote.getVehicleId()+".html");
            templateMessage.setUrl(WeiXinUtil.oauth2buildAuthorizationUrl(IConfig.get("weixinURL")+ "/car/"+ HashIdsHelper.encode(quote.getVehicleId())+".html", "snsapi_base", ""));
            templateMessage.setTopColor("#4C4C4C");

            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
            String keyword5 =  model1.getCarYear()+ "款 " +model1.getBrand()+" "+model1.getFamilyGroupName()+" "+model1.getFamily()+" "+model1.getShortName();

            templateMessage.getData().add(new WxMpTemplateData("first", "您收到一条车商报价信息，请查看！", "#4C4C4C"));
            templateMessage.getData().add(new WxMpTemplateData("keyword1", "[车辆ID]"+vehicle.getId(), "#4C4C4C"));
            templateMessage.getData().add(new WxMpTemplateData("keyword2", bidUser.getNickName()+"  已报价", "#4C4C4C"));
            templateMessage.getData().add(new WxMpTemplateData("keyword3", time, "#4C4C4C"));
            templateMessage.getData().add(new WxMpTemplateData("keyword4", (quote.getQuote()/10000.00)+"万元", "#4C4C4C"));
            templateMessage.getData().add(new WxMpTemplateData("keyword5",keyword5, "#4C4C4C"));
            templateMessage.getData().add(new WxMpTemplateData("remark", "点击查看车商报详情", "#019BFF"));
            String result = wechatService.sendTemplateMsg(templateMessage);
            WeixinPushMsg wxp = new WeixinPushMsg();
            wxp.setStatus(0);
            if (result!=null && !"".equals(result)){
                wxp.setSendId(result);
                wxp.setStatus(1);
            }
            wxp.setType(1);
            wxp.setCreateDate(new Date());
            wxp.setUserId(ownerUser.getId());
            wxp.setVehicleId(vehicle.getId());
            wxp.setSendUser(aggUser.getId());
            wxp.setContent("您收到一条车商报价信息，请查看！###"+vehicle.getId()+"###"+ bidUser.getNickName()+"###"+time+"###"+(quote.getQuote()/10000.00)+"万元"+"###"+keyword5+"###点击查看车商报详情");
            iWeixinPushMsgService.insert(wxp);
            log.info("=========== 发送车商报价微信公众号推送消息  ===========  id: "+result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  后台自定义推送消息
     * @param vhclId 车辆id
     * @param message 内容
     * @param title title
     */
    public String messagePush(Long vhclId ,String title,String message ,String remark){
        try {

            UUser aggUser = TokenManager.getToken();
            Vehicle vehicle = iVehicleService.selectByKey(vhclId);
            User ownerUser = iUserService.selectByKey(vehicle.getUserId());//车主信息
            WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
            templateMessage.setToUser(ownerUser.getOpenId());
            templateMessage.setTemplateId(IConfig.get("CONTENT_PUSHING"));
            templateMessage.setUrl(WeiXinUtil.oauth2buildAuthorizationUrl(IConfig.get("weixinURL") + "/car/" + HashIdsHelper.encode(vhclId) + ".html", "snsapi_base", ""));
            templateMessage.setTopColor("#4C4C4C");

            if (StringUtils.isEmpty(title)) {
                title = "您收到一条行情价信息，请查看！";
            }
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
            templateMessage.getData().add(new WxMpTemplateData("first", title, "#4C4C4C"));
            templateMessage.getData().add(new WxMpTemplateData("keyword1", message, "#4C4C4C"));
            templateMessage.getData().add(new WxMpTemplateData("keyword2", time, "#4C4C4C"));
            templateMessage.getData().add(new WxMpTemplateData("remark", remark, "#019BFF"));
            String result = wechatService.sendTemplateMsg(templateMessage);
            log.info("=========== 发送自定义消息给车主  ===========  id: " + result);
            WeixinPushMsg wxp = new WeixinPushMsg();
            wxp.setStatus(0);
            if (result!=null && !"".equals(result)){
                wxp.setSendId(result);
                wxp.setStatus(1);
            }
            wxp.setType(3);
            wxp.setCreateDate(new Date());
            wxp.setUserId(ownerUser.getId());
            wxp.setVehicleId(vhclId);
            wxp.setSendUser(aggUser.getId());
            wxp.setContent(title+"###"+message+"###"+time+"###"+remark);
            iWeixinPushMsgService.insert(wxp);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 是否接受车商报价
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/updCarQuote")
    @ResponseBody
    public Map<String, Object> updCarQuote(Quote quote, ModelMap modelMap,Double bidPrice) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("result", 0);
        model.put("data", "更新失败！");
        UUser aggUser = TokenManager.getToken();
        Long qId = quote.getId();

        // 将原来的报价改成无效 在新增一条报价信息
        Quote oldQuote = iQuoteService.selectByKey(qId);
        oldQuote.setType(1);
        oldQuote.setUpdateDate(new Date());
        oldQuote.setUpdateUser(aggUser.getId());
        iQuoteService.updateNotNull(oldQuote);

        Quote updQuote = new Quote();
        updQuote.setDealerId(oldQuote.getDealerId());
        updQuote.setVehicleId(oldQuote.getVehicleId());
        if(bidPrice!=null){
            updQuote.setQuote(Long.parseLong(new DecimalFormat("######").format(bidPrice * 10000)));
        }else{
            updQuote.setQuote(0L);
        }
        updQuote.setStatus(quote.getStatus());
        updQuote.setId(null);
        updQuote.setCreateDate(oldQuote.getCreateDate());
        updQuote.setUpdateDate(new Date());
        updQuote.setUpdateUser(aggUser.getId());
        updQuote.setType(0);
        Quote x = iQuoteService.insert(updQuote);
        if (x != null) {
            model.put("result", 1);
            model.put("data", "更新成功！");
            if(oldQuote!= null && oldQuote.getQuote()!= updQuote.getQuote() && !oldQuote.getQuote().equals(updQuote.getQuote())){
                // 修改 车辆报价次数和时间
                Vehicle vehicle = iVehicleService.findVehicleById(oldQuote.getVehicleId());
                if(vehicle.getQuoteCount() == null){
                    vehicle.setQuoteCount(0);
                }
                vehicle.setQuoteCount(vehicle.getQuoteCount()+1);
                vehicle.setLastQuoteDate(new Date());
                iVehicleService.updateNotNull(vehicle);

                Quote newQuote = iQuoteService.selectByKey(x.getId());
                sendQuoteMessage(newQuote);//推送报价信息给车主
            }
        }
        return model;
    }
    /**
     * 删除车商报价
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/delCarQuote")
    @ResponseBody
    public Map<String, Object> delCarQuote(Quote quote, ModelMap modelMap) {
    	Map<String, Object> model = new HashMap<String, Object>();
        UUser aggUser = TokenManager.getToken();
    	model.put("result", 0);
    	model.put("data", "删除失败！");
    	if(quote.getId()!=null){
            Quote q = new Quote();
            q.setId(quote.getId());
            q.setType(2);
            q.setUpdateDate(new Date());
            q.setUpdateUser(aggUser.getId());
    		int x = iQuoteService.updateNotNull(q);
    		if (x > 0) {
    			model.put("result", 1);
    			model.put("data", "删除成功！");
    		}
    	}
    	return model;
    }

    /**
     * 保存答案  其他选项 填写的内容
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/updChoice")
    @ResponseBody
    public Map<String, Object> updChoice(String choiceDesc, Long id) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("result", 1);
        Choice ch = new Choice();
        ch.setId(id);
        ch.setTypeDesc(choiceDesc);
        int x = iChoiceService.updateNotNull(ch);
        if (x > 0) {
            model.put("data", "其他选项内容保存成功！");
        }
        return model;
    }

    /**
     * 查询车辆留言
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/findcarMessage")
    @ResponseBody
    public Map<String, Object> findcarMessage(Long vhclId) {
        Map<String, Object> model = new HashMap<String, Object>();
        Message message = new Message();
        message.setVehicleId(vhclId);
        message.setStatus(Short.decode(1 + ""));
        List<Message> list = iMessageService.select(message);
        for (Message m : list) {
            User user = iUserService.selectByKey(m.getUserId());
            if (user != null) {
                m.setUserName(user.getNickName());
            }
        }
        model.put("data", list);
        return model;
    }


    /**
     * 删除车辆留言
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/carMessageDel")
    @ResponseBody
    public Map<String, Object> carMessageDel(Long id) {
        Map<String, Object> model = new HashMap<>();
        Message m = new Message();
        m.setId(id);
        m.setStatus(Short.decode(0 + ""));
        int x = iMessageService.updateNotNull(m);
        model.put("data", "留言删除失败！");
        if (x > 0) {
            model.put("result", 1);
            model.put("data", "留言删除成功！");
        }
        return model;
    }

    /**
     * 删除车辆留言
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/findColor")
    @ResponseBody
    public Map<String, Object> findColor() {
        Map<String, Object> model = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (Color c : Color.values()) {
            list.add(c.getName());
        }
        model.put("data", list);
        model.put("result", 1);
        return model;
    }

    @RequestMapping(value = "/text")
    public ModelAndView text() {
        Vehicle vehicle = iVehicleService.findVehicleById(121L);
        return new ModelAndView("vehiclemanage/text", "page", vehicle);
    }


}



