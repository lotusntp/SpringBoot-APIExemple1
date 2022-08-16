package com.lotusntp.training.backend.mapper;

import com.lotusntp.training.backend.entity.User;
import com.lotusntp.training.backend.model.MRegisterResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    MRegisterResponse toRegisterResponse(User user);
}
