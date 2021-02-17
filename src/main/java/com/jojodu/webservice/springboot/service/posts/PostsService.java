package com.jojodu.webservice.springboot.service.posts;

import com.jojodu.webservice.springboot.domain.posts.Posts;
import com.jojodu.webservice.springboot.domain.posts.PostsRepository;
import com.jojodu.webservice.springboot.web.dto.PostsResponseDto;
import com.jojodu.webservice.springboot.web.dto.PostsSaveRequestDto;
import com.jojodu.webservice.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostsService {
    final private PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }
    @Transactional
    public PostsResponseDto findById(final Long id){
        Optional<Posts> findPost = postsRepository.findById(id);
        return new PostsResponseDto(findPost.get());
    }
    @Transactional
    public Long update(final Long id, final PostsUpdateRequestDto postsUpdateRequestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id= "+id));
        posts.update(postsUpdateRequestDto.getTitle(),postsUpdateRequestDto.getContent());
        return id;
    }
}
