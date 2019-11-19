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
@JSONType(serializeEnumAsJavaBean = true)//序列化枚举类为javaBean
@JsonFormat(shape = JsonFormat.Shape.OBJECT)//将Java对象转换成json对象和xml文档，同样也可以将json、xml转换成Java对象
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

    public static Integer getValueByType(String name) {
        for (GenderEnum gender : GenderEnum.values()) {
            if (name.equals(gender.name())) return gender.getValue();
        }
        return 1;
    }
}
