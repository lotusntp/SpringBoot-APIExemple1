package com.lotusntp.training.backend.repository;

import com.lotusntp.training.backend.entity.Social;
import com.lotusntp.training.backend.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SocialRepository extends CrudRepository<Social,String> {


    Optional<Social> findByUser(User user);



}
