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

    //CREATE

    public  static UserException createEmailNull(){
        return  new UserException("create.email.null");
    }

    public  static UserException unAuthorized(){
        return  new UserException("unauthorized");
    }

    public  static UserException notFound(){
        return  new UserException("user.not.found");
    }
    public  static UserException createPasswordNull(){
        return  new UserException("create.password.null");
    }

    public  static UserException createNameNull(){
        return  new UserException("create.name.null");
    }

    public  static UserException createEmailDuplicated(){
        return  new UserException("create.email.duplicated");
    }

    public  static UserException loginFailEmailNotFound(){
        return  new UserException("login.fail.email");
    }

    public  static UserException loginFailPasswordIncorrect(){
        return  new UserException("login.fail.email");
    }
}
