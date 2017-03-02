package com.car.mp.controller;

import com.car.mp.domain.CarAreaEntity;
import com.car.mp.domain.PriceAlarmEntity;
import com.car.mp.domain.dto.ModelDTO;
import com.car.mp.domain.dto.PriceAlarmDTO;
import com.car.mp.service.CarAreaService;
import com.car.mp.service.PriceAlarmService;
import com.car.mp.service.SegmentService;
import com.car.mp.util.Helper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 新车报价
 */
@Controller
@RequestMapping("/price")
public class PriceController extends GenericController {

	@Resource
	private PriceAlarmService priceAlarmService;
	@Resource
	private CarAreaService carAreaService;
    @Resource
    private SegmentService segmentService;

	final static Logger LOG = org.slf4j.LoggerFactory.getLogger(PriceController.class);

    
    private void parseQuery(String q, PriceAlarmDTO dto){
        ModelDTO modelDTO = segmentService.getModelDTO(q);
        if(modelDTO==null) return;
        String brand = modelDTO.getBrand();
        String factory = modelDTO.getFactory();
        String family = modelDTO.getFamily();
        //临时处理分词缺陷
        if(StringUtils.isNotBlank(brand)&& StringUtils.isNotBlank(family)&&brand.equalsIgnoreCase(family)) brand=null;
        //todo parse q to brand factory family
        if(StringUtils.isNotBlank(brand)) dto.setBrand(brand);
        if(StringUtils.isNotBlank(factory)) dto.setFactory(factory);
        if(StringUtils.isNotBlank(family)) dto.setFamily(family);
    }
    
	/**
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public String list(ModelMap modelMap, HttpServletRequest request) {
		try {
            int pageIndex = Helper.parseInt(request.getParameter("page"));
            String q = request.getParameter("q");
            int area = Helper.parseInt(request.getParameter("area"));

            PriceAlarmDTO dto = new PriceAlarmDTO();
//            dto.setDate(date);
            dto.setPageSize(DEFAUTL_PAGE_SIZE);
            dto.setPageIndex(pageIndex);
            if (0 < area) {dto.setAreaId(area); }
//          将关键词分词
            parseQuery(q,dto);
            List<PriceAlarmEntity> list = priceAlarmService.list(dto);
            int record = priceAlarmService.listCount(dto);
            int totalPage = record/dto.getPageSize();
            if(record%dto.getPageSize()>0) totalPage+=1;

            modelMap.put("list", list);
            modelMap.put("areaId", area);
            modelMap.put("curPage",pageIndex<1?1:pageIndex);
            modelMap.put("totalPage",totalPage);
            modelMap.put("q",q);

            //获得地区名称
            CarAreaEntity areaEntity = carAreaService.get(area);
            if(areaEntity==null) {
                modelMap.put("areaName", "请选择");
            }else{
                modelMap.put("areaName",areaEntity.getCarAreaName());
            }
            modelMap.put("area",area);
            modelMap.put("yesterday", Helper.getYesterday());
		} catch (Exception e) {
			LOG.info("list | 新车价格预警 | 查询列表  | 异常:{}。", e);
		}
		return "price/list";
	}

    /**
     * 获取热门车型列表
     * @return
     */
    @RequestMapping(value = "/list", produces="application/json;charset=UTF-8", method = RequestMethod.GET)
    @ResponseBody
    public  String getPriceList(HttpServletRequest request) {
        try {
            int pageIndex = Helper.parseInt(request.getParameter("page"));
            String q = request.getParameter("q");
            int area = Helper.parseInt(request.getParameter("area"));

            PriceAlarmDTO dto = new PriceAlarmDTO();
            dto.setPageSize(DEFAUTL_PAGE_SIZE);
            dto.setPageIndex(pageIndex);
            if (0 < area) {dto.setAreaId(area); }

//            将关键词分词
            parseQuery(q,dto);
            List<PriceAlarmEntity> list = priceAlarmService.list(dto);
            int record = priceAlarmService.listCount(dto);
            int totalPage = record/dto.getPageSize();
            if(record%dto.getPageSize()>0) totalPage+=1;

            JSONObject obj = new JSONObject();
            obj.put("status", 0);
            obj.put("message", "成功");
            obj.put("totalPage",totalPage);
            obj.put("data", JSONArray.fromObject(list));
            return obj.toString();
        } catch(Exception e){
            LOG.info(e.getMessage());
            return "";
        }
    }
}
