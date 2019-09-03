package com.train.mp.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Iterator;
import java.util.regex.Pattern;

/**
 * 参数校验类
 *
 * @author jim clark
 * @version 1.0
 */
public class ValidateParam {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidateParam.class);

    private static int[] weight = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};    //十七位数字本体码权重
    private static char[] validate = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};    //mod11,对应校验码字符值

    public ValidateParam() {
    }

    public static void validateLength(int min, int max, String param, String errorCode) throws CustomException {
        if (StringUtils.hasText(param)) {
            int length = param.length();
            if (length < min || length > max) throwError(errorCode);

        } else {
            throwError(errorCode);
        }

    }

    /**
     * 校验UUId
     *
     * @param errorCode message.properties 中的错误编码
     * @param id        id
     * @throws CustomException 业务异常
     */
    public static void validateUUId(String errorCode, String id) throws CustomException {
        regex("[a-z\\d]{8}-[a-z\\d]{4}-[a-z\\d]{4}-[a-z\\d]{4}-[a-z\\d]{12}", errorCode, id);
    }

    /**
     * 校验电话号码
     *
     * @param errorCode message.properties 中的错误编码
     * @param phoneNo   phoneNo
     * @throws CustomException 业务异常
     */
    public static void validatePhoneNo(String errorCode, String phoneNo) throws CustomException {
        String regex = "[1][3-9][0-9]{9}";
        regex(regex, errorCode, phoneNo);
    }

    /**
     * 校验短信验证码
     *
     * @param errorCode message.properties 中的错误编码
     * @param smsCode   smsCode
     * @throws CustomException 业务异常
     */
    public static void validateSmsCode(String errorCode, String smsCode) throws CustomException {
        regex("[1][3-9][0-9]{9}", errorCode, smsCode);
    }

    /**
     * 校验密码格式
     *
     * @param errorCode message.properties 中的错误编码
     * @param password  password
     * @throws CustomException 业务异常
     */
    public static void validatePassword(String errorCode, String password) throws CustomException {
        String regex = "[a-zA-Z\\d`!@#$%^&*()_+~\\-=\\[\\]{}|;',./<>?\\\\:\"]{6,30}";
        regex(regex, errorCode, password);
    }

    /**
     * 校验邮箱格式
     *
     * @param errorCode message.properties 中的错误编码
     * @param email     email
     * @throws CustomException 业务异常
     */
    public static void validateEmail(String errorCode, String email) throws CustomException {
        String regex = "[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+\\.){1,2}[a-z0-9]{1,4}";
        regex(regex, errorCode, email);
    }

    /**
     * 校验name 格式
     *
     * @param errorCode message.properties 中的错误编码
     * @param name      name
     * @throws CustomException 业务异常
     */
    public static void validNameIllegal(String errorCode, String name) throws CustomException {
        String regex = "[a-zA-Z0-9_]+";
        regex(regex, errorCode, name);
    }

    /**
     * 校验url 格式
     *
     * @param errorCode message.properties 中的错误编码
     * @param url       url
     * @throws CustomException 业务异常
     */
    public static void validUrlIllegal(String errorCode, String url) throws CustomException {
        String regex = "[a-zA-Z0-9_\\-]+";
        regex(regex, errorCode, url);
    }

    /**
     * 校验中文名称 格式
     *
     * @param errorCode message.properties 中的错误编码
     * @param name      name
     * @throws CustomException 业务异常
     */
    public static void validChineseNameIllegal(String errorCode, String name) throws CustomException {
        String regex = "[a-zA-Z0-9一-龥_]+";
        regex(regex, errorCode, name);
    }

    /**
     * 自定义校验
     *
     * @param errorCode message.properties 中的错误编码
     * @param regex     正则
     * @param param     param
     * @throws CustomException 业务异常
     */
    public static void regex(String regex, String errorCode, String param) throws CustomException {
        if (StringUtils.hasText(param)) {
            boolean flg = Pattern.matches(regex, param);
            if (!flg) throwError(errorCode);
        } else {
            throwError(errorCode);
        }
    }

    /**
     * 校验身份证算法
     *
     * @param idCard idCard
     * @return
     */

    public static boolean validateIdCard(String idCard) throws CustomException {
        if (!StringUtils.hasText(idCard)) return false;
        String id17 = idCard.substring(0, idCard.length() - 1);
        int sum = 0;
        int mode = 0;
        for (int i = 0; i < id17.length(); i++) {
            sum = sum + Integer.parseInt(String.valueOf(id17.charAt(i))) * weight[i];
        }
        mode = sum % 11;
        String match = validate[mode] + "";
        String byMatch = idCard.substring(idCard.length() - 1, idCard.length()).toUpperCase();
        return match.equals(byMatch);
    }


    /**
     * @param bindingResult bindingResult
     * @throws CustomException 业务异常
     */
    public static void validateBindingResult(BindingResult bindingResult) throws CustomException {
        if (bindingResult.hasErrors()) {
            Iterator var1 = bindingResult.getAllErrors().iterator();
            while (var1.hasNext()) {
                ObjectError error = (ObjectError) var1.next();
                throwError(error.getDefaultMessage());
            }
        }
    }


    /**
     * 抛出异常
     *
     * @param msg 异常信息
     * @throws CustomException CustomException
     */
    public static void throwError(String msg) throws CustomException {
        LOGGER.error("Business exception occurred:{}", msg);
        throw new CustomException(msg);
    }


}
