package com.blogApp.controller;

import com.blogApp.dto.UserDto;
import com.blogApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto serviceUser = userService.createUser(userDto);
        return new ResponseEntity<>(serviceUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Integer id) {
        UserDto updatedUser = userService.updateUser(userDto,id);
       // return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/{uid}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("uid") Integer id){
        UserDto userById = userService.getUserById(id);
        return new ResponseEntity<>(userById,HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
    }

}
