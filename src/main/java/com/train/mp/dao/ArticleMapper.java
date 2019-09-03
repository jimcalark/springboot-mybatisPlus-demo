package com.train.mp.dao;

import com.train.mp.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 论坛模块---帖子表 Mapper 接口
 * </p>
 *
 * @author Jim clark
 * @since 2019-09-03
 */
@Mapper
@Repository
public interface ArticleMapper extends BaseMapper<Article> {

}
