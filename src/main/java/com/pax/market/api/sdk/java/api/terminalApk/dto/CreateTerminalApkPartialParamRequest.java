package com.pax.market.api.sdk.java.api.terminalApk.dto;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * The type Create terminal apk request.
 */
public class CreateTerminalApkPartialParamRequest extends CreateTerminalApkRequest {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String partialPid;

	/**
     * Gets partial pid
	 * @return
	 */
	public String getPartialPid() {
		return partialPid;
	}

	/**
     * Sets partial pid
	 * @param partialPid
	 */
	public void setPartialPid(String partialPid) {
		this.partialPid = partialPid;
	}
}
