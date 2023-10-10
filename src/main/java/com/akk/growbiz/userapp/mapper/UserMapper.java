package com.akk.growbiz.userapp.mapper;

import com.akk.growbiz.userapp.entity.UserEntity;
import com.akk.growbiz.userapp.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(User user);

    User toUser(UserEntity target);

    List<UserEntity> toEntityList(List<User> user);

    List<User> toUserList(List<UserEntity> target);

}
