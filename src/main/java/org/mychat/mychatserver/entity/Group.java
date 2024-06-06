package org.mychat.mychatserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("group")
public class Group {
    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public Integer getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(Integer ownerid) {
        this.ownerid = ownerid;
    }
    @TableId(type= IdType.AUTO)
    private int groupid;
    private String groupname;

    private Integer ownerid;



    @Override
    public String toString() {
        return "Group{" +
                "GroupId=" + groupid +
                ", username='" + groupname + '\'' +
                ", ownerid='" + ownerid + '\'' +
                '}';
    }
}
