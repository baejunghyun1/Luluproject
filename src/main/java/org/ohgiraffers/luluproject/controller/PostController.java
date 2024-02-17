package org.ohgiraffers.luluproject.controller;

import lombok.RequiredArgsConstructor;
import org.ohgiraffers.luluproject.dto.PageRequestDTO;
import org.ohgiraffers.luluproject.dto.PageResponseDTO;
import org.ohgiraffers.luluproject.dto.PostDTO;
import org.ohgiraffers.luluproject.service.PostService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@RestController
@RequestMapping("/lulu")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/list")

    public void list(PageRequestDTO pageRequestDTO, Model model){

        PageResponseDTO<PostDTO> responseDTO = postService.list(pageRequestDTO);

        model.addAttribute("responseDTO", responseDTO);
    }

    @GetMapping("/register")
    public void registerGET() {

    }
    @PostMapping("/register")
    public String registerPost(PostDTO postDTO, RedirectAttributes redirectAttributes){

        Long post_id = postService.register(postDTO);

        redirectAttributes.addFlashAttribute("result", post_id);

        return  "redirect:/lulu/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(Long post_id, PageRequestDTO pageRequestDTO, Model model){

        PostDTO postDTO = postService.readOne(post_id);

        model.addAttribute("post_id", postDTO);
    }

    @PostMapping("/modify")
    public String modify( PageRequestDTO pageRequestDTO,
                          PostDTO postDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()) {
            String link = pageRequestDTO.getLink();

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            redirectAttributes.addAttribute("post_id", postDTO.getPost_id());

            return "redirect:/lulu/modify?"+link;
        }
        postService.modify(postDTO);

        redirectAttributes.addFlashAttribute("result", "modified");

        redirectAttributes.addAttribute("post_id", postDTO.getPost_id());

        return "redirect:/lulu/read";
    }

    @PostMapping("/remove")
    public String remove(Long post_id, RedirectAttributes redirectAttributes){

        postService.remove(post_id);

        redirectAttributes.addFlashAttribute("result", "removed");

        return "redirect:/lulu/list";
    }
}
