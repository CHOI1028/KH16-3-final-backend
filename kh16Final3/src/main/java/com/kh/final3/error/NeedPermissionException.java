package com.kh.final3.error;

//권한 부족으로 인해서 실행이 불가능한 경우 발생하는 예외
public class NeedPermissionException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public NeedPermissionException() {
		super();
	}
	public NeedPermissionException(String message) {
		super(message);
	}
}
