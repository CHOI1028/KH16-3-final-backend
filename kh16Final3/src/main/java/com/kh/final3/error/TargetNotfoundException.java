package com.kh.final3.error;

public class TargetNotfoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public TargetNotfoundException() {
		super();
	}
	public TargetNotfoundException(String message) {
		super(message);
	}
}