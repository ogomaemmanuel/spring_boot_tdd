package com.safaricom.tdd_demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class PostControllerUnitTest {
    @Mock
    private  PostService postServiceMock;

   @InjectMocks
    private  PostController postController;
@Captor
    ArgumentCaptor<PostCreateRequest> postCreateRequestArgumentCaptor;

//    @BeforeEach
//    public void init(){
//        postServiceMock= Mockito.mock(PostService.class);
//        postController = new PostController(postServiceMock);
//    }


    @Test
    public void test_save_post(){
//Arrange
        PostCreateRequest createRequest= new PostCreateRequest();
        createRequest.setContent("Test content");
        var post = new Post();
        post.setContent("content");
        when(postServiceMock.savePost(createRequest)).thenReturn(post);
        //Act/execute
         postController.savePost(createRequest);
        //Assert /verify
        verify(postServiceMock).savePost(postCreateRequestArgumentCaptor.capture());
        PostCreateRequest request=  postCreateRequestArgumentCaptor.getValue();
        Assertions.assertNotNull(request.getCreatedAt());
    }



}
