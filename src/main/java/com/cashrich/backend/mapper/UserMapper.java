package com.cashrich.backend.mapper;

import com.cashrich.backend.DTO.UserDto;
import com.cashrich.backend.Entity.User;

public class UserMapper {

    public static UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setMobile(user.getMobile());
        userDto.setUserName(user.getUserName());
        return userDto;
    }

}
