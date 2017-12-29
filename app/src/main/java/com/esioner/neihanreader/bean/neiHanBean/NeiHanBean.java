package com.esioner.neihanreader.bean.neiHanBean;


import com.google.gson.annotations.SerializedName;

public class NeiHanBean {

    /**
     * 成功时为 success
     */
    private String message;
    @SerializedName("data")
    private NeiHanRootData neiHanRootData;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NeiHanRootData getNeiHanRootData() {
        return neiHanRootData;
    }

    public void setNeiHanRootData(NeiHanRootData neiHanRootData) {
        this.neiHanRootData = neiHanRootData;
    }

    @Override
    public String toString() {
        return "NeiHanBean{" +
                "message='" + message + '\'' +
                ", neiHanRootData=" + neiHanRootData +
                '}';
    }
}