package com.esioner.neihanreader.bean.ActionBean;

import com.google.gson.annotations.SerializedName;

/**
 * @author Esioner
 * @date 2018/1/2
 */

public class DiggBean {
    /**
     * {
     * "action_exist": "digg",
     * "bury_count": 139,
     * "comment_count": 87,
     * "digg_count": 4097,
     * "group_id": 82370369881,
     * "message": "success",
     * "repin_count": 53,
     * "tag": "",
     * "tag_id": 0
     * }
     */

    @SerializedName("action_exist")
    private String actionExist;
    @SerializedName("bury_count")
    private int buryCount;
    @SerializedName("digg_count")
    private int diggCount;
    @SerializedName("comment_count")
    private int commentCount;
    @SerializedName("group_id")
    private int groupId;
    @SerializedName("message")
    private String message;
    @SerializedName("repin_count")
    private int repinCount;
    @SerializedName("tag")
    private String tag;
    @SerializedName("tag_id")
    private int tagId;

    public String getActionExist() {
        return actionExist;
    }

    public void setActionExist(String actionExist) {
        this.actionExist = actionExist;
    }

    public int getBuryCount() {
        return buryCount;
    }

    public void setBuryCount(int buryCount) {
        this.buryCount = buryCount;
    }

    public int getDiggCount() {
        return diggCount;
    }

    public void setDiggCount(int diggCount) {
        this.diggCount = diggCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRepinCount() {
        return repinCount;
    }

    public void setRepinCount(int repinCount) {
        this.repinCount = repinCount;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
}
