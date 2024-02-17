package org.ohgiraffers.luluproject.repository;

import org.junit.jupiter.api.Test;
import org.ohgiraffers.luluproject.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class PostRepositoryTests {
    @Autowired
    private PostRepository postRepository;

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1,100).forEach(i -> {
            Post post = Post.builder()
                    .title("title..." + i)
                    .content("content..." + i)
                    .build();

           postRepository.save(post);
        });
    }

    @Test
    public void testDelete(){

        Long post_id = 1L;
        postRepository.deleteById(post_id);
    }

    @Test
    public void testUpdate() {
        Long post_id = 100L;

        Optional<Post> result = postRepository.findById(post_id);

        Post post = result.orElseThrow();

        post.change("update..title 100", "update content 100");

        postRepository.save(post);
    }
}


