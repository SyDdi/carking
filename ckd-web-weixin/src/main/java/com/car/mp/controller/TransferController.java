package com.car.mp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.car.core.utils.PrettyDateFormat;
import com.car.domain.*;
import com.car.domain.Collection;
import com.car.enums.Color;
import com.car.mp.service.CarBrandService;
import com.car.mp.service.PushMsgService;
import com.car.mp.service.UserService;
import com.car.security.HashIdsHelper;
import com.car.service.*;
import me.chanjar.weixin.common.exception.WxErrorException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 转让
 * @author admin
 */
@Controller
@RequestMapping("/transfer")
public class TransferController extends GenericController{
	@Resource
	private CarBrandService carBrand;
	@Reference
	IVehicleService vehicleService;//调用Dubbo暴露的接口
	@Reference
	IMessageService iMessageService;
	@Reference
	CommonUploadFileService uploadService;
	@Reference
	IUserService iUserService;
	@Autowired
	UserService userService;
	@Reference
	IZoneService iZoneService;
	@Reference
	IModelService iModelService;
	@Reference
	IPictureService pictureService;
	@Reference
	IQuoteService iQuoteService;
	@Reference
	IReportService iReportService;
	@Reference
	IIssueService iIssueService;
	@Reference
	IChoiceService iChoiceService;
	@Reference
	ICollectionService iCollectionService;
	@Reference
	IWechatService wechatService;
	@Reference
	IWeixinShareService iWeixinShareService;
	@Reference
	private IWeixinPushMsgService iWeixinPushMsgService;
	@Autowired
	private PushMsgService pushMsgService;

	@RequestMapping("")
	public String index(ModelMap modelMap,
			@RequestParam("modelId") String modelId,
			@RequestParam("timeId") String timeId,
			@RequestParam("colorId") String colorId,
			@RequestParam("mile") String mile,
			@RequestParam("zoneId") String zoneId,
			@RequestParam("purchasePrice") String purchasePrice){
        try {
            Model model = iModelService.selectByKey(Integer.valueOf(modelId));
			Zone zone = iZoneService.selectByKey(Long.valueOf(zoneId));
			Vehicle vehicle = new Vehicle();
			vehicle.setProvinceId(zone.getProvinceId());
			vehicle.setZoneId(zone.getId());

            modelMap.put("model", model);
            modelMap.put("timeId", timeId);
            modelMap.put("colorId", colorId);
            modelMap.put("outerColor", Color.getName(Integer.valueOf(colorId)));
            modelMap.put("mile", mile);
			modelMap.put("zoneId", zoneId);
			modelMap.put("vehicle", vehicle);
            modelMap.put("zoneName",zone!=null ?zone.getZoneName():"");
            modelMap.put("purchasePrice", purchasePrice);
        } catch (Exception e) {
			e.printStackTrace();
        }
		return "transfer/index";
	}

	/**
	 * 新增车辆跳转
	 * @param modelMap
     * @return
     */
	@RequestMapping("newCar")
	public String newCar(HttpServletRequest request,ModelMap modelMap){
		User user = getUser(request);
		if(user != null){
			modelMap.put("userId",user.getId());
		}
		modelMap.put("timeId", "请选择");
		modelMap.put("outerColor", "请选择");
		modelMap.put("zoneName","请选择");
		return "transfer/index";
	}

	@RequestMapping("edit")
	public String edit(ModelMap modelMap,String id){
		Long vhclId = HashIdsHelper.decode(id,-1) ;
		if(vhclId == null || vhclId < 0){
			return "transfer/deletepage";
		}
		Vehicle vehicle = vehicleService.selectByKey(vhclId);
		Model model = iModelService.selectByKey(Integer.valueOf(vehicle.getModelId().toString()));
		
		try {
			Zone regZone =  iZoneService.selectByKey(vehicle.getRegZoneId());
			Zone zone =  iZoneService.selectByKey(vehicle.getZoneId());
			modelMap.put("model", model);
			modelMap.put("vehicle", vehicle);
			modelMap.put("timeId", vehicle.getRegDate());
			modelMap.put("colorId", Color.getColorId(vehicle.getOuterColor()));
			modelMap.put("outerColor", vehicle.getOuterColor());
			modelMap.put("mile", vehicle.getMiles());
			modelMap.put("publicDate", new SimpleDateFormat("YYYY-MM-dd").format(vehicle.getPublicDate()));
			modelMap.put("purchasePrice", (vehicle.getExpectPrice()/10000.00));
			modelMap.put("regZone",regZone!= null ? regZone.getZoneName():"请选择");
			modelMap.put("zoneName",zone!= null ? zone.getZoneName():"请选择");
			Picture picture = new Picture();
			picture.setVehicleId(vhclId);
			List<Picture> list = pictureService.select(picture);
			for(int i = 0 ; i<list.size() ; i++){
				modelMap.put("picpath"+list.get(i).getType(), list.get(i).getImg());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "transfer/index";
	}
	
	@RequestMapping("generate")
	public String vehicleGenerate(ModelMap modelMap,Vehicle vehicle, Double purchasePrice ,String phoneNumber ,HttpServletRequest request){
		LOG.warn(" insert vehicle ");
		User user = getUser(request);
//		try {
			String[] imgs = new String[6] ;
			for(int i = 1 ; i<7 ; i++){
				imgs[i-1] = request.getParameter("picpath"+i);
			}
			Model model = iModelService.selectByKey(Integer.valueOf(vehicle.getModelId().toString()));//由于重载，要转成Integer类型


			vehicle.setFamilyId(Long.parseLong(model.getFamilyId()+""));
			vehicle.setBrandId(Long.parseLong(model.getBrandId()+""));
			vehicle.setFactoryId(model.getFactoryId());
			vehicle.setCarYear(model.getCarYear());
			try {
				vehicle.setExpectPrice(Long.parseLong(new DecimalFormat("###########").format(purchasePrice * 10000)));
			}catch (Exception x){}

			vehicle.setTelephone(phoneNumber);
			vehicle.setPublicDate(new Date());
			Vehicle x = new Vehicle();
			if(vehicle.getId()!=null && vehicle.getId()>0){ //修改车辆信息
				 vehicle.setUpdateDate(new Date());
				 vehicle.setUpdateUser(0L);
				 int upd = vehicleService.updateNotNull(vehicle);
				 if(upd>0){
					 x.setId(vehicle.getId());
				 }
			}else {  //新增车辆信息
				if(user!= null ){
					vehicle.setUserId(user.getId());
				}
				 vehicle.setCreateDate(new Date());
				 vehicle.setStatus(Short.parseShort(1+""));
				 x=vehicleService.insert(vehicle);
				pushMsgService.messagePush(x.getId(),"用户新增车辆！",user.getNickName()+" 新增车辆！车辆编号："+x.getId(),"");
			}
			if(x!= null){
				for(int i = 0 ; i< imgs.length ; i++) {
//					if(null!=imgs[i] && !"".equals(imgs[i])) {
						Picture pic = new Picture();
						pic.setType((i + 1));
						pic.setImg(imgs[i]);
						pic.setId(null);
						pic.setVehicleId(x.getId());
						List<Picture> list = pictureService.findImgByVehiclId(x.getId(), (i + 1));
						pic.setUpdateDate(new Date());
						if (list.size() > 0) {
							pic.setId(list.get(0).getId());
							int upd = pictureService.updateAll(pic);
						} else {
							pic.setCreateDate(new Date());
							int ins = pictureService.save(pic);
						}
//					}
				}
			}
			Zone regZone =  iZoneService.selectByKey(vehicle.getRegZoneId());
			Zone zone =  iZoneService.selectByKey(vehicle.getRegZoneId());
			modelMap.put("model", model);
			modelMap.put("vehicle", x);
			modelMap.put("publicDate", new SimpleDateFormat("YYYY-MM-dd").format(vehicle.getPublicDate()));
			modelMap.put("purchasePrice", purchasePrice);
			modelMap.put("regZone",regZone!= null ? regZone.getZoneName():"");
			modelMap.put("zoneName",zone!= null ? zone.getZoneName():"");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return "redirect:/car/"+HashIdsHelper.encode(x.getId())+".html";
	}
	/**
	 * 轮播图
	 * @param
	 * @return
	 */
	@RequestMapping(value="/pictures")
	@ResponseBody
	public Map<String, Object> pictures(Long vhclId){
		Map<String, Object> model = new HashMap<String, Object>();
		Picture picture = new Picture();
		picture.setVehicleId(vhclId);
		List<Picture> list = pictureService.select(picture);
		model.put("data",list);
		return model;
	}

	/**
	 * 吧图片路径保存在数据库
	 * @param
	 * @return
	 */
	@RequestMapping(value="/saveImg")
	@ResponseBody
	public Long saveImg(String imgPath , Integer type){
		Map<String, Object> model = new HashMap<String, Object>();
		Picture picture = new Picture();
		picture.setType(type);
		picture.setImg(imgPath);
		picture.setUpdateDate(new Date());
		picture.setCreateDate(new Date());
		picture.setStatus(Short.parseShort(1+""));
		Picture pic = pictureService.insert(picture);
		if(pic!= null ){
			return pic.getId();
		}
		return 0L;
	}


	
	/*
	*//**
	 * 图片上传
	 * @param
	 * @return
	 *//*
	@RequestMapping(value="/imgUpload",method = RequestMethod.POST)
	@ResponseBody
	public String saveOrUpdate(@RequestParam("photo") MultipartFile file, HttpServletRequest request,Integer bizType )throws Exception {
		Map<String,Object> map = new HashMap<>();
		if(!file.isEmpty()){
			String filename = file.getOriginalFilename();    //得到上传时的文件名
			System.out.println("upload over. "+ filename);	UploadFileDto uploadFileDto = new UploadFileDto(FileUtils.getFileType(filename),file.getBytes());
			try {
				System.out.println(uploadService);
				ResultDto result = uploadService.uploadFile(uploadFileDto);
				return result.getFilePath();
			}catch (CommonException e){
				//异常处理
			}
		}
		return "上传图片错误！";
	}
*/
	 /** 查询车辆留言
	 * @param
	 * @return
	 */
	@RequestMapping(value="/messages")
	@ResponseBody
	public Map<String, Object> message(Long vhclId){
		Map<String, Object> model = new HashMap<String, Object>();
		Vehicle vehicle = vehicleService.selectByKey(vhclId);
		Message message = new Message();
		message.setVehicleId(vhclId);
		message.setStatus(Short.parseShort(1+""));
		List<Message> list = iMessageService.select(message);
		for(Message m :list){
			User user = iUserService.selectByKey(m.getUserId());
			if(user != null ) {
				m.setIsOnwer(0);
				if(vehicle.getUserId() == user.getId()){
						m.setIsOnwer(1);
				}
				m.setUserName(user.getNickName());
				m.setUser(user);
				
				Date create = m.getCreateDate();
				if(create!=null){
					String time = new PrettyDateFormat("@", "MM-dd").format(create.getTime());
					m.setCreateTime(time);
				}else{
					m.setCreateTime("时间获取失败");
				}
			}
		}
		model.put("data",list);
		return model;
	}
	
	 /** 查询车辆留言JSONP
	 * @param
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/messageJSONP")
	public void messageJSONP(Long vhclId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String, Object> model = new HashMap<String, Object>();
		Message message = new Message();
		message.setVehicleId((long)3);
		message.setStatus(Short.decode(1+""));
		List<Message> list = iMessageService.select(message);
		for(Message m :list){
			User user = iUserService.selectByKey(m.getUserId());
			if(user != null ) {
				m.setUserName(user.getNickName());
			}
		}
		model.put("data",list);
		JSONObject js=JSONObject.fromObject(model);
		
		String cb = request.getParameter("callback");
		String data=cb;
		data=cb+"("+js.toString()+")";
		if(cb!=null){
		}
		response.getWriter().write(data);
	}
	
	/**
	 * 车况评级
	 */
	@RequestMapping(value="/report")
	@ResponseBody
	public Map<String, Object> report(Long vhclId){
		Map<String, Object> model = new HashMap<String, Object>();
		Report rep=new Report();
		rep.setVehicleId(vhclId);
		List<Report> list = iReportService.select(rep);
		if(list.size()>0){
            Report report = list.get(0);
            String answerIds = report.getAnswerIds();
            JSONArray jsonArray=JSONArray.fromObject(answerIds);
            Iterator<Object> iterator = jsonArray.iterator();
            report.setIssue(new ArrayList<Issue>());
            List<Issue> resultList=report.getIssue();
            while(iterator.hasNext()){
                Object next = iterator.next();
                JSONObject jsonObject=JSONObject.fromObject(next);
                String ans_ids=(String) jsonObject.get("answerIds");
                String[] anss=ans_ids.split(",");
                List<Long> choiceIdList=new ArrayList<Long>();
                for (int i=0;i<anss.length;i++) {
                    if(!anss[i].equals("")&&anss[i]!=null){
                        choiceIdList.add(Long.valueOf(anss[i]));
                    }
                }
				if(choiceIdList.size() > 0) {
					List<Choice> choices = iChoiceService.findByIdsMap(choiceIdList);
					if (choices != null) {
						String choiceDesc = "";
						for (int j = 0; j < choices.size(); j++) {
							if (choices.get(j).getId() != 32) {
								choiceDesc += choices.get(j).getChoiceDesc();
							} else {
								choiceDesc += choices.get(j).getTypeDesc();
							}
							if (j != choices.size() - 1) {
								choiceDesc += "、";
							}
						}
						Long iss_id = Long.valueOf(jsonObject.get("issueId").toString());
						Issue issue = iIssueService.selectByKey(iss_id);
						issue.setAnswerIds(choiceDesc);
						if (issue.getId() == 9) {
//							System.out.println("******车辆综合评级**********：" + issue.getAnswerIds());
							report.setStar(issue.getAnswerIds().substring(0, 1));
						} else if (issue.getId() != 9) {
							resultList.add(issue);
						}
					}
				}
            }
            System.out.println(report.getReportDesc());
		    model.put("data",report);
			model.put("result",1);
		}else{
			model.put("data","");
			model.put("result",0);
		}
		return model;
	}
	
	/**
	 * 车商报价
	 * @param
	 * @return
	 */
	@RequestMapping(value="/quotes")
	@ResponseBody
	public Map<String, Object> quotes(Long vhclId){
		Map<String, Object> model = new HashMap<String, Object>();
		Quote qu = new Quote();
		qu.setVehicleId(vhclId);
		qu.setType(0);
		List<Quote> list = iQuoteService.select(qu);
		for (Quote quote : list) {
			Long dealerId = quote.getDealerId();
			User u=iUserService.selectByKey(dealerId);
			quote.setNickName(u.getNickName());
			quote.setQuoteDesc(u.getQuoteDesc());
			//System.out.println(qu.getNickName());
		}
		model.put("data",list);
		return model;
	}
	
	/**
	 * 展示
	 * @param
	 * @return
	 */
	@RequestMapping(value="/show")
	@ResponseBody
	public Map<String, Object> show(Long vhclId,Short status){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("status", status);
		try {
			if(vhclId!=null){
				Vehicle vehicle=new Vehicle();
//				Short s=(short) (status==1?0:1);
				vehicle.setId(vhclId);
				vehicle.setStatus(status);
				vehicle.setPublicDate(new Date());
				vehicle.setUpdateDate(new Date());
				vehicle.setUpdateUser(0L);
				int result=vehicleService.updateNotNull(vehicle);
				if(result>=0){
					model.put("status",status);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	/**
	 * 收藏
	 * @param
	 * @return
	 */
	@RequestMapping(value="/collect")
	@ResponseBody
	public Map<String, Object> collect(Long vhclId,Long userId,HttpServletRequest request){
		User user = getUser(request);
		Map<String, Object> model = new HashMap<String, Object>();
		Collection collect=new Collection();
		if(user !=null) {
			collect.setUserId(user.getId());
		}else{
			collect.setUserId(userId);
		}
		collect.setVehicleId(vhclId);
		User isSubscribe =iUserService.selectByKey(collect.getUserId());
		if(isSubscribe != null && isSubscribe.getSubscribe() == 1) {//验证是否订阅 没有订阅 先订阅
			try {
				List<Collection> collList = iCollectionService.select(collect);
				if (collList.size() > 0) {
					collect = collList.get(0);
					Short statu = (short) (collect.getStatus() == 1 ? 0 : 1);
					collect.setStatus(statu);
					iCollectionService.updateNotNull(collect);
				} else {
					collect.setStatus((short) 1);
					collect.setCreateDate(new Date());
					iCollectionService.save(collect);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.put("status", collect.getStatus());
		}else{
			model.put("status", 20);
		}
		return model;
	}

	/**
	 * 是否收藏
	 * @param
	 * @return
	 */
	@RequestMapping(value="/favorite")
	@ResponseBody
	public int favorite(Long vhclId,Long userId,HttpServletRequest request){
		User user = getUser(request);
		Collection collect=new Collection();
		if(user !=null) {
			collect.setUserId(user.getId());
		}else if(userId != null && userId > 0){
			collect.setUserId(userId);
		}else{
			return -1;
		}
		collect.setVehicleId(vhclId);
		List<Collection> collList = iCollectionService.select(collect);
		if(collList.size()>0){
			return collList.get(0).getStatus();
		}
		return -1;
	}

	/**
	 * 满意
	 */
	@RequestMapping(value="/satisfied")
	@ResponseBody
	public Map<String,Object> satisfied(Long quoteId,Short isSatisfied){
		System.out.println("quoteId:"+quoteId);
		System.out.println("isSatisfied:"+isSatisfied);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", 0);
		Quote quote=new Quote();
		quote.setId(quoteId);
		quote.setStatus(isSatisfied);
		try {
			int x = iQuoteService.updateNotNull(quote);
			if(x>0){
				map.put("message", quote.getStatus());
				Quote newQuote = iQuoteService.selectByKey(quoteId);
				if(newQuote!=null) {
					User dealer = iUserService.selectByKey(newQuote.getDealerId());
					if(dealer!=null) {
						pushMsgService.messagePush(newQuote.getVehicleId(), "用户是否满意车商报价!", (isSatisfied == 1 ? "满意 " : "不满意 ") + dealer.getNickName()+"报价，报价金额："+(newQuote.getQuote()/10000.00)+"万元。车辆编号："+newQuote.getVehicleId() , "");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 获取手机号
     *
	 */
	@RequestMapping(value="/phone")
	@ResponseBody
	public Map<String,Object> phone(Long vhclId,String telephone){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", 0);
		try {
			Vehicle vehicle = vehicleService.findVehicleById(vhclId);
			if(vehicle!=null) {
				Vehicle vhcl = new Vehicle();
				vhcl.setId(vhclId);
				vhcl.setTelephone(telephone);
				vhcl.setPublicDate(new Date());
				vehicle.setUpdateUser(0L);
				vehicle.setUpdateDate(new Date());
				int x = vehicleService.updateNotNull(vhcl);
				if (x > 0) {
					map.put("message", 1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 删除留言
	 * @param modelMap
     * @return
     */
	@RequestMapping("/delMsg")
	@ResponseBody
	public Map<String, Object>  delMsg(ModelMap modelMap,Long msgId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", 0);
		map.put("data", "删除失败，请联系管理员！");
		Message msg = new Message();
		msg.setId(msgId);
		msg.setStatus(Short.parseShort(0+""));
		int x = iMessageService.updateNotNull(msg);
		if(x > 0){
			map.put("message", 1);
			map.put("data", "删除成功！");
		}
		return map;
	}

	/**
	 * 验证用户信息
	 * @return
	 */
	@RequestMapping("/findUserInfo")
	@ResponseBody
	public Map<String, Object>  findUserInfo(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", 0);
		map.put("data", "用户验证失败");

		User user = getUser(request);
		if(user!=null) {
			map.put("message", 1);
			map.put("data", user.getId());
		}
		return map;
	}

	/**
	 * 保存留言
	 * @return
	 */
	@RequestMapping("/savaMessage")
	@ResponseBody
	public Map<String, Object>  savaMessage(Message msg ,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", 0);
		map.put("data", "保存失败，请联系管理员！");

		User user = iUserService.selectByKey(msg.getUserId());
		//if(user!= null && user.getSubscribe() == 1 ) {  //留言时 必须关注公众号
			Vehicle vehicle = vehicleService.selectByKey(msg.getVehicleId());

			msg.setStatus(Short.parseShort(1 + ""));
			msg.setCreateDate(new Date());
			Message x = iMessageService.insert(msg);
			/*  留言时需自动收藏该车  */
			Collection collect = new Collection();
			collect.setUserId(msg.getUserId());
			collect.setVehicleId(msg.getVehicleId());
			List<Collection> collList = iCollectionService.select(collect);
			if (collList.size() > 0) {
				collect = collList.get(0);
				collect.setStatus((short)1);
				iCollectionService.updateNotNull(collect);
			} else {
				collect.setStatus((short) 1);
				collect.setCreateDate(new Date());
				if(vehicle.getUserId() != user.getId() && !vehicle.getUserId().equals(user.getId())) {
					iCollectionService.save(collect);
				}
			}
			pushMsgService.sendWeiXinMessage(vehicle.getId(),user,msg.getContent());

			if (x != null) {
				map.put("message", 1);
				map.put("data", "保存成功！");
			}
//		}else{
//			map.put("message", 2);
//			map.put("data", "请订阅微信公众号！");
//		}
		return map;
	}

	/**
	 * 分享回调存入数据库
	 * @return
	 * @throws WxErrorException
     */
	@RequestMapping("/share")
	@ResponseBody
	public void share(Long vhclId,long userId ,int shareSource) throws WxErrorException {
		Vehicle vehicle = vehicleService.selectByKey(vhclId);

		WeixinShare wxs = new WeixinShare();
		wxs.setVehicleId(vhclId);
		wxs.setUserId(userId);
		wxs.setCreateDate(new Date());
		wxs.setType(0);
		wxs.setShareSource(shareSource);
		if(vehicle.getUserId() == userId){
			wxs.setType(1);
		}
		iWeixinShareService.insert(wxs);

	}
//	/**
//	 *  销售推送的时候 需要把消息推送到所有收藏该车的用户
//	 */
//	public void sendWeiXinMessage(Long vhclId,User user,String content){
//		try {
//			Vehicle vehicle = vehicleService.selectByKey(vhclId);
//			Model model = iModelService.selectByKey(Integer.valueOf(vehicle.getModelId() + ""));//由于重载，要转成Integer类型
//			Collection collect = new Collection();
//			collect.setVehicleId(vhclId);
//			collect.setStatus((short)1);
//			List<Collection> collList = iCollectionService.select(collect);
//			User ownerUser = iUserService.selectByKey(vehicle.getUserId()); //车主也推送消息
//			collect.setUserId(vehicle.getUserId());
//			collList.add(collect);
//			for(int i = 0 ; i< collList.size() ; i++) {//发送给所有收藏该车的人
//				User use = iUserService.selectByKey(collList.get(i).getUserId());//车主信息
//				if (use.getId() != user.getId() && !use.getId().equals(user.getId())) {//不要推送给自己
//					if (use.getSubscribe() == 1) {//必须订阅公众号
//						WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
//						templateMessage.setToUser(use.getOpenId());
//						templateMessage.setTemplateId(ApplicationConfig.MESSAGE_TEMPLATE);
////						templateMessage.setUrl(ApplicationConfig.WEIXINURL + "/car/" + vhclId + ".html");
//						//  因为 车主发布车源后 很长一段时间 不操作 服务器 session 失效后再访问 该页面 会导致看不到车主应该看到的信息 故：用下面這种方式  调用链接时获得code在LoginInteceptor拦截器中获取用户信息
//						templateMessage.setUrl(wxMpService.oauth2buildAuthorizationUrl(ApplicationConfig.WEIXINURL + "/car/" + HashIdsHelper.encode(vhclId) + ".html", "snsapi_base", ""));
//						templateMessage.setTopColor("#4C4C4C");
//						String first ="["+ownerUser.getNickName()+"] "+ model.getCarYear() + "款 " + model.getBrand() + " " + model.getFamilyGroupName() + " " + model.getFamily() + " " + model.getShortName();
//						String userContent =  user.getNickName() + "  " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//						templateMessage.getData().add(new WxMpTemplateData("first",first, "#4C4C4C"));
//						templateMessage.getData().add(new WxMpTemplateData("user",userContent, "#4C4C4C"));
//						templateMessage.getData().add(new WxMpTemplateData("ask", content, "#4C4C4C"));
//						templateMessage.getData().add(new WxMpTemplateData("remark", "点击查看新留言详情", "#019BFF"));
//						String result = wechatService.sendTemplateMsg(templateMessage);
//						WeixinPushMsg wxp = new WeixinPushMsg();
//						wxp.setStatus(0);
//						if (result!=null && !"".equals(result)){
//							wxp.setSendId(result);
//							wxp.setStatus(1);
//						}
//						wxp.setType(2);
//						wxp.setCreateDate(new Date());
//						wxp.setUserId(use.getId());
//						wxp.setVehicleId(vehicle.getId());
//						wxp.setContent(first+"###"+ user+"###"+content+"###点击查看新留言详情");
//						iWeixinPushMsgService.insert(wxp);
//					}
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	/**
//	 *  自定义推送消息给后台人员
//	 * @param vhclId 车辆id
//	 * @param message 内容
//	 * @param title title
//	 */
//	public String messagePush(Long vhclId ,String title,String message ,String remark){
//		try {
//			// 推送信息给后台工作人员
//			User user = new User();
//			user.setType(3);
//			user.setSubscribe(1);
//			List<User> list = iUserService.select(user);
//			for(User u : list) {
//				WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
//				templateMessage.setToUser(u.getOpenId());
//				templateMessage.setTemplateId(ApplicationConfig.CONTENT_PUSHING);
//				templateMessage.setUrl(wxMpService.oauth2buildAuthorizationUrl(ApplicationConfig.WEIXINURL + "/car/" + HashIdsHelper.encode(vhclId) + ".html", "snsapi_base", ""));
//				templateMessage.setTopColor("#4C4C4C");
//
//				String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
//				templateMessage.getData().add(new WxMpTemplateData("first", title, "#4C4C4C"));
//				templateMessage.getData().add(new WxMpTemplateData("keyword1", message, "#4C4C4C"));
//				templateMessage.getData().add(new WxMpTemplateData("keyword2", time, "#4C4C4C"));
//				templateMessage.getData().add(new WxMpTemplateData("remark", remark, "#019BFF"));
//				String result = wechatService.sendTemplateMsg(templateMessage);
//			// 发送消息保存到DB中
//				WeixinPushMsg wxp = new WeixinPushMsg();
//				wxp.setStatus(0);
//				if (result != null && !"".equals(result)) {
//					wxp.setSendId(result);
//					wxp.setStatus(1);
//				}
//				wxp.setType(4);
//				wxp.setCreateDate(new Date());
//				wxp.setUserId(u.getId());
//				wxp.setVehicleId(vhclId);
//				wxp.setContent(title + "###" + message + "###" + time + "###" + remark);
//				iWeixinPushMsgService.insert(wxp);
//			}
//			return "1";
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "";
//	}



}
