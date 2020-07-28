/*
 *
 *  * ===========================================================================================
 *  * = COPYRIGHT
 *  *          PAX Computer Technology(Shenzhen) CO., LTD PROPRIETARY INFORMATION
 *  *   This software is supplied under the terms of a license agreement or nondisclosure
 *  *   agreement with PAX Computer Technology(Shenzhen) CO., LTD and may not be copied or
 *  *   disclosed except in accordance with the terms in that agreement.
 *  *     Copyright (C) 2019. PAX Computer Technology(Shenzhen) CO., LTD All rights reserved.
 *  * ===========================================================================================
 *
 */

package com.pax.market.api.sdk.java.api.app.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author liukai
 * @date 2019/5/23
 */
public class AppPageDTO implements Serializable {

    private static final long serialVersionUID = 6168881422955634546L;

    private Long id;
    private String name;                // 应用名字
    private String packageName;         // 应用包名,唯一
    private String status;              // 应用状态
    private String osType;              // 操作系统
    private Boolean specificReseller;   // 是否定向发布至代理商
    private Integer chargeType;
    private BigDecimal price;
    private Long downloads;             // 下载次数
    private List<ApkDTO> apkList;

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

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public Boolean getSpecificReseller() {
        return specificReseller;
    }

    public void setSpecificReseller(Boolean specificReseller) {
        this.specificReseller = specificReseller;
    }

    public Integer getChargeType() {
        return chargeType;
    }

    public void setChargeType(Integer chargeType) {
        this.chargeType = chargeType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getDownloads() {
        return downloads;
    }

    public void setDownloads(Long downloads) {
        this.downloads = downloads;
    }

    public List<ApkDTO> getApkList() {
        return apkList;
    }

    public void setApkList(List<ApkDTO> apkList) {
        this.apkList = apkList;
    }

    @Override
    public String toString() {
        return "AppPageDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                ", status='" + status + '\'' +
                ", osType='" + osType + '\'' +
                ", specificReseller=" + specificReseller +
                ", chargeType=" + chargeType +
                ", price=" + price +
                ", downloads=" + downloads +
                ", apkList=" + apkList +
                '}';
    }
}
