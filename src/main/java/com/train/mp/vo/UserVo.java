package com.train.mp.vo;

import com.train.mp.entity.User;
import com.train.mp.enums.GenderEnum;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


/**
 * 用户页面对象
 *
 * @author Jim Clark
 * @version 1.0
 * create on  2019/9/3 0003 14:41
 */
@Data
public class UserVo {

    public  Long id;

    @NotBlank(message = "姓名不能为空")
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z]{2,15}$", message = "姓名为2至15位中文或英文")//pattern 在值为null的时候不会进行校验
    private String name;

    @NotNull(message = "性别不能为空")
    private GenderEnum gender;

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1(3|4|5|6|7|8|9)\\d{9}$", message = "手机号不正确")
    private String phone;


    /**
     * 转为实体类
     *
     * @return
     */
    public User convertToEntity() {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }
}
