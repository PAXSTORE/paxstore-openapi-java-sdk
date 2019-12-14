package com.pax.market.api.sdk.java.api.terminalApk.dto;

import java.io.Serializable;

/**
 * @author liukai
 * @date 2019/10/22
 */
public class FileParameter implements Serializable {

    private static final long serialVersionUID = 2749595791799657319L;
    private String pid;
    private String fileName;
    private String fileData;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileData() {
        return fileData;
    }

    public void setFileData(String fileData) {
        this.fileData = fileData;
    }
}
