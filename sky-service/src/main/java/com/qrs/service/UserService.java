package com.qrs.service;

import com.qrs.dto.UserLoginDTO;
import com.qrs.entity.User;

public interface UserService {
    User wxLogin(UserLoginDTO userLoginDTO);
}
