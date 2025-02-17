package com.pax.market.api.sdk.java.api.factoryModel.dto;

import java.io.Serializable;

/**
 * @author shifan
 * @date 2025/2/17
 */
public class ModelDTO implements Serializable {

    private static final long serialVersionUID = 1177599828025278192L;
    private Long id;
    private String name;
    private String productTypeLabel;
    private String parentProductTypeLabel;
    private String productType;
    private String parentProductType;


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

    public String getProductTypeLabel() {
        return productTypeLabel;
    }

    public void setProductTypeLabel(String productTypeLabel) {
        this.productTypeLabel = productTypeLabel;
    }

    public String getParentProductTypeLabel() {
        return parentProductTypeLabel;
    }

    public void setParentProductTypeLabel(String parentProductTypeLabel) {
        this.parentProductTypeLabel = parentProductTypeLabel;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getParentProductType() {
        return parentProductType;
    }

    public void setParentProductType(String parentProductType) {
        this.parentProductType = parentProductType;
    }

    @Override
    public String toString() {
        return "ModelDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productTypeLabel='" + productTypeLabel + '\'' +
                ", parentProductTypeLabel='" + parentProductTypeLabel + '\'' +
                ", productType='" + productType + '\'' +
                ", parentProductType='" + parentProductType + '\'' +
                '}';
    }
}
