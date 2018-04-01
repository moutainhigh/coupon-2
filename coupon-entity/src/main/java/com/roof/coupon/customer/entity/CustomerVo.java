package com.roof.coupon.customer.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： c_customer <br/>
 *         描述：客户 <br/>
 */
public class CustomerVo extends Customer {

	private List<CustomerVo> customerList;

	public CustomerVo() {
		super();
	}

	public CustomerVo(Long id) {
		super();
		this.id = id;
	}

	public List<CustomerVo> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<CustomerVo> customerList) {
		this.customerList = customerList;
	}

}
