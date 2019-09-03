package com.train.mp.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * api 接口返回值
 *
 * @author zhang kui
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult {

    /**
     * 1 成功   其余状态失败 401  未登录  403 无权限
     */
    private int code;

    /**
     * 错误或者成功消息
     */
    private String message;

    /**
     * 返回数据值
     */
    private Object data;

    /**
     * 成功消息返回
     *
     * @return ApiResult
     */
    public static ApiResult success() {
        return new ApiResult(1, "成功", null);
    }

    /**
     * 成功消息返回
     *
     * @param data data
     * @return ApiResult
     */
    public static ApiResult successResult(Object data) {

        return new ApiResult(1, "成功", data);
    }

    /**
     * 成功消息返回
     *
     * @param message message
     * @return ApiResult
     */
    public static ApiResult successResult(String message, Object data) {

        return new ApiResult(1, message, data);
    }

    /**
     * 错误消息返回
     *
     * @param message message
     * @return ApiResult
     */
    public static ApiResult errorResult(String message) {

        return new ApiResult(0, message, null);
    }

    /**
     * 错误消息返回+数据
     *
     * @param code    编码
     * @param data    数据
     * @param message 信息
     * @return ApiResult
     */
    public static ApiResult errorResult(int code, String message, Object data) {
        return new ApiResult(code, message, data);
    }


}
