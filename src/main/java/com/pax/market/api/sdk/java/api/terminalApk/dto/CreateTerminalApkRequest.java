package com.pax.market.api.sdk.java.api.terminalApk.dto;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class CreateTerminalApkRequest implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 919091318157077556L;

	private String tid;
	
	private String serialNo;

	@NotBlank
	private String packageName;
	
	private String version;
	
	private String templateName;
	
	private Map<String, String> parameters;
	private List<FileParameter> base64FileParameters;

	private String pushTemplateName;



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
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public Map<String, String> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

    public List<FileParameter> getBase64FileParameters() {
        return base64FileParameters;
    }

    public void setBase64FileParameters(List<FileParameter> base64FileParameters) {
        this.base64FileParameters = base64FileParameters;
    }

	public String getPushTemplateName() {
		return pushTemplateName;
	}

	public void setPushTemplateName(String pushTemplateName) {
		this.pushTemplateName = pushTemplateName;
	}
}
