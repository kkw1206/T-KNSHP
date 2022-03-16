package com.kovan.lib.cipher.exception;


public class UserDefineException extends RuntimeException {
    protected ExceptionCode exceptionCode = new ExceptionCode();

    public UserDefineException() {
        super("User Define Exception Error.");
    }

    public UserDefineException(Exception e) {
        super(e);
    }

    public UserDefineException(ExceptionCode ec) {
        this.exceptionCode = ec;
    }

    public UserDefineException(String errorCd, String errorMsg) {
        this.exceptionCode.setErrorCd(errorCd);
        this.exceptionCode.setErrorMsg(errorMsg);
    }

    public UserDefineException(Exception e, String errorCd, String errorMsg) {
        super(e);
        this.exceptionCode.setErrorCd(errorCd);
        this.exceptionCode.setErrorMsg(errorMsg);
    }

    public UserDefineException(Exception e, ExceptionCode ec) {
        super(e);
        this.exceptionCode = ec;
    }

    public ExceptionCode getExceptionCode() {
        return this.exceptionCode;
    }
}
