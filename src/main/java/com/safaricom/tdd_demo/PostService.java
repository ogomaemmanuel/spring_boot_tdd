package com.safaricom.tdd_demo;

import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post savePost(PostCreateRequest postCreateRequest) {
        Post post = new Post();
        post.setContent(postCreateRequest.getContent());
        return this.postRepository.save(post);
    }


}
