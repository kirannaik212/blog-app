package com.blogApp.service;

import com.blogApp.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,Integer id);
    UserDto getUserById(Integer id);
    List<UserDto> getAllUser();
    void deleteUser(Integer id);
}
