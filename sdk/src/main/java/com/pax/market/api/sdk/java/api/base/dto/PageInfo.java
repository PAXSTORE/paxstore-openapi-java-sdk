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

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author tanjie
 * @date 2018-07-03
 */
public class PageInfo<T extends Serializable> implements Serializable{

	private static final long serialVersionUID = 3245457190997838762L;
	private int pageNo;
	private int limit;
	private String orderBy;
	private Long totalCount;
	private boolean hasNext;
	private List<T> dataSet;
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public boolean isHasNext() {
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	public List<T> getDataSet() {
		return dataSet;
	}
	public void setDataSet(List<T> dataSet) {
		this.dataSet = dataSet;
	}
	@Override
	public String toString() {
		return "PageInfo [pageNo=" + pageNo + ", limit=" + limit + ", orderBy=" + orderBy + ", totalCount=" + totalCount
				+ ", hasNext=" + hasNext + ", dataSet=" + dataSet + "]";
	}
	
	


}
