package com.train.mp.support;

/**
 * 自定义异常
 *
 * @author Jim Clark
 * @version 1.0
 * create on  2019/9/3 0003 15:39
 */
public class CustomException extends RuntimeException {


    private static final long serialVersionUID = 1L;

    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }
    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(String author, String message, Throwable cause) {
        super(message, cause);
        this.author = author;
    }

    public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }





}
