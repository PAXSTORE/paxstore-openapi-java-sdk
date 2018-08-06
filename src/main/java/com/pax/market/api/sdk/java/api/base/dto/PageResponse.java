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
 * @date 2018-07-02
 */
public class PageResponse<T extends Serializable> extends BaseDTO{
	private static final long serialVersionUID = -2620822481165409572L;
	private int pageNo = 1;
    private int limit;
    private String orderBy;
    
    private Long totalCount;
    private List<T> dataset;
    private boolean hasNext;
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
	public List<T> getDataset() {
		return dataset;
	}
	public void setDataset(List<T> dataset) {
		this.dataset = dataset;
	}
	public boolean isHasNext() {
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	@Override
	public String toString() {
		return "PageResponse [pageNo=" + pageNo + ", limit=" + limit + ", orderBy=" + orderBy + ", totalCount="
				+ totalCount + ", dataset=" + dataset + ", hasNext=" + hasNext + "]";
	}
	
}
