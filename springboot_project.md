# 1.프로젝트 만들기

1. gradle을 이용해서 프로젝트 생성
2. GroupId와 ArtifactId작성(artifactId가 프로젝트의 이름)
3. 생성완료

## [1]빌드도구(Build Tool)

* 빌드 도구는 프로젝트에서 사용하는 라이브러리 의존성을 관리하고 애플리케이션을 애플리케이션을 배포가능한 상태로 포장(Packaging or Archiving)하는 과정을 담당한다.
* gradle과 maven이 있다.

## [2]build.gradle

스프링 부트는 빌드에 사용하는 바이너리 플로그인은 buildscripts와 plugins 두 가지 방식으로 선언이 가능하다.

* buildscripts방식
  gradle에서 바이너리 플러그인을 정의하는 고전적인 방식. 빌드스크립트(build.gradle)파일 안에 빌드 스크립트를 정의하는 어색함을 자아내는 부분.
* plugins방식
  buildscripts 플러그인 선언부를 이해할 수 있는 형태로 개선한 것으로 gradle 4.6부터 적용되었다.

![image](https://user-images.githubusercontent.com/57162257/108158770-023bbf80-7129-11eb-9d5d-bb2ea729327d.png)

* io.spring.dependency-management 
  스프링 부트의 의존성들을 관리해 주는 필수적인 플러그인.

* dependencies

  프로젝트에 필요한 의존성들을 선언하는곳.



# 2.Controller

```java
@RestController
public class HelloController{
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
```

## [1]HelloControllerTest

HelloController가 있는 위치와 동일하게 src/Test 디렉토리에도 생성해준다. 대신 테스트 코드를 작성할 클래스는 대상 클래스 이름에 Test를 붙인다.

```java
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class) //1
@WebMvcTest(controllers = HelloController.class) //2
public class HelloControllerTest{
    @Autowired //3
    private MockMvc mvc; //4
    
    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";
        mvc.perform(get("/hello")) //5
            .andExpect(status().isOk()) //6
            .andExpect(content().string(hello)) //7
            .andDo(print()); //8
    }
}
```

1. @RunWith
   * JUnit프레임워크가 내장된 Runner를 실행할때 @RunWith어노테이션을 통해 SpringRunner.class라는 확장된 클래스를 실행하라고 지시함.
   * SpringRunner라는 스프링 실행자를 사용함.
   * 즉, 스프링 부트 테스트와 JUnit사이에 연결자 역할을한다.
2. @WebMvcTest
   * 여러 스프링 테스트 어노테이션 중, Web(Spring Mvc)에 집중할 수 있는 어노테이션
   * 선언할 경우 @Controller, @ControllerAdvice등을 사용할 수 있다.
   * 단, @Service, @Component, @Repository등은 사용할수 없다
   * 즉, Controller만 사용할수 있다.
3. @Autowired
   * 스프링이 관리하는 빈(Bean)을 주입 받는다.
4. private MockMvc mvc
   * 웹 API를 테스트할 때 사용한다.
   * 스프링 MVC테스트의 시작점.
   * 이 클래스를 통해 HTTP GET,POST등에 대한 API테스트를 할 수 있다.
   * 객체를 통해 실제 컨테이너가 실행되는 것은 아니지만 로직상으로 테스트를 진행할수 있다.
5. mvc.perform(get("/hello"))
   * MockMvc를 통해 /hello 주소로 HTTP GET요청을 한다.
   * 체이닝이 지원되서 여러 검증 기능을 이어서 선언할수 있다.
   * get같은 경우 자동 import가 되지않아서 직접 작성함request.MockRequestBuilder에 있다.)
6. .andExpect(status().isOk)
   * mvc.perform의 결과를 검증
   * HTTP Header의 Status를 검증.
7. .andExpect(content().string(hello))
   * 응답 본문의 내용을 검증.
8. .andDo(print())
   * 디테일한 테스트 결과를 확인할수 있다.
   * ![image](https://user-images.githubusercontent.com/57162257/108313466-be65ba80-71fb-11eb-8a06-b4a34db1a2b1.png)

# *테스트코드

* **TDD** : 테스트가 주도하는 개발, 테스트 코드를 먼저 작성
* **단위테스트** : 기능 단위의 테스트 코드를 작성
* **JUnit** : 테스트 코드 작성을 도와주는 프레임워크(Java)

## [1]Application클래스

@SpringBootApplication 어노테이션이 존재하는 메인 클래스인 Application클래스는 @SpringBootApplication이 있는 위치부터 설정을 읽어가기 때문에 **이 클래스는 항상 프로젝트의 최상단에 위치해야한다.**

main메소드에서 실행하는 **SpringApplication.run**으로 인해 내장 WAS를 실행한다. 내장 WAS를 가지고 있으므로 서버에 톰캣을 설치할 필요가없어지고, 스프링 부트로 만들어진 Jar파일(실행 가능한 Java패키징 파일)로 실행하면 된다.



# 3.Dto(Data transfer object)

Controller <-> Service <-> Repository간에 필요한 데이터를 캡슐화한 데이터 전달 객체

controller에서 service로 전달할때 필요한 dto

```java
@Getter
@RequiredArgsConstructor
public class HelloResponseDto{
    final private String name;
    final private int amount;
}
```

## [1]HelloControllerTest]

HelloController에서 params로 name과 amount를 받을것이다.

```java
@GetMapping("/hello/dto")
    public HelloResponseDto helloDto(
            @RequestParam(name = "name")String name,
            @RequestParam(name = "amount")int amount){
        return new HelloResponseDto(name,amount);
    }
```

그런다음 테스트코드를 작성해보자

```java
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest(controller = HelloController.class)
public class HelloControllerTest{
    @Autowired
    private MockMvc mvc;
    
    ...
        
    @Test
	public void helloDto가_반환된다() throws Exception{
        String name = "이현종";
        int amount = 1000;
        
        mvc.perform(get("/hello/dto")
                   .param("name",name)
                   .param("amount",String.valueOf(amount))) //1
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name",is(name))) //2
            .andExpect(jsonPath("$.amount",is(amount)))
            .andDo(print());
    }
}
```

결과
![image](https://user-images.githubusercontent.com/57162257/108317216-4b5f4280-7201-11eb-9137-5053e48d3b05.png)

1. param("amount",String.valueOf(amount))
   * param은 mvc.perform에서 url을 보낼때 query로 값을 보낼때 사용
   * 첫번쨰 파라미터는 query key, 두번째 파라미터는 query value이다. 
   * 값이 String만 허용되기떄문에 int형인 amount를 convert해준다.
2. jsonPath("$.name",is(name))
   * JSON 응답값을 필드별로 검증할수 있는 메소드
   * $를 기준으로 필드명을 명시한다
   * name과 amount를 검증하니 $.name과 $.amount로 검증한다.

# 4.domain

## [application.yml]

MySQL과 연결하기위해 먼저 application.yml에서 설정먼저해준다.

```java
spring:
  datasource:
    url: "jdbc:mysql://127.0.0.1:3306/sopt?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC"
    username: "root"
    password: "dlguswhd3323!"
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    database-platform: "org.hibernate.dialect.MySQL5Dialect"
    database: mysql
```

* **spring.datasurce.url** 
  * 데이터베이스와 연결시킬 url설정
* **spring.jpa.database-platform** 
  * JPA 데이터베이스 플랫폼을 지정한다
  * MySQL5Dialect, MySQL5InnoDBDialect 등 여러가지가 있다.
  * MySQL5InnoDBDialect를 사용시 테이블이 존재하지않다고 에러가 발생해서 MySQL5Dialect 플랫폼 사용
* **spring.jpa.shpw-sql**
  * 콘솔에 JPA실행 쿼리를 출력합니다.
* **spring.jpa.hibernate.ddl_auto**
  * none : 아무것도 실행하지 않는다.
  * create : SessionFactory가 시작될 떄 기존테이블을 삭제 후 다시 생성한다.
  * create-drop : create와 같으나 SessionFactory가 종료될 떄 drop을 실행
  * update : 변경된 스키마만 반영
  * validate : 엔티티와 테이블이 정상적으로 매핑되었는지만 확인.



h2사용시 로그확인하는 방법
jojodu님께서 직접 알려주신바로는 springboot 버전이 2.1.10이후 부터는 위와 같이 jdbc-url를 직접 설정해주어야만 작동이 된다고 한다. 

```java
//쿼리로그 보여주는 옵션
spring.jpa.show-sql=true
    
//쿼리로그 to MySQL
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL57Dialect
spring.jpa.properties.hibernate.dialect.storage_engine=innodb
spring.datasource.hikari.jdbc-url=jdbc:h2:mem://localhost/~/testdb;MODE=MYSQL
```

## [1]PostsRepositoryTest

MockMvc를 사용하지않고 @Service나 @Repository를 사용하는 테스는 서블릿 컨테이너를 구동 후 테스트를 해야함으로 **@SpringBootTest어노테이션에 webEnvironment값을 변경**해주어야한다. 그리고 MockMvc객체를 통해서가아닌 TestRestTemplate객체 같은 다른 방식으로 테스트를 해야한다. 이러한 테스트를 **서블릿 컨테이너 테스트**라고 한다.

```java
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest{
    @Autowired
    PostsRepository postsRepository; //1
    @After //2
    public void cleanup(){
        postsRepository.deleteAll();
    }
    
    @Test
    public void 게시글저장_불러오기(){
        //given //3
        String title = "테스트 게시글";
        String content = "테스트 본문";
        postsRepository.save(Posts.builder()
                            .title(title)
                            .content(content)
                            .author("이현종")
                            .build());
        //when //4
        List<Posts> postsList = postsRepository.findAll();
        
        //then //5
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqual(title); //6
        assertThat(posts.getContent()).isEqual(content);
    }
}
```

1. @Autowired
   * 스프링이 관리하는 빈을 등록해준다
   * PostsRepository를 스프링이 인식할수 있도록 빈으로 등록해준다.
2. @After
   * JUnit에서 단위테스트가 끝날때마다 수행되는 메소드를 지정
3. given
   * 테스트를 수행하기 이전의 상태(사전작업)
4. when
   * 사용하가 지정하는 동작 설명
5. then
   * 지정된 동작으로 인해 예상되는 변경사항
6. assertThat
   * assertj라는 테스트 검증 라이브러리의 검증 메소드
   * 검증하고 싶은 대상을 메소드 인자로 받는다
   * 메소드 체이닝이 지원되어 isEqualTo와 같이 메소드를 사용할수 있다.
7. isEqualTo
   * assertj의 동등 비교 메소드
   * assertThat에 있는 ㄱ밧과 isEqualTo의 값을 비교해서 같을 때만 성공