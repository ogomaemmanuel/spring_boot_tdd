package com.safaricom.tdd_demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PostController.class)
public class PostControllerMvcTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private PostService postService;
    @MockBean
    SecurityFilterChain apiSecurity;

    @BeforeEach
    public void  setUp() throws Exception {
        when(postService.savePost(any(PostCreateRequest.class))).thenReturn(new Post());
    }


    @ParameterizedTest
    @CsvSource(textBlock = """
            { "content": "hello world" }, 400,
            {"content": "hhhhhhhhiiiiiiiiiiiiiiiiiiiiiiihhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"},200,
            {},400
            """)

    @WithMockUser
    public void test_save_post_endpoint(String content,int status) throws Exception {
        mockMvc.perform(post("/posts")
                .content(content).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(status));
    }


}
