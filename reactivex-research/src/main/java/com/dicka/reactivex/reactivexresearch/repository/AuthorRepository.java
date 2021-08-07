package com.dicka.reactivex.reactivexresearch.repository;

import com.dicka.reactivex.reactivexresearch.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String>,
        JpaSpecificationExecutor<Author> {

    @Query(value = "select * from authors where name=:name", nativeQuery = true)
    Optional<Author> findByName(@Param("name")String name);

}
