package com.web.pj.Exception;

public class UserNotFoundException  extends RuntimeException {
    private static final long serialVersionUID = -6074753940710869977L;

    public UserNotFoundException() {
        super("账号不存在");
    }
}
