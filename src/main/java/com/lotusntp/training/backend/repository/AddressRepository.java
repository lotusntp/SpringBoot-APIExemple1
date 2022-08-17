package com.lotusntp.training.backend.repository;

import com.lotusntp.training.backend.entity.Address;
import com.lotusntp.training.backend.entity.Social;
import com.lotusntp.training.backend.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address,String> {


    List<Address> findByUser(User user);



}
