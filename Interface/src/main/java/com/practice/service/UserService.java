package com.practice.service;

import com.practice.result.JsonResult;

/**
 * User service
 * @author Xushd on 2017/12/22 16:24
 */
public interface UserService {

    /**
     * List  user nav
     * @param token
     * @return
     */
    JsonResult listUserNav(String token);
}
