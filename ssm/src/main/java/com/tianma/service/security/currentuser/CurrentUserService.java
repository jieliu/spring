package com.tianma.service.security.currentuser;

import com.tianma.model.security.CurrentUser;

public interface CurrentUserService {

    boolean canAccessUser(CurrentUser currentUser, String userId);

}
