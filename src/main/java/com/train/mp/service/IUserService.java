package com.train.mp.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.train.mp.entity.User;
import com.train.mp.vo.UserVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Jim clark
 * @since 2019-09-03
 */
public interface IUserService extends IService<User> {


    /**
     * 自定义分页
     *
     * @param page    page
     * @param wrapper wrapper
     * @return
     */
    IPage<UserVo> userVoPage(IPage<UserVo> page, Wrapper<User> wrapper);
}
