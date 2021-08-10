package com.dicka.reactive.reactiver2dbc.service;

import com.dicka.reactive.reactiver2dbc.entity.Department;
import com.dicka.reactive.reactiver2dbc.entity.User;
import com.dicka.reactive.reactiver2dbc.model.UserDepartment;
import com.dicka.reactive.reactiver2dbc.repository.DepartmentRepository;
import com.dicka.reactive.reactiver2dbc.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.function.BiFunction;

@Slf4j
@Service
public class UserDepartmentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public Mono<User> createUser(User user){
        return userRepository.save(user);
    }

    public Flux<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Flux<User> getAllUserByAge(int age){
        return userRepository.findByAge(age);
    }

    public Mono<User> findById(int id){
        return userRepository.findById(id);
    }

    public Mono<User> updateUser(Integer userId, User user){
        return userRepository.findById(userId).flatMap(currentUser -> {
            currentUser.setAge(user.getAge());
            currentUser.setName(user.getName());
            currentUser.setSalary(user.getSalary());
            return userRepository.save(currentUser);
        });
    }

    public Mono<User> deleteUser(Integer userId){
        return userRepository.findById(userId).flatMap(currentUser -> userRepository.delete(currentUser)
                .then(Mono.just(currentUser)));
    }

    public Flux<User> fetchUsers(List<Integer> userIds){
        return Flux.fromIterable(userIds)
                .parallel()
                .runOn(Schedulers.elastic())
                .flatMap(i -> findById(i))
                .ordered((u1, u2) -> u2.getId() - u1.getId());
    }

    private Mono<Department> getDepartmentById(int userId){
        return departmentRepository.findByUserId(userId);
    }

    public Mono<UserDepartment> fetchUserDepartment(Integer userId){
        Mono<User> userMono = userRepository.findById(userId).subscribeOn(Schedulers.boundedElastic());
        Mono<Department> userDepartment = getDepartmentById(userId).subscribeOn(Schedulers.elastic());
        return Mono.zip(userMono, userDepartment, userDepartmentBiFunction);
    }

    private BiFunction<User, Department, UserDepartment> userDepartmentBiFunction = (x1, x2) -> UserDepartment
            .builder()
            .userId(x1.getId())
            .departmentId(x2.getId())
            .loc(x2.getLoc())
            .departmentName(x2.getName())
            .salary(x1.getSalary())
            .username(x1.getName())
            .age(x1.getAge())
            .build();
}
