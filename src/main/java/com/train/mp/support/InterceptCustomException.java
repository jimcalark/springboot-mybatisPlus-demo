package com.train.mp.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常拦截处理
 * 将自定义异常发送至MQ消费至数据库
 *
 * @author Jim Clark
 */
@ControllerAdvice
public class InterceptCustomException {

    private Logger LOG = LoggerFactory.getLogger(InterceptCustomException.class);


    /**
     * 拦截自定义异常
     *
     * @param e e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ApiResult baseServiceException(CustomException e) {
        LOG.error(e.getMessage());
        e.printStackTrace();
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
//		将异常信息发送到MQ
//      String message = sw.toString();
//		Map<String, Object> data = new HashMap<String, Object>();
//		data.put("author", e.getAuthor());
//		data.put("message", message);
//		MessageModel messageModel = new MessageModel();
//		messageModel.setData(JSONObject.toJSONString(data));
//		messageModel.setProcessClass("com.quanqiuwa.service.ExceptionHandleServiceImpl");
//		sendMqUtils.sendMsg("EXCEPTION_DIRECT", "EXCEPTION.KEY", messageModel);
        return ApiResult.errorResult(e.getMessage());
    }
}
