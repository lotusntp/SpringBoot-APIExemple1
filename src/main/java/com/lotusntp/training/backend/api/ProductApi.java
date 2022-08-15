package com.lotusntp.training.backend.api;

import com.lotusntp.training.backend.business.ProductBusiness;
import com.lotusntp.training.backend.exception.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductApi {

    private  final ProductBusiness productBusiness;

    public ProductApi(ProductBusiness productBusiness) {
        this.productBusiness = productBusiness;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getProductById(@PathVariable("id") String id) throws BaseException {
        String response = productBusiness.getProductById(id);
        return  ResponseEntity.ok(response);
    }
}
