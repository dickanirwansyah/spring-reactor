package com.dicka.reactive.reactiver2dbc.controller;

import com.dicka.reactive.reactiver2dbc.entity.User;
import com.dicka.reactive.reactiver2dbc.model.UserDepartment;
import com.dicka.reactive.reactiver2dbc.service.UserDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/reactive")
public class UserDepartmentController {

    @Autowired
    private UserDepartmentService userDepartmentService;

    @GetMapping(value = "/all-users")
    public Flux<User> getAllUsers(){
        return userDepartmentService.getAllUsers();
    }

    @PostMapping(value = "/create-user")
    public Mono<User> createUser(@RequestBody User user){
        return userDepartmentService.createUser(user);
    }

    @PutMapping(value = "/update-user/{userId}")
    public Mono<User> updateUser(@PathVariable("userId")Integer userId,
                                 @RequestBody User user){
        return userDepartmentService.updateUser(userId, user);
    }

    @PostMapping(value = "/search-user")
    public Flux<User> searchUser(@RequestBody List<Integer> ids){
        return userDepartmentService.fetchUsers(ids);
    }

    @GetMapping(value = "/user-department/{userId}")
    public Mono<UserDepartment> getUserDepartment(@PathVariable("userId")Integer userId){
        return userDepartmentService.fetchUserDepartment(userId);
    }
}
