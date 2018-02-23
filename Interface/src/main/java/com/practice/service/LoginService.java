package com.practice.service;

import com.practice.result.JsonResult;

/**
 * Login service
 * @author Xushd  2017/12/21 22:26
 */
public interface LoginService {

    /**
     * Manage Login Check
     * @param account
     * @param password
     * @return
     */
    JsonResult manageLoginCheck(String account,String password);

    /**
     * Get Parent Token
     * @param id
     * @return
     */
    JsonResult getParentToken(Long id);

    /**
     * Update parent token
     * @param token
     * @return
     */
    JsonResult updateToken(String token);
}
