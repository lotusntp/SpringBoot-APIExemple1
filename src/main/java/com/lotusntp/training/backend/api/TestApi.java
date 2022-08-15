package com.lotusntp.training.backend.api;

import com.lotusntp.training.backend.business.TestBusiness;
import com.lotusntp.training.backend.exception.BaseException;
import com.lotusntp.training.backend.model.MRegisterRequest;
import com.lotusntp.training.backend.model.TestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/test")
public class TestApi {

    // method : 1 use autowired call field injection
//    @Autowired
//    private TestResponse business;

    // method : 2 use call Constructor Injection
    private final TestBusiness business;


    public TestApi(TestBusiness business) {
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
    public ResponseEntity<String>  register(@RequestBody MRegisterRequest request) throws BaseException {

        String response = business.register(request);
        return ResponseEntity.ok(response);

    }


}
