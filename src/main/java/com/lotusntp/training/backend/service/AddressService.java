package com.lotusntp.training.backend.service;

import com.lotusntp.training.backend.entity.Address;
import com.lotusntp.training.backend.entity.Social;
import com.lotusntp.training.backend.entity.User;
import com.lotusntp.training.backend.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    public List<Address> findByUser(User user){
        return  addressRepository.findByUser(user);
    }

    public Address create(User user,String line1,String line2,String zipcode){
        // TODO : validate


        Address entity = new Address();
        entity.setLine1(line1);
        entity.setLine2(line2);
        entity.setZipcode(zipcode);


        return addressRepository.save(entity);

    }

}
