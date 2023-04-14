package com.safaricom.tdd_demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceUnitTest {

    @Mock
    PostRepository postRepository;

    @InjectMocks
    PostService postService;

    @Test
    public void test_save_post(){
        //Arrange
        when(postRepository.save(any(Post.class))).thenReturn(new Post());
        PostCreateRequest request = new PostCreateRequest();
        request.setCreatedAt(LocalDate.now());
        request.setContent("Test");
        //Act/Execute
        postService.savePost(request);
        //Verify/Assert
        verify(postRepository).save(any(Post.class));

    }




}
