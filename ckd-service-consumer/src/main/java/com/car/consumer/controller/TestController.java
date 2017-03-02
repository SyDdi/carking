package com.car.consumer.controller;

import javax.annotation.Resource;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.dubbo.config.annotation.Reference;

import com.car.domain.Province;
import com.car.domain.User;
import com.car.domain.dto.ResultDto;
import com.car.domain.dto.UploadFileDto;
import com.car.exception.CommonException;
import com.car.service.CommonUploadFileService;
import com.car.service.IProvinceService;
import com.car.service.IUserService;
import com.car.service.TestDubbo;
import com.github.pagehelper.PageHelper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.car.consumer.service.TestMng;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.util.List;

//@Controller
@RestController
public class TestController   {
	@Resource
	private TestMng testMng;

    @Reference
    TestDubbo testDubbo;//调用Dubbo暴露的接口

    @Reference
    IUserService IUserService;

    @Reference
    IProvinceService IProvinceService;

    @Reference


	@RequestMapping("/")
	@ResponseBody   //
	public String testDo(HttpServletRequest hsrq){
		StringBuffer str = new StringBuffer();
		str.append("service:").append(testMng.getStr());
		str.append("control:").append(testDubbo == null ? "无法找到dubbo" : testDubbo.getStr());
        return str.toString();
	}

    @RequestMapping(value="/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUser() {
        List<User> users = IUserService.selectByUser(new User(),1,1);
        return users;
    }

    @RequestMapping(value="/provinces")
    @ResponseStatus(HttpStatus.OK)
    public List<Province> getProvince() {
        Example example = new Example(Province.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andGreaterThan("id",10);
        //分页查询
        PageHelper.startPage(1, 20);
        return IProvinceService.selectByExample(example);
    }

    @RequestMapping(value="/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable Long id) {
        User user = (User) IUserService.selectByKey(id);
        return user;
    }

    @Reference
    CommonUploadFileService commonUploadFileService;

    @RequestMapping(value={"/upload"},method=RequestMethod.POST)
    public Object saveOrUpdate( @RequestParam("photo") MultipartFile file, HttpServletRequest request)throws Exception {
        if(!file.isEmpty()){
            String filename = file.getOriginalFilename();    //得到上传时的文件名
            System.out.println("upload over. "+ filename);
            UploadFileDto uploadFileDto = new UploadFileDto("jpg",file.getBytes());
            try {
                ResultDto result = commonUploadFileService.uploadFile(uploadFileDto);
                return result;
            }catch (CommonException e){
                //异常处理
            }

        }
        return "文件为空或上传失败";
    }


}
