package com.train.mp.service.impl;

import com.train.mp.entity.Comment;
import com.train.mp.mapper.CommentMapper;
import com.train.mp.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 论坛模块--帖子评论表 服务实现类
 * </p>
 *
 * @author Jim clark
 * @since 2019-09-02
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
