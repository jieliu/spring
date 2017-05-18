package com.tianma.service.security;

import com.tianma.model.security.UserVo;

/**
 * Created by fiboliu on 16-11-1.
 */
public interface UserService {
    UserVo getUserById(String userId);
}
