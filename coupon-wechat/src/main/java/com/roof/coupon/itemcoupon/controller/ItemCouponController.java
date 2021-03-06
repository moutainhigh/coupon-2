package com.roof.coupon.itemcoupon.controller;

import com.roof.coupon.DefaultUseableEnum;
import com.roof.coupon.accesslog.entity.AccessLog;
import com.roof.coupon.accesslog.service.api.IAccessLogService;
import com.roof.coupon.itemcoupon.entity.ItemCoupon;
import com.roof.coupon.itemcoupon.entity.ItemCouponVo;
import com.roof.coupon.itemcoupon.service.api.IItemCouponService;
import com.roof.coupon.itemcoupon.service.impl.TaobaoCommond;
import com.roof.coupon.searchconfig.entity.SearchConfig;
import com.roof.coupon.searchconfig.service.api.ISearchConfigService;
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
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author liht
 */
@Api(value = "itemcoupon", description = "商品优惠券管理")
@Controller
@RequestMapping("coupon/wechat")
public class ItemCouponController {
    private IItemCouponService itemCouponService;

    public static final String[] platforms = new String[]{"jd.com"};

    @Autowired
    private IAccessLogService accessLogService;

    @Autowired
    private TaobaoCommond taobaoCommond;

    @Autowired
    private ISearchConfigService searchConfigService;

    @ApiOperation(value = "获得本地商品优惠券分页列表")
    @RequestMapping(value = "itemcoupon/list", method = {RequestMethod.POST})
    public @ResponseBody
    Result<Page> list(ItemCouponVo itemCoupon, HttpServletRequest request) {
        Page page = PageUtils.createPage(request);
        itemCoupon.setUseable(DefaultUseableEnum.usable.getCode());
//        itemCoupon.setPlatform("jingtuitui");
        page = itemCouponService.page(page, itemCoupon);
        return new Result(Result.SUCCESS, page);
    }

    /**
     * @param type    参数为 jingtuitui 或者 taoke
     * @param name    查询的关键字
     * @param request
     * @return
     */
    @ApiOperation(value = "获得远程商品优惠券分页列表")
    @RequestMapping(value = "itemcoupon/listconnect", method = {RequestMethod.POST})
    public @ResponseBody
    Result<Page> listConnect(String type, String name, HttpServletRequest request) {
        Page page = PageUtils.createPage(request);
//        type = "jingtuitui";
        page = itemCouponService.pageConnect(page, type, name);
//        List<ItemCoupon> list = (List<ItemCoupon>) page.getDataList();
//        for (ItemCoupon entity : list
//                ) {
//            if (entity.getCouponClickUrl().indexOf("jd.com") > -1) {
//                entity.setPlatform("jingtuitui");
//            } else {
//                entity.setPlatform("taoke");
//            }
//        }
//        page.setDataList(list);
        return new Result(Result.SUCCESS, page);
    }

    @ApiOperation(value = "获得搜索配置分页列表")
    @RequestMapping(value = "searchconfig", method = {RequestMethod.GET})
    public @ResponseBody
    Result<Page> list(SearchConfig searchConfig, HttpServletRequest request) {
        Page page = PageUtils.createPage(request);
        page = searchConfigService.page(page, searchConfig);
        return new Result(Result.SUCCESS, page);
    }

    @ApiOperation(value = "根据ID加载商品优惠券")
    @RequestMapping(value = "itemcoupon/{id}/{customerId}", method = {RequestMethod.GET})
    public @ResponseBody
    Result<ItemCouponVo> load(@PathVariable Long id, @PathVariable Long customerId) {
        ItemCouponVo itemCouponVo = itemCouponService.wechatLoad(new ItemCouponVo(id, customerId));
//        if (itemCouponVo.getCouponClickUrl().indexOf("jd.com") > -1) {
//            itemCouponVo.setPlatform("jingtuitui");
//        } else {
//            itemCouponVo.setPlatform("taoke");
//        }
        return new Result(Result.SUCCESS, itemCouponVo);
    }

    @ApiOperation(value = "根据ID加载远程商品优惠券")
    @RequestMapping(value = "itemcoupon/connect/{outerId}/{customerId}", method = {RequestMethod.GET})
    public @ResponseBody
    Result<ItemCoupon> loadConnect(@PathVariable String outerId, @PathVariable Long customerId) {
        ItemCoupon itemCoupon = itemCouponService.loadConnect(new ItemCouponVo(outerId, customerId));
        return new Result(Result.SUCCESS, itemCoupon);
    }

    @ApiOperation(value = "根据ID加载优惠券的淘口令")
    @RequestMapping(value = "itemcoupon/{id}/taobao", method = {RequestMethod.GET})
    public @ResponseBody
    Result<ItemCouponVo> taobao(@PathVariable Long id) {
        ItemCoupon itemCoupon = itemCouponService.load(new ItemCoupon(id));
        try {
            String model = taobaoCommond.getCode(itemCoupon);
            String result = "复制框内整段文字，{model}，打开「手淘」即可「领取优惠券」并购买".replace("{model}", model);
            return new Result(Result.SUCCESS, "", result);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(Result.ERROR, "获取验证码错误");
        }
    }

    @ApiOperation(value = "根据outid 远程加载优惠券的淘口令")
    @RequestMapping(value = "itemcoupon/connect/{outerId}/taobao", method = {RequestMethod.GET})
    public @ResponseBody
    Result<ItemCouponVo> taobaoconnect(@PathVariable String outerId) {
        ItemCouponVo vo = new ItemCouponVo();
        vo.setOuterId(outerId);
        ItemCoupon itemCoupon = itemCouponService.loadConnect(vo);

//        ItemCoupon itemCoupon = itemCouponService.load(new ItemCoupon(id));
        try {
            String model = taobaoCommond.getCode(itemCoupon);
            String result = "复制框内整段文字，{model}，打开「手淘」即可「领取优惠券」并购买".replace("{model}", model);
            return new Result(Result.SUCCESS, "", result);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(Result.ERROR, "获取验证码错误");
        }
    }


    @Autowired(required = true)
    public void setItemCouponService(@Qualifier("itemCouponService") IItemCouponService itemCouponService) {
        this.itemCouponService = itemCouponService;
    }


}
