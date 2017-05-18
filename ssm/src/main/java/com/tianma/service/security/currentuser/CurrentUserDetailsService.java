package com.tianma.service.security.currentuser;

import com.tianma.model.security.CurrentUser;
import com.tianma.model.security.UserVo;
import com.tianma.service.security.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CurrentUserDetailsService.class);

    private final UserService userService;

    @Autowired
    public CurrentUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    public CurrentUser loadUserByUsername(String userId) throws UsernameNotFoundException {

        logger.debug("Authenticating login with userId={}", userId.replaceFirst("@.*", "@***"));

        UserVo user = userService.getUserById(userId);
        if(user == null) {
            return null;
        }
        return new CurrentUser(user);
    }

}
