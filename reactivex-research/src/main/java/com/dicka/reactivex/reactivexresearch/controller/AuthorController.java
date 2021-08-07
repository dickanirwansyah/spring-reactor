package com.dicka.reactivex.reactivexresearch.controller;

import com.dicka.reactivex.reactivexresearch.entity.Author;
import com.dicka.reactivex.reactivexresearch.model.RequestAuthor;
import com.dicka.reactivex.reactivexresearch.service.AuthorService;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping(value = "/create-author")
    public ResponseEntity<Single<Author>> createAuthor(@RequestBody RequestAuthor requestAuthor){
        return ResponseEntity.ok(authorService.addAuthor(requestAuthor));
    }

    @GetMapping(value = "/list-single")
    public ResponseEntity<Single<List<Author>>> listAuthor(){
        return ResponseEntity.ok(authorService.listAuthor());
    }

    @GetMapping(value = "/list-name")
    public ResponseEntity<List<String>> getAuthorName(){
        return ResponseEntity.ok(authorService.getAuthorName());
    }
}
