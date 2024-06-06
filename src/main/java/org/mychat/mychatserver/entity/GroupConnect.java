package org.mychat.mychatserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("groupconnect")
public class GroupConnect {
    public int getGroupconnectid() {
        return groupconnectid;
    }

    public void setGroupconnectid(int groupconnectid) {
        this.groupconnectid = groupconnectid;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    private int groupconnectid;
    private int groupid;
    @TableId(type= IdType.AUTO)
    private int uid;

    @Override
    public String toString() {
        return "Group{" +
                "GroupConnectId=" + groupconnectid +
                "GroupId=" + groupid +
                ", UserId='" + uid + '\'' +
                '}';
    }

}
