package com.train.mp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.train.mp.entity.User;
import com.train.mp.service.IUserService;
import com.train.mp.support.ApiResult;
import com.train.mp.support.ValidateParam;
import com.train.mp.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author Jim clark
 * @since 2019-09-03
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    /**
     * 新增或修改用户
     *
     * @param userVo        vo
     * @param bindingResult bindingResult
     * @return
     */
    @PostMapping("save")
    public ApiResult saveUser(@RequestBody @Valid UserVo userVo, BindingResult bindingResult) {
        ValidateParam.validateBindingResult(bindingResult);
        userVo.convertToEntity().insert();
        return ApiResult.success();
    }

    /**
     * 根据用户姓名获取用户所有信息
     *
     * @param name 用户姓名
     * @return
     */
    @GetMapping("getByName")
    public ApiResult getByName(@RequestParam String name) {
        User result = userService.getOne(Wrappers.<User>lambdaQuery().likeRight(User::getName, name));
        return  ApiResult.successResult(result);
    }
}
