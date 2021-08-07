package com.dicka.reactivex.reactivexresearch.repository;

import com.dicka.reactivex.reactivexresearch.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String>,
        JpaSpecificationExecutor<Book> {
}
