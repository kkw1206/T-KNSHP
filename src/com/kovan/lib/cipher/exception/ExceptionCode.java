package com.kovan.lib.cipher.exception;

public class ExceptionCode {
    private String errorCd = null;
    private String errorMsg = null;

    public ExceptionCode() {
    }

    public ExceptionCode(String ec, String em) {
        this.errorCd = ec;
        this.errorMsg = em;
    }

    public void setErrorCd(String errorCd) {
        this.errorCd = errorCd;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorCd() {
        return this.errorCd;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }
}
