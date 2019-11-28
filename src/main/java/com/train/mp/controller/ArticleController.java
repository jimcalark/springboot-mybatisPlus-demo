package com.train.mp.controller;


import com.train.mp.support.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.train.mp.controller.BaseController;

/**
 * <p>
 * 论坛模块---帖子表 前端控制器
 * </p>
 *
 * @author Jim clark
 * @since 2019-09-03
 */
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController {



    @GetMapping("test")
    public ApiResult aopTest(){
        return  ApiResult.successResult("测试AOP");
    }
}
