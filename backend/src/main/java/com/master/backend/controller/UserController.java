package com.master.backend.controller;

import com.master.backend.domain.User;
import com.master.backend.dto.LoginDTO;
import com.master.backend.dto.UserDTO;
import com.master.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/api/user/login")
    LoginDTO login(@RequestBody @Valid UserDTO request) throws Exception {
        return userService.login(request);
    }

    @GetMapping("/api/user")
    List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/api/user/{id}")
    User getById(@PathVariable Integer id) throws Exception{
        return userService.getById(id);
    }

    @PostMapping("/api/user")
    @ResponseStatus(HttpStatus.CREATED)
    User add(@RequestBody User user) throws Exception{
        return userService.addUser(user);
    }

    @PutMapping("/api/user/{id}")
    User update(@RequestBody User newUser,@PathVariable Integer id) throws Exception{
        return userService.update(newUser,id);
    }

    @DeleteMapping("/api/user/{id}")
    void deleteById(@PathVariable Integer id) throws Exception{
        userService.deleteById(id);
    }
}
