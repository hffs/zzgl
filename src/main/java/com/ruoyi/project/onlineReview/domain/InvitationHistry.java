package com.ruoyi.project.onlineReview.domain;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 关于资质在线评审的日志基类
 */
public class InvitationHistry implements Serializable {

    private long id,//自增id
            invitationId;//邀约申请id
    private String record;//日志详细内容
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;//日志录入时间

    private long fileId;
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setInvitationId(long invitationId) {
        this.invitationId = invitationId;
    }

    public void setRecord(String record) {
        this.record = record;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public long getFileId() {
        return fileId;
    }

    public long getId() {
        return id;
    }

    public long getInvitationId() {
        return invitationId;
    }

    public String getRecord() {
        return record;
    }

    public static JSONArray getHistry(List<InvitationHistry> list){
        JSONArray array = new JSONArray();
        for (InvitationHistry invitationHistry:list
        ) {
            JSONObject jsonOb = new JSONObject();
            jsonOb.put("content",invitationHistry.getRecord());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            jsonOb.put("timestamp",sdf.format(invitationHistry.getCreateTime()));
            array.add(jsonOb);
        }
        return array;
    }

    public static JSONObject getHistry(InvitationHistry invitationHistry){
            JSONObject jsonOb = new JSONObject();
            jsonOb.put("content",invitationHistry.getRecord());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            jsonOb.put("timestamp",sdf.format(invitationHistry.getCreateTime()));
        return jsonOb;
    }
}
