package com.pax.market.api.sdk.java.api.factoryModel.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author shifan
 * @date 2025/2/17
 */
public class FactoryDTO implements Serializable {

    private static final long serialVersionUID = -7343153718255771429L;
    private Long id;
    private String name;
    private List<ModelDTO> modelList;

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

    public List<ModelDTO> getModelList() {
        return modelList;
    }

    public void setModelList(List<ModelDTO> modelList) {
        this.modelList = modelList;
    }

    @Override
    public String toString() {
        return "FactoryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", modelList=" + modelList +
                '}';
    }
}
