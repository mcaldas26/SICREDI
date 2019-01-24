package br.com.sicredi.challange.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BaseBusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7187762869112287863L;

	public BaseBusinessException(String msg) {
		super(msg);
	}

	public BaseBusinessException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
