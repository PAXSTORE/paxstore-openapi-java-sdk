package com.pax.market.api.sdk.java.api.emm.emmApp.dto;

import java.io.Serializable;
import java.util.List;

public class EmmAppAvailableTestVersionDTO implements Serializable {

    private static final long serialVersionUID = 8299964243945864051L;

    private List<EmmAppAvailableTestVersion> appAvailableTestVersionList;

    public List<EmmAppAvailableTestVersion> getAppAvailableTestVersionList() {
        return appAvailableTestVersionList;
    }

    public void setAppAvailableTestVersionList(List<EmmAppAvailableTestVersion> appAvailableTestVersionList) {
        this.appAvailableTestVersionList = appAvailableTestVersionList;
    }

    @Override
    public String toString() {
        return "EmmAppAvailableTestVersionDTO{" +
                "appAvailableTestVersionList=" + appAvailableTestVersionList +
                '}';
    }

    public static class EmmAppAvailableTestVersion implements Serializable {

        private static final long serialVersionUID = 6214099322206636861L;

        private Long trackId;

        private String trackAlias;

        private String versionName;

        public Long getTrackId() {
            return trackId;
        }

        public void setTrackId(Long trackId) {
            this.trackId = trackId;
        }

        public String getTrackAlias() {
            return trackAlias;
        }

        public void setTrackAlias(String trackAlias) {
            this.trackAlias = trackAlias;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        @Override
        public String toString() {
            return "EmmAppAvailableTestVersion{" +
                    "trackId=" + trackId +
                    ", trackAlias='" + trackAlias + '\'' +
                    ", versionName='" + versionName + '\'' +
                    '}';
        }
    }


}
