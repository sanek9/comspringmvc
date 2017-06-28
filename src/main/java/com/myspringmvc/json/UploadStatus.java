package com.myspringmvc.json;

/**
 * Created by sanek9 on 28.06.17.
 */
public class UploadStatus {
    private String status;
    private String sourceId;

    public UploadStatus(String status, String sourceId) {
        this.status = status;
        this.sourceId = sourceId;
    }

    public UploadStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
