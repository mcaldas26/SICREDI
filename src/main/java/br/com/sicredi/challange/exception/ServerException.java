package br.com.sicredi.challange.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1960154298730852945L;

	public ServerException(String msg) {
		super(msg);
	}

	public ServerException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
