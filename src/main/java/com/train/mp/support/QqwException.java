package com.train.mp.support;

/**
 * 自定义异常
 * @author zhang kui
 */
public class QqwException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String author;
	
	public QqwException() {
		super();
	}
	
	public QqwException(String author, String message, Throwable cause) {
		super(message, cause);
		this.author = author;
	}

	public QqwException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public QqwException(String message, Throwable cause) {
		super(message, cause);
	}

	public QqwException(String message) {
		super(message);
	}

	public QqwException(Throwable cause) {
		super(cause);
	}
	
	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}
	
}
