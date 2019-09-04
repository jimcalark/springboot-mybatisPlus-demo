package com.train.mp.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.train.mp.entity.User;
import com.train.mp.enums.GenderEnum;
import com.train.mp.service.IUserService;
import com.train.mp.support.ApiResult;
import com.train.mp.support.ValidateParam;
import com.train.mp.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户  [单表操作]
 * </p>
 *
 * @author Jim clark
 * @since 2019-09-03
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    /**
     * 新增或修改用户  [领域模型和字段填充 为null 的字段不会加入sql]
     * 传回id即为修改
     *
     * @param userVo        vo
     * @param bindingResult bindingResult
     * @return
     */
    @PostMapping("save")
    public ApiResult saveUser(@RequestBody @Valid UserVo userVo, BindingResult bindingResult) {
        ValidateParam.validateBindingResult(bindingResult);
        userVo.convertToEntity().insertOrUpdate();
        return ApiResult.success();
    }

    /**
     * 根据用户姓名获取用户所有信息  [getOne]
     * 注意gender这个枚举属性的返回值
     *
     * @param name 用户姓名
     * @return
     */
    @GetMapping("getByName")
    public ApiResult getByName(@RequestParam String name) {
        User result = userService.getOne(Wrappers.<User>lambdaQuery().likeRight(User::getName, name));
        return ApiResult.successResult(result);
    }

    /**
     * 获取枚举类的所有项  [枚举类转为json]
     * 返回值的类型为：
     * {
     * "code": 1,
     * "message": "成功",
     * "data": [
     * {
     * "value": 1,
     * "desc": "男性"
     * },
     * {
     * "value": 2,
     * "desc": "女性"
     * }
     * ]
     * }
     *
     * @return
     */
    @GetMapping("getEnums")
    public ApiResult getEnumValues() {
        return ApiResult.successResult(GenderEnum.values());
    }

    /**
     * 通过id获取用户姓名 [只获取一个字段]
     *
     * @param userId 用户id
     * @return
     */
    @GetMapping("getNameById")
    public ApiResult getUserNameById(@RequestParam Long userId) {
        Map<String, Object> map = userService.getMap(new QueryWrapper<User>().lambda().select(User::getName).eq(User::getId, userId));
        return ApiResult.successResult(map.get("name"));
    }


    /**
     * 指定获取一个对象的某些属性值 [getMap]
     *
     * @param userId 用户id
     * @return
     */
    @GetMapping("FiledList")
    public ApiResult getSomeFiledListByMap(@RequestParam Long userId) {
        Map<String, Object> map = userService.getMap(new LambdaQueryWrapper<User>().select(User::getId, User::getName).eq(User::getId, userId));//map
        //User user = userService.getOne(new LambdaQueryWrapper<User>().select(User::getId, User::getName).eq(User::getId, userId));//实体
        return ApiResult.successResult(map);
    }

    /**
     * 忽略某些字段  [指定哪些字段不查出  使用的是字段名而非属性名]
     *
     * @param userId 用户id
     * @return
     */
    @GetMapping("ignoreFiledList")
    public ApiResult ignoreFiledList(@RequestParam Long userId) {
        Map<String, Object> result = userService.getMap(new LambdaQueryWrapper<User>().select(User.class, user -> !user.getColumn().equals("create_time")).eq(User::getId, userId));
        //User user = userService.getOne(new LambdaQueryWrapper<User>().select(User.class, user -> !user.getColumn().equals("create_time")).eq(User::getId, userId));
        return ApiResult.successResult(result);
    }


    /**
     * 不建实体修改某些字段  [LambdaUpdateWrapper]
     * <p>
     * 中文编码问题  吴娇凤转码为 URL 编码表内容
     * url编码规则：url编码就是一个字符ascii码的十六进制。不过稍微有些变动，需要在前面加上“%”。比如“\”，它的ascii码是92，92的十六进制是5c，所以“\”的url编码就是%5c
     * http://127.0.0.1:8081/user/updateFiledList?userId=1&name=吴娇凤&phone=152816151231 转码为
     * http://127.0.0.1:8081/user/updateFiledList?userId=1&name=%E5%90%B4%E5%A8%87%E5%87%A4&phone=152816151231
     *
     * @param userId 用户id
     * @param name   姓名
     * @param phone  电话
     * @return
     */
    @PostMapping("updateFiledList")
    public ApiResult updateFiledList(@RequestParam Long userId, @RequestParam String name, @RequestParam String phone) {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<User>().eq(User::getId, userId).set(User::getName, name).set(User::getPhone, phone);
        userService.update(updateWrapper);
        return ApiResult.success();
    }


    /**
     * 单表分页   [返回的自定义对象  注意 IPage<UserVo>  和mapper xml 中的sql]  这种属于自定义sql的查询不会自动过滤掉逻辑删除字段需要手动指出
     *
     * @param page     当前页
     * @param pageSize 页容量
     * @param name     姓名
     * @param phone    电话
     * @return
     */
    @GetMapping("page")
    public ApiResult pageUserList(@RequestParam(required = false, defaultValue = "1") int page,
                                  @RequestParam(required = false, defaultValue = "10") int pageSize,
                                  @RequestParam(required = false) String name, @RequestParam(required = false) String phone) {
        IPage<UserVo> pageParam = new Page<>(page, pageSize);
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<User>().likeRight(StringUtils.hasText(name), User::getName, name).likeRight(StringUtils.hasText(phone), User::getPhone, phone);
        IPage<UserVo> userVoIPage = userService.userVoPage(pageParam, userLambdaQueryWrapper);
        return ApiResult.successResult(userVoIPage);
    }

    public static void main(String[] args) {
        Map<String, Object> result = new HashMap<>();
        result.put("1", "a");
        result.put("2", "b");
        System.err.println(result.values());
    }

}
