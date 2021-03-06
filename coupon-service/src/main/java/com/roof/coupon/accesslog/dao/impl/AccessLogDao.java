package com.roof.coupon.accesslog.dao.impl;

import java.util.Comparator;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.roof.dataaccess.PageQuery;
import org.roof.roof.dataaccess.api.AbstractDao;
import org.roof.roof.dataaccess.api.IDaoSupport;
import org.roof.roof.dataaccess.api.IPageQuery;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.roof.coupon.accesslog.dao.api.IAccessLogDao;
import com.roof.coupon.accesslog.entity.AccessLog;

@Service
public class AccessLogDao extends AbstractDao implements IAccessLogDao {

    private PageQueryFactory<PageQuery> pageQueryFactory;

    @Override
    public Page page(Page page, AccessLog accessLog) {
        IPageQuery pageQuery = pageQueryFactory.getPageQuery(page, "selectAccessLogPage", "selectAccessLogCount");
        //IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectAccessLogPage", null);
        return pageQuery.select(accessLog);
    }

    @Override
    public Page pageGroupby(Page page, AccessLog accessLog) {
        IPageQuery pageQuery = pageQueryFactory.getPageQuery(page, "selectAccessLogGroupbyPage", "selectAccessLogGroupbyCount");
        //IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectAccessLogPage", null);
        return pageQuery.select(accessLog);
    }

    @Autowired
    public void setPageQueryFactory(
            @Qualifier("pageQueryFactory") PageQueryFactory<PageQuery> pageQueryFactory
    ) {
        this.pageQueryFactory = pageQueryFactory;
    }

    @Autowired
    public void setDaoSupport(
            @Qualifier("roofDaoSupport") IDaoSupport daoSupport
    ) {
        this.daoSupport = daoSupport;
    }

}