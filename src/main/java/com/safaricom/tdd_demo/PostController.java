package com.safaricom.tdd_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/posts")
public class PostController {
    private  final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<?> savePost(@RequestBody @Valid PostCreateRequest postRequest) {
       postRequest.setCreatedAt(LocalDate.now());
        Post post=this.postService.savePost(postRequest);
         return  ResponseEntity.ok(post);
    }


}
