package com.lotusntp.training.backend.api;

import com.lotusntp.training.backend.business.UserBusiness;
import com.lotusntp.training.backend.entity.User;
import com.lotusntp.training.backend.exception.BaseException;
import com.lotusntp.training.backend.model.MLoginRequest;
import com.lotusntp.training.backend.model.MRegisterRequest;
import com.lotusntp.training.backend.model.MRegisterResponse;
import com.lotusntp.training.backend.model.TestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserApi {

    // method : 1 use autowired call field injection
//    @Autowired
//    private TestResponse business;

    // method : 2 use call Constructor Injection
    private final UserBusiness business;




    public UserApi(UserBusiness business) {
        this.business = business;
    }

    @GetMapping
    public TestResponse test(){
        TestResponse response = new TestResponse();
        response.setName("Lot");
        response.setFood("first");
        return response;
    }

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<MRegisterResponse>  register(@RequestBody MRegisterRequest request) throws BaseException {

        MRegisterResponse response = business.register(request);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/refresh-token")
    public ResponseEntity<String> refreshToken() throws BaseException {
        String token = business.refreshToken();
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MLoginRequest request) throws BaseException {
        String response = business.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/upload-profile")
    public ResponseEntity<String> uploadProfilePicture(@RequestPart MultipartFile file) throws BaseException {
        String response = business.uploadProfilePicture(file);
        return  ResponseEntity.ok(response);
    }


}
