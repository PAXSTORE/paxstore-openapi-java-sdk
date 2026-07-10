package com.pax.market.api.sdk.java.api.app.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author shifan
 * @date 2026/4/13
 */
public class ApkModelDTO implements Serializable {
    private static final long serialVersionUID = -6118272838437007960L;
    private List<ModelDTO> modelList;

    public List<ModelDTO> getModelList() {
        return modelList;
    }

    public void setModelList(List<ModelDTO> modelList) {
        this.modelList = modelList;
    }

    public static class FactoryDTO implements Serializable {
        private static final long serialVersionUID = 1L;
        private Long id;

        private String name;

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

        @Override
        public String toString() {
            return "FactoryDTO{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static class ModelDTO implements Serializable {

        private static final long serialVersionUID = 1L;
        private Long id;
        private String name;

        private FactoryDTO factory;

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

        public FactoryDTO getFactory() {
            return factory;
        }

        public void setFactory(FactoryDTO factory) {
            this.factory = factory;
        }

        @Override
        public String toString() {
            return "ModelDTO{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", factory=" + factory +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ApkModelDTO{" +
                "modelList=" + modelList +
                '}';
    }
}
