package com.lotusntp.training.backend.exception;

public class UserException extends BaseException {

    public UserException(String code) {
        super("user." + code);
    }

    // user.register.email.null
    public  static UserException requsetNull(){
        return  new UserException("register.email.null");
    }

    public  static UserException emailNull(){
        return  new UserException("register.email.null");
    }
}
