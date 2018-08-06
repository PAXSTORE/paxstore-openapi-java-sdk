/*
 * *******************************************************************************
 * COPYRIGHT
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *
 *      Copyright (C) 2017 PAX Technology, Inc. All rights reserved.
 * *******************************************************************************
 */
package com.pax.market.api.sdk.java.api.base.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.pax.market.api.sdk.java.api.constant.Constants;

/**
 *
 * @author tanjie
 * @date 2018-07-02
 */
public class PageRequestDTO{


	@Min(1)
    private int pageNo = 1;
	
	@Min(1)
	@Max(1000)
    private int pageSize = Constants.DEFAULT_PAGE_SIZE;
    private String orderBy;

	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
