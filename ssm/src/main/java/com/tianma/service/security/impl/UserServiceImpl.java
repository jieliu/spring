package com.tianma.service.security.impl;

import com.tianma.model.security.UserDo;
import com.tianma.model.security.UserVo;
import com.tianma.repository.UserMapper;
import com.tianma.service.security.UserService;
import com.tianma.support.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.stereotype.Service;

/**
 * Created by fiboliu on 16-11-1.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    public UserVo getUserById(String userId) {
        UserDo userDo = userMapper.selectUserById(userId);
        if(userDo != null) {
            UserVo userVo = new UserVo();
            userVo.setUserId(userId);
            userVo.setPassword(userDo.getPassword());
            userVo.setRole(Role.USER);
            return userVo;
        }
        return null;
    }
}
