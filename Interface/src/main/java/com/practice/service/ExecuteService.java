package com.practice.service;

/**
 * @author Xushd  2018/1/25 20:46
 */
public interface ExecuteService {

    /**
     * Polling activity begin
     */
    void pollingActivityBegin();
    /**
     * Polling actvity end
     */
    void pollingActivityEnd();
    /**
     * Polling activity close
     */
    void pollingActivityEnrollClose();
}
