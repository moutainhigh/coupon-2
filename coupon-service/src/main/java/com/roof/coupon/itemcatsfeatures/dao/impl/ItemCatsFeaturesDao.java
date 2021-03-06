package com.roof.coupon.itemcatsfeatures.dao.impl;

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

import com.roof.coupon.itemcatsfeatures.dao.api.IItemCatsFeaturesDao;
import com.roof.coupon.itemcatsfeatures.entity.ItemCatsFeatures;
@Service
public class ItemCatsFeaturesDao extends AbstractDao implements IItemCatsFeaturesDao {
	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, ItemCatsFeatures itemCatsFeatures) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectItemCatsFeaturesPage", "selectItemCatsFeaturesCount");
		//IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectItemCatsFeaturesPage", null);
		return pageQuery.select(itemCatsFeatures);
	}
	
	@Autowired
	public void setPageQueryFactory(
			@Qualifier("pageQueryFactory") PageQueryFactory<PageQuery> pageQueryFactory) {
		this.pageQueryFactory = pageQueryFactory;
	}
	
	@Autowired
	public void setDaoSupport(
			@Qualifier("roofDaoSupport") IDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}

}