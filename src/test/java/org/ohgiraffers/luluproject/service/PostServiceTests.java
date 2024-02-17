package org.ohgiraffers.luluproject.service;

import org.junit.jupiter.api.Test;
import org.ohgiraffers.luluproject.domain.Post;
import org.ohgiraffers.luluproject.dto.PageRequestDTO;
import org.ohgiraffers.luluproject.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostServiceTests {
    @Autowired
    private PostService postService;

    @Test
    public void testRegister() {
        System.out.println(postService.getClass().getName());

        PostDTO postdto = PostDTO.builder()
                .title("Sample Title...")
                .content("Sample Content...")
                .build();

        Long post_id = postService.register(postdto);
    }

    @Test
    public void testModify() {

        PostDTO postDTO = PostDTO.builder()
                .post_id(101L)
                .title("Updated....102")
                .content("Updated content 101...")
                .build();

        postService.modify(postDTO);
    }
}
