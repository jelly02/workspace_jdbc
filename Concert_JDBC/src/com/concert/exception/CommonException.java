package com.concert.exception;

/**
 * 예외 처리 클래스
 * 
 * @author KIMJURI
 *
 */
public class CommonException extends Exception {
	
	public CommonException() {
		super("사용자 예외");
	}
	
	public CommonException(String message) {
		super("[오류] " + message);
	}	
	
	
	

}
