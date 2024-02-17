package org.ohgiraffers.luluproject.service;

import org.ohgiraffers.luluproject.domain.Post;
import org.ohgiraffers.luluproject.dto.PageRequestDTO;
import org.ohgiraffers.luluproject.dto.PageResponseDTO;
import org.ohgiraffers.luluproject.dto.PostDTO;

public interface PostService {

    Long register(PostDTO postDTO);

    PostDTO readOne(Long post_id);

    void modify(PostDTO postDTO);

    void remove(Long post_id);

    PageResponseDTO<PostDTO> list(PageRequestDTO pageRequestDTO);

}
