package com.dicka.reactivex.reactivexresearch.service;

import com.dicka.reactivex.reactivexresearch.entity.Author;
import com.dicka.reactivex.reactivexresearch.exception.ErrorException;
import com.dicka.reactivex.reactivexresearch.model.RequestAuthor;
import com.dicka.reactivex.reactivexresearch.repository.AuthorRepository;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Single<Author> addAuthor(RequestAuthor requestAuthor) {
        return Single.create(singleEmitter -> {
            authorRepository.findByName(requestAuthor.getName())
                    .ifPresentOrElse(author -> {
                        singleEmitter.onError(new ErrorException("error because username  "+requestAuthor.getName()+" is already exists"));
                    }, () -> {
                        Author author = Author
                                .builder().id(UUID.randomUUID().toString())
                                .name(requestAuthor.getName()).build();
                        singleEmitter.onSuccess(authorRepository.save(author));
                    });
        });
    }

    @Override
    public Single<List<Author>> listAuthor() {
        return Single.create(singleEmitter -> {
            if (authorRepository.findAll().isEmpty()){
                singleEmitter.onError(new ErrorException("error because data not found"));
            }else{
                singleEmitter.onSuccess(authorRepository.findAll());
            }
        });
    }

    @Override
    public List<String> getAuthorName() {
        List<String> stringList = new ArrayList<>();
        log.info("data : "+authorRepository.findAll());
        authorRepository.findAll().stream()
                .map(author -> stringList.add(author.getName()));
        return stringList;
    }

}
