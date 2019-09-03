package com.train.mp.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * mybatisPlus 配置类
 *
 * @author Jim Clark
 * @version 1.0
 * create on  2019/9/3 0003 15:25
 */
@Component
public class MybatisPlusConfig implements MetaObjectHandler {

    /**
     * 获取当前用户id
     *
     * @return
     */
    private Long getCurrentUserId() {
        return 100L;
    }

    /**
     * 自动填充新增策略
     *
     * @param metaObject metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //具有该字段且没有手动填充时才去填充
        if (metaObject.hasSetter("createTime") && Objects.isNull(getFieldValByName("createTime", metaObject))) {
            setInsertFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }

        if (metaObject.hasSetter("creatorId") && Objects.isNull(getFieldValByName("creatorId", metaObject))) {
            setInsertFieldValByName("creatorId", getCurrentUserId(), metaObject);
        }
        if (metaObject.hasSetter("enable") && Objects.isNull(getFieldValByName("enable", metaObject))) {
            setInsertFieldValByName("enable", 1, metaObject);
        }
    }

    /**
     * 自动填充修改策略
     *
     * @param metaObject metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasSetter("modifyTime") && Objects.isNull(getFieldValByName("modifyTime", metaObject))) {
            setUpdateFieldValByName("modifyTime", LocalDateTime.now(), metaObject);
        }

        if (metaObject.hasSetter("modifierId") && Objects.isNull(getFieldValByName("modifierId", metaObject))) {
            setUpdateFieldValByName("modifierId", getCurrentUserId(), metaObject);
        }
    }
}
