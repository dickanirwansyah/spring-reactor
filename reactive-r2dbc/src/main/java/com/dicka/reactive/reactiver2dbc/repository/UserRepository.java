package com.dicka.reactive.reactiver2dbc.repository;

import com.dicka.reactive.reactiver2dbc.entity.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Integer> {

    @Query("select * from user where age=:age")
    Flux<User> findByAge(int age);
}
