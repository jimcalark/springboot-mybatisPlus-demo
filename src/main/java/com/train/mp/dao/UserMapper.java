package com.train.mp.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.train.mp.entity.User;
import com.train.mp.vo.UserVo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Jim clark
 * @since 2019-09-03
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 自定义分页
     *
     * @param page    page
     * @param wrapper wrapper
     * @return
     */
    IPage<UserVo> userVoPage(IPage<UserVo> page, @Param(Constants.WRAPPER) Wrapper<User> wrapper);
}
