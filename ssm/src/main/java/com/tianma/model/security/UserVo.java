package com.tianma.model.security;

import com.tianma.support.Role;

/**
 * Created by zuowenxia on 2017/4/25.
 */
public class UserVo {

    private String userId;
    private String password;
    private Role role;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
