package com.train.mp.controller;

/**
 * 基础controller
 *
 * @author Jim Clark
 * @version 1.0
 * create on  2019/9/3 0003 14:35
 */
public abstract class BaseController {


    /**
     * 获取当前用户id
     *
     * @return
     */
    public Long getCurrentUserId() {
        return 100L;
    }
}
