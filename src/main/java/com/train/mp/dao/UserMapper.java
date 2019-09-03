package com.train.mp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.train.mp.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jim clark
 * @since 2019-09-03
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
