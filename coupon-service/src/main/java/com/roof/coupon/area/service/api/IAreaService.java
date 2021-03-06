package com.roof.coupon.area.service.api;


import com.roof.coupon.area.entity.Area;
import com.roof.coupon.area.entity.AreaTreeSelect;
import com.roof.coupon.area.entity.AreaVo;
import org.roof.roof.dataaccess.api.Page;

import java.io.Serializable;
import java.util.List;

public interface IAreaService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Area area);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Area area);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(Area area);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Area area);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(Area area);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(Area area);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract AreaVo load(Area area);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract AreaVo selectForObject(Area area);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<AreaVo> selectForList(Area area);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Area area);

	List<AreaTreeSelect> tree(Area area);
}