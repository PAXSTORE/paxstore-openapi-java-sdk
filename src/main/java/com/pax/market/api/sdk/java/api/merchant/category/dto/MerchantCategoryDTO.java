package com.pax.market.api.sdk.java.api.merchant.category.dto;

import java.io.Serializable;

public class MerchantCategoryDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 660988358887400629L;
	
	private Long id;
    private String name;
    private String remarks;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "MerchantCategoryDTO [id=" + id + ", name=" + name + ", remarks=" + remarks + "]";
	}
	
	

}
