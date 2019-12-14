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
package com.pax.market.api.sdk.java.api.terminal.dto;

import java.io.Serializable;
import java.util.List;


/**
 *
 * @author tanjie
 * @date 2018-07-02
 */
public class TerminalDTO implements Serializable{

	private static final long serialVersionUID = 7445602838773666039L;

	private Long id;
    
    private String name;
    
    private String tid;
    
    private String serialNo;
    
    private String status;
    
    private String merchantName;
    
    private String modelName;
    
    private String resellerName;
    
    private String location;

	private TerminalLocationDTO geoLocation;

	private TerminalInstalledFirmwareDTO installedFirmware;

	private List<TerminalInstalledApkDTO> installedApks;

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

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getResellerName() {
		return resellerName;
	}

	public void setResellerName(String resellerName) {
		this.resellerName = resellerName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public TerminalLocationDTO getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(TerminalLocationDTO geoLocation) {
		this.geoLocation = geoLocation;
	}

	public TerminalInstalledFirmwareDTO getInstalledFirmware() {
		return installedFirmware;
	}

	public void setInstalledFirmware(TerminalInstalledFirmwareDTO installedFirmware) {
		this.installedFirmware = installedFirmware;
	}

	public List<TerminalInstalledApkDTO> getInstalledApks() {
		return installedApks;
	}

	public void setInstalledApks(List<TerminalInstalledApkDTO> installedApks) {
		this.installedApks = installedApks;
	}

	@Override
	public String toString() {
		return "TerminalDTO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", tid='" + tid + '\'' +
				", serialNo='" + serialNo + '\'' +
				", status='" + status + '\'' +
				", merchantName='" + merchantName + '\'' +
				", modelName='" + modelName + '\'' +
				", resellerName='" + resellerName + '\'' +
				", location='" + location + '\'' +
				", geoLocation=" + geoLocation +
				", installedFirmware=" + installedFirmware +
				", installedApks=" + installedApks +
				'}';
	}
}
