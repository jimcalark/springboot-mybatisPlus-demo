package com.train.mp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 论坛模块--帖子评论回复表
 * </p>
 *
 * @author Jim clark
 * @since 2019-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mp_replay")
public class Replay extends Model<Replay> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 评论id
     */
    private Long commentId;

    /**
     * 回复内容
     */
    private String content;

    /**
     * 评论者id
     */
    private Long fromUserId;

    /**
     * 被评论者id
     */
    private Long toUserId;

    /**
     * 创建人id
     */
    @TableField(fill = FieldFill.INSERT)
    private Long creatorId;

    /**
     * 回复时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改人id
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long modifierId;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime modifyTime;

    /**
     * 逻辑删除 0：删除；1:可用
     */
    @TableLogic
    private Integer enable;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
