package com.tianma.service.security.currentuser;

import com.tianma.model.security.CurrentUser;
import com.tianma.support.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserDetailsService.class);

    public boolean canAccessUser(CurrentUser currentUser, String userId) {
        LOGGER.debug("Checking if login={} has access to login={}", currentUser, userId);
        return currentUser != null
                && (currentUser.getRole() == Role.ADMIN || currentUser.getUserId().equals(userId));
    }

}
