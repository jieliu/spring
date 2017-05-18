package com.tianma.model.security;

import com.tianma.support.Role;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * 当前登录用户信息
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private UserVo userVo;

    public CurrentUser(UserVo userInfoVo) {
        super(userInfoVo.getUserId(), userInfoVo.getPassword(), AuthorityUtils.createAuthorityList(userInfoVo.getRole().toString()));
        this.userVo = userInfoVo;
    }

    public UserVo getUser() {
        return userVo;
    }

    public String getUserId() {
        return userVo.getUserId();
    }

    public Role getRole() {
        return userVo.getRole();
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "user=" + userVo +
                '}';
    }
}
