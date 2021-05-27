package com.web.pj.Exception;

public class WrongPasswordException extends RuntimeException {
    private static final long serialVersionUID = -6074753940710869977L;

    public WrongPasswordException() {
        super("密码错误");
    }
}
