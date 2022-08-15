package com.lotusntp.training.backend.business;

import com.lotusntp.training.backend.exception.BaseException;
import com.lotusntp.training.backend.exception.UserException;
import com.lotusntp.training.backend.model.MRegisterRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TestBusiness {

    public String register(MRegisterRequest request) throws BaseException {
        if (request == null) {
            throw UserException.requsetNull();
        }
        if (Objects.isNull(request.getEmail())) {
            throw UserException.emailNull();
        }
        return "";
    }
}
