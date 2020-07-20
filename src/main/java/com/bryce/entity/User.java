package com.bryce.entity;

import com.bryce.constant.Gender;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName User
 * @Description user
 * @Author Bryce
 * @Date 2020-07-20 10:37
 */
@Data
@Accessors(chain = true)
public class User extends BaseEntity{
    private String username;
    private String password;
    private Gender gender;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", id='" + getId() + '\'' +
                ", updateTime='" + getUpdateTime() + '\'' +
                ", createTime='" + getCreateTime() + '\'' +
                ", ver='" + getVersion() + '\'' +
                ", deleted='" + getDeleted() + '\'' +
                '}';
    }
}
