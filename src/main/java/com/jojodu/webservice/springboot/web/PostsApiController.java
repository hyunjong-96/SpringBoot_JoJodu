package com.jojodu.webservice.springboot.web;

import com.jojodu.webservice.springboot.service.posts.PostsService;
import com.jojodu.webservice.springboot.web.dto.PostsResponseDto;
import com.jojodu.webservice.springboot.web.dto.PostsSaveRequestDto;
import com.jojodu.webservice.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts")
public class PostsApiController {
    final private PostsService postsService;

    @PostMapping("")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }
    @GetMapping("/{id}")
    public PostsResponseDto findById(@PathVariable(name = "id") Long id){
        return postsService.findById(id);
    }
    @PutMapping("/{id}")
    public Long update(@PathVariable(name = "id") Long id, @RequestBody PostsUpdateRequestDto postsUpdateRequestDto){
        return postsService.update(id,postsUpdateRequestDto);
    }
}
