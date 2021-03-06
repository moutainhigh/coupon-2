package com.roof.coupon.customer.controller;

import com.roof.coupon.customer.entity.Customer;
import com.roof.coupon.customer.entity.CustomerVo;
import com.roof.coupon.customer.service.api.ICustomerService;
import com.roof.coupon.wechat.service.impl.WeChatDto;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Api(value = "customer", description = "顾客管理")
@Controller
@RequestMapping("coupon/wechat")
public class CustomerWechatController {
	private ICustomerService customerService;



	private ExecutorService executorService = Executors.newFixedThreadPool(1);






	@RequestMapping(value = "customer", method = {RequestMethod.POST})
	public @ResponseBody Result create_v2(@RequestBody CustomerVo customer) {
		if (customer != null) {
			if(StringUtils.isEmpty(customer.getCode())){
				return new Result(Result.FAIL,"code不能为空");
			}
			WeChatDto weChatDto =  customerService.saveOrUpdate(customer);
			return new Result(Result.SUCCESS,weChatDto);
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

	@RequestMapping(value = "customer/{openid}", method = {RequestMethod.GET})
	public @ResponseBody Result<CustomerVo> loadByOpenId(@PathVariable String openid) {
		CustomerVo customerVo = customerService.loadByOpenid(openid);
		return new Result(Result.SUCCESS,customerVo);
	}

    @RequestMapping(value = "customer/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<CustomerVo> load(@PathVariable Long id) {
		CustomerVo customerVo = customerService.load(new Customer(id));
        return new Result(Result.SUCCESS,customerVo);
    }
	
	@RequestMapping(value = "customer/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody Customer customer) {
		if (id != null && customer != null) {
			customer.setId(id);
			customerService.updateIgnoreNull(customer);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}




	@Autowired(required = true)
	public void setCustomerService(
			@Qualifier("customerService") ICustomerService customerService) {
		this.customerService = customerService;
	}



}
