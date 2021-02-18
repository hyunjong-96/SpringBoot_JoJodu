package com.jojodu.webservice.springboot.domain.posts;

//import org.junit.After;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;


import com.jojodu.webservice.springboot.service.posts.PostsService;
import com.jojodu.webservice.springboot.web.dto.PostsSaveRequestDto;
import com.jojodu.webservice.springboot.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsRepositoryTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private PostsRepository postsRepository;
    @Autowired
    private PostsService postsService;

    @After
    public void tearDown() throws Exception{
        postsRepository.deleteAll();
    }

    @Test
    public void Posts_등록된다(){
        //given
        String title = "test_title";
        String content = "test_content";
        PostsSaveRequestDto postsSaveRequestDto =PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("이현종")
                .build();
        String url = "http://localhost:"+port+"/api/v1/posts";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,postsSaveRequestDto,Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void Posts_수정하기()throws Exception{
        //given
        Posts savePosts = Posts.builder()
                .title("title")
                .content("content")
                .author("이현종")
                .build();
        Long getId = savePosts.getId();
        String convertTitle = "title2";
        String convertContent = "content2";
        String url = "http://localhost:"+port+"/api/v1/posts"+getId;
        PostsUpdateRequestDto updateRequestDto = PostsUpdateRequestDto.builder()
                .title(convertTitle)
                .content(convertContent)
                .build();
        //when
        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(updateRequestDto);
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url,HttpMethod.PUT,requestEntity,Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(convertTitle);
        assertThat(all.get(0).getContent()).isEqualTo(convertContent);
    }
}
