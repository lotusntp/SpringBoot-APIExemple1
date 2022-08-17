package com.lotusntp.training.backend.service;

import com.lotusntp.training.backend.entity.Social;
import com.lotusntp.training.backend.entity.User;
import com.lotusntp.training.backend.exception.BaseException;
import com.lotusntp.training.backend.exception.UserException;
import com.lotusntp.training.backend.repository.SocialRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class SocialService {

    private final SocialRepository socialRepository;

    public SocialService(SocialRepository socialRepository) {
        this.socialRepository = socialRepository;
    }

    public Optional<Social> findByUser(User user){
        return  socialRepository.findByUser(user);
    }

    public Social create(User user,String facebook,String line,String instagram,String tiktok){
        // TODO : validate


        Social entity = new Social();
        entity.setUser(user);
        entity.setFacebook(facebook);
        entity.setLine(line);
        entity.setInstagram(instagram);
        entity.setTiktok(tiktok);

        return socialRepository.save(entity);

    }

}
