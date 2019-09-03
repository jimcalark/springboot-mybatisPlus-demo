package com.train.mp.enums;

import com.alibaba.fastjson.annotation.JSONType;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 性别枚举类
 *
 * @author Jim Clark
 * @version 1.0
 * create on  2019/9/3 0003 14:23
 */
@JSONType(serializeEnumAsJavaBean = true)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum GenderEnum {
    MAN(1, "男性"),
    WOMEN(2, "女性");

    private int value;
    private String desc;

    GenderEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
