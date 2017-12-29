package com.esioner.neihanreader.bean.neiHanBean;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class NeiHanRootData {
    /**
     * 是否有更多
     */
    @SerializedName("has_more")
    private boolean hasMore;
    /**
     * 更新提示
     */
    @SerializedName("tip")
    private String refreshTip;
    /**
     * 是否有新的数据
     */
    @SerializedName("has_new_message")
    private boolean hasNewMessage;

    @SerializedName("max_time")
    private double maxTime;
    @SerializedName("min_time")
    private double minTime;
    /**
     * 具体内容
     */
    @SerializedName("data")
    private List<NeiHanDataBean> detailData;


    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public String getRefreshTip() {
        return refreshTip;
    }

    public void setRefreshTip(String refreshTip) {
        this.refreshTip = refreshTip;
    }

    public boolean isHasNewMessage() {
        return hasNewMessage;
    }

    public void setHasNewMessage(boolean hasNewMessage) {
        this.hasNewMessage = hasNewMessage;
    }

    public double getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(double maxTime) {
        this.maxTime = maxTime;
    }

    public double getMinTime() {
        return minTime;
    }

    public void setMinTime(double minTime) {
        this.minTime = minTime;
    }

    public List<NeiHanDataBean> getDetailData() {
        return detailData;
    }

    public void setDetailData(List<NeiHanDataBean> detailData) {
        this.detailData = detailData;
    }

    @Override
    public String toString() {
        return "NeiHanRootData{" +
                "hasMore=" + hasMore +
                ", refreshTip='" + refreshTip + '\'' +
                ", hasNewMessage=" + hasNewMessage +
                ", maxTime=" + maxTime +
                ", minTime=" + minTime +
                ", detailData=" + detailData +
                '}';
    }
}
