package com.car.mp.controller.transfer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.car.domain.Collection;
import com.car.domain.Model;
import com.car.domain.User;
import com.car.domain.Vehicle;
import com.car.mp.controller.GenericController;
import com.car.mp.service.CarBrandService;
import com.car.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 我的收藏、我的车
 * @author admin
 */
@Controller
@RequestMapping("/my")
public class MyFavoriteController extends GenericController{
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
	@Reference
	IZoneService iZoneService;
	@Reference
	IModelService iModelService;
	@Reference
	IPictureService pictureService;
	@Reference
	private ICollectionService iCollectionService;

	@RequestMapping("")
	public String init(ModelMap modelMap ,HttpServletRequest request){
		User user = getUser(request);
		if(user != null ){
			modelMap.put("userId",user.getId());
		}
		return "transfer/myfavorite";
//		return "transfer/init";
	}

	/**
	 * 查询我的车
	 * @param request
	 * @param userId
     * @return
     */
	@RequestMapping("/car")
	@ResponseBody
	public Map<String,Object> findMycar(HttpServletRequest request,Long userId){
		Map<String,Object> map = new HashMap<>();
		map.put("result",0);
		map.put("data", "");
		if(userId !=null && userId>0) {
			List<Vehicle> list = vehicleService.selectVhclByUserId(userId,0);
			for(Vehicle v : list){
				if(v.getModelId() != null) {
					Model model = iModelService.selectByKey(Integer.parseInt(v.getModelId() + ""));
					v.setModel(model);
				}
			}
			map.put("data", list);
			map.put("result",1);
		}
		return map;
	}
	/**
	 * 查询我的收藏
	 * @param request
	 * @param userId
	 * @return
	 */
	@RequestMapping("/favorite")
	@ResponseBody
	public Map<String,Object> findFavorite(HttpServletRequest request,Long userId){
		Map<String,Object> map = new HashMap<>();
		map.put("result",0);
		map.put("data", "");
		try {
			if(userId>0) {
				Collection coll = new Collection();
				coll.setUserId(userId);
				coll.setStatus(Short.parseShort(1+""));//未删除车辆
				List<Collection> list = iCollectionService.select(coll);
				for(Collection c : list){
					Vehicle vehi = vehicleService.selectByKey(c.getVehicleId());
					if(vehi!=null){
						Model model=iModelService.selectByKey(vehi.getModelId().intValue());
						vehi.setModel(model);
						c.setVehicle(vehi);
					}
				}
				map.put("data", list);
				map.put("result",1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 我的车 - 删除车辆
	 * @return
	 */
	@RequestMapping("/updCarStatus")
	@ResponseBody
	public Map<String,Object> updCarStatus(HttpServletRequest request,Long userId ,Long vhclId){
		Map<String,Object> map = new HashMap<>();
		map.put("result",0);
		map.put("data", "删除失败,请联系管理员！");
		if(userId>0) {
			Vehicle vhcl = new Vehicle();
			vhcl.setUserId(userId);
			vhcl.setIsDelete(0);//未删除车辆
			vhcl.setId(vhclId);
			Vehicle list = vehicleService.selectByKey(vhclId);
			if( list!=null){
				Vehicle vehicle = new Vehicle();
				vehicle.setId(list.getId());
				vehicle.setIsDelete(1);
				vehicle.setUpdateDate(new Date());
				vehicle.setUpdateUser(userId);
				int x = vehicleService.updateNotNull(vehicle);
				if(x>0){
					map.put("data", "已删除！");
					map.put("result",1);
				}
			}
		}
		return map;
	}
	/**
	 * 我的车 - 删除收藏
	 * @return
	 */
	@RequestMapping("/updCollStatus")
	@ResponseBody
	public Map<String,Object> updCollStatus(HttpServletRequest request,Long collId){
		Map<String,Object> map = new HashMap<>();
		map.put("result",0);
		map.put("data", "取消收藏失败,请联系管理员！");
		if(collId>0) {
			Collection coll = iCollectionService.selectByKey(collId);
			if( coll!=null){
				coll.setStatus((short) 0);
				int x = iCollectionService.updateNotNull(coll);
				if(x>0){
					map.put("data", "已取消收藏！");
					map.put("result",1);
				}
			}
		}
		return map;
	}
}
