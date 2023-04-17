package com.safaricom.tdd_demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PostController.class)
public class PostControllerMvcTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @BeforeEach
    public void  setUp() throws Exception {
        Post post = new Post();
        post.setContent("Test");
        when(postService.savePost(any(PostCreateRequest.class))).thenReturn(post);
    }


    @ParameterizedTest
    @CsvSource(textBlock = """
            { "content": "hello world" }, 400,
            {"content": "hhhhhhhhiiiiiiiiiiiiiiiiiiiiiiihhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"},200,
            {},400
            """)

    @WithMockUser
    public void test_save_post_endpoint(String content,int status) throws Exception {
        mockMvc.perform(post("/posts").with(csrf())
                .content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(status));
    }
   @WithMockUser
   @ParameterizedTest
    @CsvSource(textBlock = """
            {"content":"hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"},
            """)
    public void   test_save_post_endpoint_returns_200_when_data_is_valid(String request) throws Exception {
        mockMvc.perform(post("/posts").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                .content(request)).andExpect(status().is(200)).andExpect(jsonPath("$.content").isNotEmpty());
        verify(postService).savePost(argThat((arg)->arg.getContent()!=null && arg.getCreatedAt()!=null));
   }


}
