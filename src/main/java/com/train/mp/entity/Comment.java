package com.train.mp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 论坛模块--帖子评论表
 * </p>
 *
 * @author Jim clark
 * @since 2019-09-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mp_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 对应的帖子id
     */
    private Long articleId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论者id
     */
    private Long creatorId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改人id
     */
    private Long modifierId;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 逻辑删除： 0：删除；1：可用
     */
    private Integer enable;


}
