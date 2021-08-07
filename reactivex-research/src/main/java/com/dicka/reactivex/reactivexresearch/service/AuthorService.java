package com.dicka.reactivex.reactivexresearch.service;

import com.dicka.reactivex.reactivexresearch.entity.Author;
import com.dicka.reactivex.reactivexresearch.model.RequestAuthor;
import io.reactivex.Single;

import java.util.List;

public interface AuthorService {

    Single<Author> addAuthor(RequestAuthor requestAuthor);
    Single<List<Author>> listAuthor();
    List<String> getAuthorName();
}
