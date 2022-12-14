package com.lotusntp.training.backend.business;

import com.lotusntp.training.backend.entity.User;
import com.lotusntp.training.backend.exception.BaseException;
import com.lotusntp.training.backend.exception.FileException;
import com.lotusntp.training.backend.exception.UserException;
import com.lotusntp.training.backend.mapper.UserMapper;
import com.lotusntp.training.backend.model.MLoginRequest;
import com.lotusntp.training.backend.model.MRegisterRequest;
import com.lotusntp.training.backend.model.MRegisterResponse;
import com.lotusntp.training.backend.service.TokenService;
import com.lotusntp.training.backend.service.UserService;
import com.lotusntp.training.backend.util.SecurityUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserBusiness {

    private final UserService userService;

    private final UserMapper userMapper;

    private final TokenService tokenService;

    public UserBusiness(UserService userService, UserMapper userMapper, TokenService tokenService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.tokenService = tokenService;
    }

    public String refreshToken() throws BaseException {

        Optional<String> opt = SecurityUtil.getCurrentUserId();
        if(opt.isEmpty()){
            throw UserException.unAuthorized();
        }

        String userId = opt.get();
        Optional<User> optUser = userService.findById(userId);
        if(optUser.isEmpty()){
            throw UserException.notFound();
        }

        User user = optUser.get();
        return tokenService.tokenize(user);

    }

    public MRegisterResponse register(MRegisterRequest request) throws BaseException {
        User user = userService.create(request.getEmail(), request.getPassword(), request.getName());
        // TODO: mapper
        MRegisterResponse model = userMapper.toRegisterResponse(user);
        return model;
    }

    public String login(MLoginRequest request) throws BaseException {
        //validate request

        //verify database
        Optional<User> opt = userService.findByEmail((request.getEmail()));

        if(opt.isEmpty()){
            throw UserException.loginFailEmailNotFound();
        }

        User user = opt.get();
        if(!userService.matchPassword(request.getPassword(),user.getPassword())){
            throw UserException.loginFailPasswordIncorrect();
        }

        // TODO: generate JWT

        return tokenService.tokenize(user);


    }

    public String uploadProfilePicture(@RequestPart MultipartFile multipartFile) throws BaseException {

        if(multipartFile == null){
            throw FileException.fileNull();
        }
        if(multipartFile.getSize() > 1048576 * 2){
            throw FileException.fileMaxSize();
        }

        String contentType = multipartFile.getContentType();
        if (contentType == null){
            throw FileException.fileSupported();
        }

        List<String> supportedTypes = Arrays.asList("image/jpeg","image/png");
        if (!supportedTypes.contains(contentType)){
            throw FileException.fileSupported();
        }
        // TODO: upload file fFile Storage (AWS S3, etc...)
        try {
            byte[] bytes = multipartFile.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "";
    }
}
