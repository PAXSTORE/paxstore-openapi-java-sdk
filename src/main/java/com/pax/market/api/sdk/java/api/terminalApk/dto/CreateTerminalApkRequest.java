package com.pax.market.api.sdk.java.api.terminalApk.dto;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The type Create terminal apk request.
 */
public class CreateTerminalApkRequest implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tid;
	
	private String serialNo;

	@NotBlank
	private String packageName;
	
	private String version;
	
	private String templateName;
	
	private Map<String, String> parameters;
	private List<FileParameter> base64FileParameters;

	private String pushTemplateName;
	private Boolean inheritPushHistory;
	private boolean forceUpdate;
	private boolean wifiOnly;
	private Date effectiveTime;
	private Date expiredTime;


	/**
	 * Gets tid.
	 *
	 * @return the tid
	 */
	public String getTid() {
		return tid;
	}

	/**
	 * Sets tid.
	 *
	 * @param tid the tid
	 */
	public void setTid(String tid) {
		this.tid = tid;
	}

	/**
	 * Gets serial no.
	 *
	 * @return the serial no
	 */
	public String getSerialNo() {
		return serialNo;
	}

	/**
	 * Sets serial no.
	 *
	 * @param serialNo the serial no
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * Gets package name.
	 *
	 * @return the package name
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * Sets package name.
	 *
	 * @param packageName the package name
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * Gets version.
	 *
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Sets version.
	 *
	 * @param version the version
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * Gets template name.
	 *
	 * @return the template name
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * Sets template name.
	 *
	 * @param templateName the template name
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	/**
	 * Gets parameters.
	 *
	 * @return the parameters
	 */
	public Map<String, String> getParameters() {
		return parameters;
	}

	/**
	 * Sets parameters.
	 *
	 * @param parameters the parameters
	 */
	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	/**
	 * Gets base 64 file parameters.
	 *
	 * @return the base 64 file parameters
	 */
	public List<FileParameter> getBase64FileParameters() {
        return base64FileParameters;
    }

	/**
	 * Sets base 64 file parameters.
	 *
	 * @param base64FileParameters the base 64 file parameters
	 */
	public void setBase64FileParameters(List<FileParameter> base64FileParameters) {
        this.base64FileParameters = base64FileParameters;
    }

	/**
	 * Gets push template name.
	 *
	 * @return the push template name
	 */
	public String getPushTemplateName() {
		return pushTemplateName;
	}

	/**
	 * Sets push template name.
	 *
	 * @param pushTemplateName the push template name
	 */
	public void setPushTemplateName(String pushTemplateName) {
		this.pushTemplateName = pushTemplateName;
	}

	/**
	 * Gets inherit push history.
	 *
	 * @return the inherit push history
	 */
	public Boolean getInheritPushHistory() {
		return inheritPushHistory;
	}

	/**
	 * Sets inherit push history.
	 *
	 * @param inheritPushHistory the inherit push history
	 */
	public void setInheritPushHistory(Boolean inheritPushHistory) {
		this.inheritPushHistory = inheritPushHistory;
	}

	/**
	 * Whether to force the app to update
	 *
	 * @return the boolean
	 */
	public boolean isForceUpdate() {
		return forceUpdate;
	}

	/**
	 * Sets force update.
	 *
	 * @param forceUpdate the force update
	 */
	public void setForceUpdate(boolean forceUpdate) {
		this.forceUpdate = forceUpdate;
	}

	/**
	 * Whether to download over Wi-Fi or Cable network only, don’t allow to download over the cellular network
	 *
	 * @return the boolean
	 */
	public boolean isWifiOnly() {
		return wifiOnly;
	}

	/**
	 * Sets wifi only.
	 *
	 * @param wifiOnly the wifi only
	 */
	public void setWifiOnly(boolean wifiOnly) {
		this.wifiOnly = wifiOnly;
	}

	/**
	 * Gets effective time.
	 *
	 * @return the effective time
	 */
	public Date getEffectiveTime() {
		return effectiveTime;
	}

	/**
	 * Sets effective time.
	 *
	 * @param effectiveTime the effective time
	 */
	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	/**
	 * Gets expired time.
	 *
	 * @return the expired time
	 */
	public Date getExpiredTime() {
		return expiredTime;
	}

	/**
	 * Sets expired time.
	 *
	 * @param expiredTime the expired time
	 */
	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}
}
