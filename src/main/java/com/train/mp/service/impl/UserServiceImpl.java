package com.train.mp.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.train.mp.dao.UserMapper;
import com.train.mp.entity.User;
import com.train.mp.service.IUserService;
import com.train.mp.vo.UserVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jim clark
 * @since 2019-09-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Override
    public IPage<UserVo> userVoPage(IPage<UserVo> page, Wrapper<User> wrapper) {
        return baseMapper.userVoPage(page, wrapper);
    }


}
