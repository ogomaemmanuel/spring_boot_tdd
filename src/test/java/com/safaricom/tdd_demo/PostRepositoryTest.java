package com.safaricom.tdd_demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;


    @Test
    public void test_save(){
        Post post = new Post();
        postRepository.save(post);
    }

    @Test
    public void test_getAllPosts(){
        Post post = new Post();
        this.postRepository.save(post);
       var posts= this.postRepository.getPosts();
        Assertions.assertEquals(1,posts.size());
    }

}
