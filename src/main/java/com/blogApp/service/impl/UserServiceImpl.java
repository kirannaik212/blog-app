package com.blogApp.service.impl;

import com.blogApp.entity.User;
import com.blogApp.exceptions.ResourceNotFoundException;
import com.blogApp.dto.UserDto;
import com.blogApp.repositories.UserRepository;
import com.blogApp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User saved = userRepo.save(user);
        return userToDto(saved);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer id) {
        User user = userRepo.findById(id).orElseThrow((() -> new ResourceNotFoundException("uesr", "id", id)));
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());

        User savedUser = userRepo.save(user);
        return userToDto(savedUser);
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
        return userToDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> allUsers = userRepo.findAll();
        List<UserDto> userList = allUsers.stream().map(user -> userToDto(user)).collect(Collectors.toList());
        return userList;
    }

    @Override
    public void deleteUser(Integer id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", " id", id));
        userRepo.delete(user);

    }

    private User dtoToUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        /*User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());*/
        return user;
    }


    private UserDto userToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        /*UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(userDto.getEmail());
        userDto.setPassword(userDto.getPassword());
        userDto.setAbout(user.getAbout());*/
        return userDto;
    }
}
