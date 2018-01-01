package com.esioner.neihanreader.bean.neiHanBean;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class NeiHanDataBean {
    /**
     * 神评
     */
    private List<NeiHanCommentsBean> comments;
    @SerializedName("group")
    private NeiHanGroupData groupData;
    private int type;
    @SerializedName("display_time")
    private float displayTime;
    @SerializedName("online_time")
    private float onlineTime;

    public List<NeiHanCommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<NeiHanCommentsBean> comments) {
        this.comments = comments;
    }

    public NeiHanGroupData getGroupData() {
        return groupData;
    }

    public void setGroupData(NeiHanGroupData groupData) {
        this.groupData = groupData;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getDisplayTime() {
        return displayTime;
    }

    public void setDisplayTime(float displayTime) {
        this.displayTime = displayTime;
    }

    public float getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(float onlineTime) {
        this.onlineTime = onlineTime;
    }
}
