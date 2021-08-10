package com.dicka.reactive.reactiver2dbc.repository;

import com.dicka.reactive.reactiver2dbc.entity.Department;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface DepartmentRepository extends ReactiveCrudRepository<Department, Integer> {

  Mono<Department> findByUserId(Integer userId);
}
