package com.roof.coupon.itemcoupon.controller;

import com.roof.coupon.accesslog.entity.AccessLog;
import com.roof.coupon.accesslog.service.api.IAccessLogService;
import com.roof.coupon.itemcoupon.entity.ItemCoupon;
import com.roof.coupon.itemcoupon.entity.ItemCouponVo;
import com.roof.coupon.itemcoupon.service.api.IItemCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author liht
 */
@Api(value = "itemcoupon", description = "商品优惠券管理")
@Controller
@RequestMapping("coupon/wechat")
public class ItemCouponController {
    private IItemCouponService itemCouponService;

    @Autowired
    private IAccessLogService accessLogService;

    @ApiOperation(value = "获得商品优惠券分页列表")
    @RequestMapping(value = "itemcoupon/list", method = {RequestMethod.GET})
    public @ResponseBody
    Result<Page> list(ItemCoupon itemCoupon, HttpServletRequest request) {
        Page page = PageUtils.createPage(request);
        page = itemCouponService.page(page, itemCoupon);
        return new Result(Result.SUCCESS, page);
    }


    @ApiOperation(value = "根据ID加载商品优惠券")
    @RequestMapping(value = "itemcoupon/{id}/{customerId}", method = {RequestMethod.GET})
    public @ResponseBody
    Result<ItemCouponVo> load(@PathVariable Long id, @PathVariable Long customerId) {
        ItemCouponVo itemCouponVo = itemCouponService.wechatLoad(new ItemCouponVo(id, customerId));
        return new Result(Result.SUCCESS, itemCouponVo);
    }


    @Autowired(required = true)
    public void setItemCouponService(
            @Qualifier("itemCouponService") IItemCouponService itemCouponService
    ) {
        this.itemCouponService = itemCouponService;
    }


}
