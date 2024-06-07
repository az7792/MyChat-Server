package org.mychat.mychatserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("contact")
public class Contact {


    public int getUid1() {
        return uid1;
    }

    public void setUid1(int uid1) {
        this.uid1 = uid1;
    }

    public int getUid2() {
        return uid2;
    }

    public void setUid2(int uid2) {
        this.uid2 = uid2;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    private int uid1;
    private int uid2;

    private int status;



    @Override
    public String toString() {
        return "Group{" +
                ", Uid1='" + uid1 + '\'' +
                ", Uid2='" + uid2 + '\'' +
                '}';
    }
}
