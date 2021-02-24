springboot시작하기

# Java기초

## *객체 지향 프로그래밍(Object-Oriented Programming)의 특징

1. 캡슐화(Encapsulation)
   -프로그램의 세부 구현을 외부로 드러나지 않도록 특정 클래스 내부로 감추는것
   -접근 제어 지시자를 통해 외부에서 접근을 제어한다.

2. 상속(Inheritance)
   -자식 클래스가 부모클래스의 특성과 기능을 그대로 물려받는 것을 말한다.
   -자식 클래스에서 재 정의를 통해 부모 클래스의 매소드를 사용할 수 있다.

3. 다형성(Polymorphism)
   -하나의 메소드, 클래스가 상황에 따라 다른 의미로 해석될 수 있다.

   -Overloading은 다형성의 가장 대표적인 예

# 1.클래스(class)(Object)를 만드는 기능을 한다.

* 붕어빵 틀(클래스)과 붕어빵(객체)
* 여러가지 필드(Field)와 메소드(Method)를 가진다.
* JVM시작 시 RunTimeDataArea의 Method(Static)Area에 할당된다.

## [1]필드(Field)

```java
public class User{
	private int idx;
    private String name;
    private String part;
}
```



* 필드 = 속성 = 상태 = 멤버 변수 표현하는것.
* 접근 제어 지시자는 private
* Field는 JVM내의 Method(Static) Area에 할당된다.

## [2]메소드(Method)

```java
public class User{
	private int idx;
    private String name;
    private String part;
    
    public void hello(){
        System.out.println("안녕하세요. 저는 "+part+"파트"+name+"입니다.")
    }
}
```

* 객체의 동작, 행위, 기능
* 메소드는 JVM내의 Method(Static) Area에 할당된다.

## [3]생성자(Constructor)

```java
//회원 객체 생성자
//Default 생성자
//매개변수가 없는 생성자
public User(){
    this.idx = 9;
    this.name = "이현종";
    this.part = "Server";
}

//회원 객체 생성자
//매개변수가 있는 생성자
//@param idx  회원 고유 번호
//@param name 이름
//@param part 파트
public User(final int idx,final String name,final String part){
    this.idx = idx;
    this.name = name;
    this.part = part;
}
```

* Instance생성해주는 특수한 매소드
* 값을 반환하지 않는다.
* 생성자의 이름은 클래스의 이름과 동일
* 접근 제어 지시자는 반드시 public

### (1)생성

```java
//Default 생성자로 객체 생성
        final User user1 = new User();
        //매개변수를 통해 객체 생성
        final User user2 = new User(2,"류지훈","기획");
```

1. User타입의 user객체 변수 생성
2. new 키워드를 통해 JVM RunTimeDataArea내의 Heap영역에 Instance를 저장할 메모리를 할당
3. User생성자를 통해 객체를 생성(초기화)
4. 생성자가 종료되면 new연산자는 객체의 **참조 값을 반환**한다.
5. user변수에 User객체의 **참조 값 할당**

## [4]static

* static으로 설정하면 같은 곳의 메모리 주소(동일한 참조 값)만을 가리키기 떄문에 static변수의 값을 공유하게 된다.
* 클래스 변수 라고도 부른다.
* 인스턴스화 하지 않고도 사용이 가능하다.(메모리를 공유하기 떄문)
* 해당 값을 변경하지 못하게 하기 위해서는 final키워드를 함께 사용해준다.

## [5]final

* 해당 Entity가 오직 한 번 할당 될 수 있음을 의미.
* 상속/변경을 금지하는 규제.
* final변수 : 해당 변수가 생성자나 대입 연산자를 통해 **한 번만 초기화 가능함**
* final메소드 : 해당 메소드를 @Override하거나 숨길 수 없음을 의미한다.
* final클래스 : 해당 클래스를 상속할 수 없음을 의미. 상속 계층 구조에서 마지막 클래스를 의미한다.
* 상수를 표현할 떄 static과 같이 사용한다.
* final키워드가 붙으면 해당 객체는 immutable이된다.

## *은닉화

클래스의 Field는 Private로 접근 재어지시자로 지정한 뒤 Getter,Setter를 통해 Filed의 값을 변경, 호출한다.
1.Getter:값을 호출
2.Setter:값을 변경

```java
public int getIdx(){
    return idx;
}
public void setIdx(final int idx){
    this.idx = idx;
}
public String getName(){
    return name;
}
public void setName(final String name){
    this.name = name;
}
public String getPart(){
    return part;
}
public void setPart(final String part){
    this.part = part;
}
```

## *접근 제어 지시자

![image](https://user-images.githubusercontent.com/57162257/106906315-62515f80-6740-11eb-9cf1-390d08005217.png)

# 2.Builder패턴

인스턴스를 생성하고자할때 파라미터를 받는 생성자에서는 정확한 위치와 순서를 고려해서 파라미터값을 넣어주어야한다.  
만약 데이터베이스를 모델링할때 100개의 필드값이 있다면 100개의 파라미터값을 정확한 순서로 집어넣어주어야 된다.

**Builder패턴**

* 생성자에 들어갈 매개변수를 차례대로 받아들이고 모든 매개변수를 받은 뒤에 이 변수들을 통합하여 한번에 사용한다.
* 데이터의 순서에 상관 없이 객체를 만든다.
* 사용자가 봤을 떄 명시적이고 명확하게 이해할 수 있어야 한다.
* 불필요한 생성자를 만들지 않고 객체를 만든다.



```java
public class UserBuilder{
    private int idx;
    private String name;
    private String part;
    
    public UserBuilder setIdx(final int idx){
        this.idx=idx;
        return this;
    }
    public UserBilder setName(final String name){
        this.name = name;
        return this;
    }
    public UserBuilder setPart(final String part){
        this.part = part;
        return this;
    }
    
    //User Builder
    //@return User객체
    public User build(){
        return new User(this.idx, this.name, this.part);
    }//User클래스에서 사용할수 있게 UserBilder클래스의 필드값들을 User클래스의 인스턴스 생성할수 있게 해준다.
}
```

# 2.인터페이스(interface)

인터페이스란 상수(static final)와 추상 메소드(abstract method)의 집합.

생성자를 가질 수 없어서 객체화가 불가능.

상수는 public static final, 추상메소드는 public abstract를 생략가능한데, 이것은 컴파일 시 자동으로 생성해주기 떄문.

인터페이스는 다중상속을 지원, 구현체에 여러개의 인터페이스를 구현할 수 있다.

## *네이밍 규칙

"~할 수 있는" 이라는 의미를 가지므로 **xxxable** 이런 형식으로 짓는다. 또는 **xxxImp1**과 같이 클래스 이름을 짓는다.

```java
public interface userInterface{
}
```



* 클래스들이 구현해야 하는 동작을 지정하는데 사용되는 추상형
* 규약, 규제
* 추상 클래스의 극단적인 경우
* 인터페이스도 상속이 가능하고, 클래스와 달리 다중 상속이 가능하다.
* 객체의 내부 구조를 알 필요 없이 인터페이스의 메소드만 알고 있으면 된다.
* 다형성을 이용한 인터페이스의 구현 객체를 손쉽게 교체할 수 있다.

## [1]상수 필드

```java
public interface Userinterface{
    public static final String DEPT = "컴퓨터공학부";
    int DEPTNO = 320;
}
```

* public static final 생략이 가능하다.

## [2]추상 메소드

```java
public interface Userinterface{
    public abstract void getInfo();
    String getDept();
}
```

* public abstract 생략이 가능하다.

## [3]디폴트 메소드

```java
public interface UserInterface{
    public default void setState(final boolean state){
        if(state){
            System.out.println("휴학중");
        }else{
            System.out.println("재학중");
        }
    }
}
```

* 메소드 내용까지 구현이 가능하다.
* 해당 인터페이스 구현시 디폴트 메소드를 override해서 사용할수 있다.
* public생략이 가능.

## [4]정적 메소드

```java
public interface UserInterface{
    public static void world(){
        System.out.println("World!");
    }
}
```

* public생략이 가능하다.

## *인터페이스 사용 이유

인터페이스를 구현하는 클래스들은 추상메소드를 오버라이딩 해야한다. 즉, 여러 클래스들이 동일한 이름을 갖는 메소드를 클래스에 따라 정의하여 사용하게된다.  결국 인터페이스는 **일종의 양식을 제공**하는 것. 그 양식에 따라 클래스들은 해당하는 항목의 조건에 따라 자유롭게 코드를 정의할수 있다.

개발할때 인터페이스는 **여러 개발자가 프로젝트를 진행할 때 통일성 있는 설계가 가능하게 해준다**. 또한 **구현한 클래스들에 따라서 하나의 인터페이스로 다양한 실행 결과를 얻을 수 있다**.(**다형성**)



# 3.상속(Inheritance)

* 객체지향의 재활용성을 극대화 시킨 프로그래밍 기법, 동시에 객체 지향을 복잡하게 하는 주요 원인.
* 부모 클래스의 내용을 자식 클래스가 물려 받는 것
* 하나의 부모 클래스는 여러 개의 자식 클래스를 가질 수 있지만, 하나의 자식 클래스는 오직 하나의 부모 클래스를 가져야 한다.
* 자식 클래스는 부모 클래스로부터 받은 메소드를 재정의 해서 사용할 수 있다.

```java
public class Student extends Person{
}
```

[예시]

```java
public class Person{
    private String name;
    public Person(final String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}

public class Student extends Person{
    private String department;
    public Student(final String name,final String department){
        //부모클래스의 생성자 사용.
        super(name);
        this.department = department;
    }
    public String getDepartment(){
        return department;
    }
    public void setDepartment(String department){
        this.departmenrt = department;
    }
}

public static void main(String[] args){
    final Student student = new Student('이현종','컴퓨터공학부');
    //부모 클래스의 메소드
    System.out.println(student.getName());//이현종
    //자식 클래스의 메소드
    System.out.println(student.getDepartment());//컴퓨터공학부
}
```



# 4.제네릭(Generic)

* 클래스 내부에서 사용할 데이터 타입을 외부에서 지정하는 기법
* 다양한 타입의 객체들을 다루는 메소드나, 컬렉션 클래스에서 컴파일 시 타입 체크를 해주는 기능.
* 타입 안정성을 제공한다.
* 타입체크와 형변환을 생략할 수 있으므로 코드가 간결해 진다.
* 컴파일 단계에서 오류를 찾을 수 있다.
* 중복 제거가 가능하다.

```java
public class DefaultRes<T>{
}
```

1. 각각의 인스턴스를 생성할 때 <> 사이에 어떤 데이터 타입을 사용했느냐에 따라 다르다.

   ```java
   public class DefaultRes<T>{
       private T responseData;
       public DefaultRes(final T responseData){
           this.responseData = responseData;
       }
   }
   
   DefaultRes<Student> defaultRes1 = new DefaultRes<(student);
   DefaultRes<User> defaultRes2 = new DefaultRes<>(user1);
   DefaultRes<User2> defaultRes3 = new DefaultRes<>(cowalwerPM);
   ```

2. 복수의 제네릭을 사용 가능(어떠한 문자도 사용할 수 있다.)

   ```java
   public class DefaultRes<T,S>{
       private T responseData;
       private S statusCode;
   }
   ```

3. 제네릭으로 올 수 있는 데이터 타입을 특정 클래스의 자식으로 한정할 수도 있다.

   ```java
   public class DefaultRes<T extends Person>{
       private T responseData;
   }
   ```

*제네릭과 인터페이스 활용 예

```java
public interface CRUDService<T>{
    DefaultRes<T> findOne(final int id) throws InternalServerError;
    DefaultRes<T> save(final T t) throws InternalServerError;
    DefaultRes<T> update (final int id, T t) throws InternalServerError;
    DefaultRes<T> delete (final int id) throws InternalServerError;
}
```



# 5.예외(Exception)

* 오류가 발생하지 않는 프로그램은 없다.
* 기능이 많아질수록 오류가 발생할 확률도 높아진다.
* 프로그램을 만든 개발자가 상정한 정상적인 처리에서 벗어나는 경우에 이를 처리하기 위한 방법

```java
try{
    ...
}catch(Exception e){
    //해당 에러 발생시 실행
}finally{
    //무조건실행
}
```

## *사용자 정의 예외

```java
public class InternalServerError extends Exception{
    private static final long serialVersionUID = 1L;
    public InternalServerError(final string msg){
        super(msg);
    }
}
```

예외 활용

```java
if(res.getStatusCode() == StatusCode.SERVICE_UNAVAILABLE){
    throw new InternalServerError("Remote Server Error");
}
```



# 6.어노테이션(annotation)

* Java 5에서 추가된 문법
* Java코드에 마치 주석 처럼 달아 특수한 의미를 부여한다.
* Compile Time, Run Time시에 해석될 수 있다.
* 자바,Spring이 제공해 주는 것도 있고, 사용자가 직접 만들 수도 있다.

## *사용자 정의 어노테이션

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth{
    
}
```

사용자 정의 Annotation(@Auth)

1. @Target : Annotation이 적용할 위치를 선택
   * PACAGE, TYPE, FIELD, METHOD..등
2. @Retention : Java Compile가 Annotation을 다루는 방법을 설정
   * 특정 시점까지 영향을 미치는 지를 결정
   * SOURCE(컴파일 전까지), CLASS(클래스 참조할 때 까지), RUNTIME(JVM에 의해 계속 참조 가능, Reflection사용)

# SpringBoot시작하기

# 7.Spring 소개

![image](https://user-images.githubusercontent.com/57162257/107146943-9de16900-698e-11eb-8fa3-83e0faf0e1f3.png)

1. POJO(Plain Old Java Object) : 평범한 옛날 자바 객체, 자바 객체
2. IOC(Inversion Of Control) : 제어의 역전, 컨테이너는 개발자 대신 객체의 생성부터 소멸까지 책임진다.
3. DI(Dependency Injection) : 의존성 주입
4. AOP(Aspect Oriented Programming) : 관점 지향 프로그래밍
5. PSA(Portable Service Abstraction) : 일관성 있는 서비스 추상화

![image](https://user-images.githubusercontent.com/57162257/107147163-e6e5ed00-698f-11eb-9bc4-c1f3b6167cf2.png)

1. Core : 스프링의 핵심, IoC기능이 구현된 BeanFactory제공
2. Context : 스프링의 기본, 스프링 기반에서 구현된 기능 객체들에 대한 접근 방법을 제공
3. DAO : JDBC에 대한 추상화 계층, 반복적인 코딩을 줄여주고, 트랜잭션 관리 기능도 제공
4. ORM : 객체와 관계 맵핑, ORM제품들을 스프링 기능과 조합해서 사용 가능
5. AOP : 관점 지향 프로그래밍 기능 제공
6. Web, Web MVC : MVC Web Application개발에 필요한 기본 기능 제공.

## [1]구성요소

1. pom.xml

   * Project Object Model
   * Maven이 해당 파일을 토대로 의존성 관리 및 배포까지 모든 것을 관리한다.
   * Spring boot는 pom.xml을 토대로 필요한 라이브러리나 프레임워크의 의존 관계를 설정을 자동으로 해준다.

2. resources

   * 자원 파일 저장
   * static : 웹 정적 리소스 저장 위치, (html,css,js,img)
   * templates : 웹 동적 리소스 저장 위치, (뷰 템플릿 저장 위치, FreeMarker, Groovy,Thymeleaf, JSP)
   * **application.properties** : Springboot의 기본 설정 파일
     spring boot가 Application시작 시 이곳의 설정들을 자동으로 반영해 준다.
     이곳에 DB정보, 서버 포트 번호 등 설정, 이럴 경우 반드시 git에 올리면 안된다.
   * 이곳에 Mapper(SQL)관련 파일이 저장될 예정

3. 프로젝트Application파일

   ![image](https://user-images.githubusercontent.com/57162257/107148087-b3f22800-6994-11eb-9a29-6d1c69d1ba3d.png)

   * @SpringBootApplication = @Configuration + @EnableAutoConfiguration + @ComponentScan
   * @Configuration : 현재 클래스가 스프링의 설정 파일임을 Spring Context에게 알려주는 Annotation
   * @EnableAutoConfiguration : SpringBoot가 설정을 자동으로 하도록 하는 Annotation
   * @ComponentScan : 다른 컴포넌트, 서비스, 설정 등을 찾을 수 있게 도와주는 Annotation

4. ServletInitializer

   ![image](https://user-images.githubusercontent.com/57162257/107148481-a2118480-6996-11eb-882f-6bff3cf57a06.png)

   * packaging을 war로 설정할 시 자동 생성되는 클래스(Jar로 설정시 생성되지 않는다.)
   * WebApplicationInitializer Interface의 구현체
   * Tomcat같은 Servlet Container환경에서 SpringBootApplication이 동작 될 수 있도록 하는 Web Application Copntext를 구성한다는 의미


## [2]MVC

![image](https://user-images.githubusercontent.com/57162257/107182641-789d3b00-6a20-11eb-8bc7-5f4f7221833b.png)

* Model-View-Controller
* 사용자 인터페이스로부터 비즈니스 로직을 분리
* 애플리케이션을 세가지의 역할로 구분한 패턴
* Model : 정보(data), 데이터베이스
* View : 사용자 인터페이스, 프론트 엔드
* Controller : 데이터와 비즈니스 로직 사이의 상호작용 관리
  사용자가 접근한 URL에 따라 요청에 맞는 데이터를 Model로 처리를 위임하고, 데이터를 View에 반영해 사용자에게 알려준다.



![image](https://user-images.githubusercontent.com/57162257/107183353-09284b00-6a22-11eb-8080-e4259a1e9129.png)

1. 클라이언트의 요청에 대한 최초 진입 지점은 DispatcherServlet이 담당. 이 Servlet이 최초 진입 지점으로 다음의 작업을 처리하게 된다.
2. DispatcherServlet은 Spring Bean Definition에 설정되어 있는 Handler Mapping 정보를 참조하여 해당 요청을 처리하기 위한 Controller를 찾는다.
3. DispatcherServlet은 선택된 Controller를 호출하여 클아이언트가 요청한 작업을 처리한다.
4. Controller는 Business Layer와의 통신을 통하여 원하는 작업을 처리한 다음 요청에 대한 성공유무에 따라 ModelAndView인스턴스를 반환한다. ModelAndView클래스에는 UI Layer에서 사용할 Model데이터와 UI Layer로 사용할 View에 대한 정보가 포함되어 있다.
5. DispatcherServlet은 ModelAndView의 View의 이름이 논리적인 View정보이면 ViewResolver를 참조하여 이 논리적인 View정보를 실질적으로 처리해야할 View를 생성하기 된다.
6. DispatcherServlet은 ViewResolver를 통하여 전달된 View에게 ModelAndView를 전달하여 마지막으로 클라이언트에게 원하는 UI를 제공할 수 있도록 한다. 마지막으로 클라이언트에게 UI를 제공할 책임은 View클래스가 담당하게 된다.

## [3]Servlet

* Java를 사용하여 웹 페이지를 동적으로 생성하는 서버측 프로그램을 말한다.
* 웹 서버의 성능을 향상하기 위해 사용되는 자바 클래스의 일종.
* JSP와 비슷한 점이 있지만. JSP는 HTML에 Java코드를 포함하고있지만, Servlet은 Java코드에 HTML을 포함하고있음.
* 외부 요청마다 Thread로 응답한다.
* Java로 구현되기 떄문에 다양한 플랫폼에서 동작한다.

## [4]Web Container(Servlet Container)

* 웹 서버의 컴포넌트 중 하나로 Java Servlet과 상호작용 한다.
* 웹 컨테이너는 Servlet의 생명주기를 관리하고, URL과 특정 Servlet을 Mapping하여 URL요청이 올바른 접근 권한을 갖도록 한다.
* Servlet, JSP, Server Side Code가 포함된 다른 타입의 파일들에 대한 요청을 다룬다.
* Servlet객체를 생성하고, Servlet을 로드, 언로드하며 요청과 응답 객체를 생성, 관리하고 다른 Servlet관리 작업을 수행한다.

## [5]WAS

![image](https://user-images.githubusercontent.com/57162257/107184372-c8313600-6a23-11eb-9d5d-e697e42117d3.png)

1. Web Server로 부터 요청이 들어오면 Container가 이를 안맞게 처리한다.
2. Container는 web.xml(배포 서술자)를 참조하여 해당 서블릿에 대한 스레드를 생성하고 httpservletRequest 및 httpServletResponse객체를 생성하여 전달한다.
3. 다음으로 컨테이너는 서블릿을 호출(service())
4. 호출된 서블릿의 작업을 담당하게 된 스레드(미리 생성된 스레드)는 요청에 따라 doPost(), 또는 doGet()을 호출한다.
5. 호출된 doPost9),doGet()메소드는 생성된 동적 페이지를  Response객체에 실어서 컨테이너에 전달한다.
6. 컨테이너는 전달받은 Response객체를 HttpResponse형태로 전환하여 웹 서버에 전달하고 생성되었던 스레드를 종료하고, HttpservletRequest 및 httpservletResponse객체를 소멸시킨다.

# 8.Spring Presentation Layer

## [1]Controller

### @RestController

* REST를 위한 전용 Controller 기능을 부여하는 Annotation
* @RestController = @Controller + @ResponseBody
* 반환값을 JSON으로 변환해 준다.

### @Controller

* 템플릿을 이용해서 HTML페이지를 렌더링하고 표시해준다

### @ResponseBody 

* 반환값을 JSON으로 변환해 준다.

### @GetMapping

* Get메소드 전용 Controller Annotation
* 리소스를 조회하는 요청에 사용

### @RequestMappgin

* method, value를 구분시켜준다.
* @RequestMapping(method= RequestMethod.GET, value=""); 의 뜻은 httpMethod는 Get이고 URL Mapping은 ""이다.

### @PathVariable

* URL Mapping에 {}문법 추가

* public String getName(@PathVariable(value="name") final String name){ return name }

  만약 URL에 "/name/이현종"으로 들어오면 이현종이 name이라는 Parameter값으로 받아져 name이라는 String타입의 변수로 변환되게된다.

### @RequestParam

* QueryString을 받아올때 사용

* **/part?part=서버** 와 같은 형태

* ```java
  @GetMapping("/info")
  public String getPart(
      @RequestParam(value="name")final String name,
  	@RequestParam(value="type",defailtValue="yb")final String type
  ){
      return name+"이고 "+ type + "입니다."; 
  }
  ```

  value는 queryString의 key값
  defaultValue는 queryString값이 없을 경우의 기본값.
  ex) URL : **localhost:8080/info?name=이현종&type=ob**  => 이현종이고 ob입니다.

### @PostMapping

* Post메소드 전용 Controller Annotation
* 리소스를 생성하는 요청에 사용

### @RequestBody

* requestBody를 parameter로 객체를 받는 Annotation
* http Messsage본문을 자바 객체로 변환해준다(Mapping)
* Spring MVC내의 HttpMessageConverter가 변환을 처리해준다.
* 전송한 객체와 전송 받을 Controller의 객체 타입이 같아야 한다.
* 같지 않으면 값이 자동으로 채워지지 않고 기본값이 들어간다.

#### *Request Body객체 받는 순서

![image](https://user-images.githubusercontent.com/57162257/107192588-27497780-6a31-11eb-825b-bf37bb65abb3.png)

1. Default생성자를 통해 빈 객체가 생성된다.(**빈 생성자 필수!**)
2. 전송 받은 Http Message Body에 생성된 객체의 속성값이 있다면 Set Method를 통해 값이 채워진다.

### @PutMapping

* Put 메소드 전용 Controller Annotation

### @DeleteMapping

* Delet 메소드 전용 Controller Annotation

# 9.SpringBoot App 3가지 배포 방법

1. 내장 Tomcat이용
   1. Jar로 Build
   2. Maven명령어 이용
2. 다른 WAS(Tomcat)이용
   1. War로 Build



# 10.Spring

## [1]Architecture

![image](https://user-images.githubusercontent.com/57162257/107230018-ddc55080-6a61-11eb-80d9-b74754d0b2ac.png)

## [2]Spring IoC(Inversion of Control, 제어의 역전)

* 프로그램의 제어 흐름 구조가 바뀌는것
* 사용자가 객체를 생성하고 소멸시키는 것이 컨테이너가 대신 하게 된다.(제어의 역전)
* 이 제어권이 스프링 Container로 넘어가는 것이 Spring IoC다.
* 제어권이 Container로 넘어 감으로써 DI, AOP가 가능하다.
* 인스턴스의 생성부터 소멸까지의 객체(Bean) 생명주기를 Container가 관리하게 된다.

## [3]Spring DI(Dependency Injection, 의존성 주입)

* 인스턴스를 자신이 아닌 IoC Container에서 생성 후 주입한다.
* 내부적으로 new 키워드를 사용하지 않고 내부적으로 setter나 생성자를 이용한다.
* 기능이 변경 될 때마다 코드를 변경하는 것은 비용이 많이 들게 되므로 가급적 코드의 변화가 적어지도록 프로그램을 작성하기 위해 탄생
* 모듈간 결합도를 낮춰서 유연한 변경을 가능하도록 한다.
* 불 필요한 의존 관계를 없애거나 줄일 수 있다.
* 각 객체를 bean컨테이너로 관리한다.
* IoC를 구현하는 한 가지 방법이 DI이다.

## [4]Spring Container

* 컨테이너는 보통 인스턴스의 생명 주기를 관리하며, 생성된 인스턴스들에게 추가적인 기능을 제공하도록 한다.
* 작성한 코드의 처리과정을 위임 받은 독립적인 존재.
* 적절한 설정만 되어있다면 누구의 도움 없이도 프로그래머가 작성한 코드를 스스로 참조한 뒤 알아서 객체의 생성과 소멸을 컨트롤해준다.
* Spring Container는 IoC를 사용해 어플리케이션을 구성하는 빈/컴포넌트 들을 관리한다.
* Spring Container = IoC Container = DI Container
* Spring Container의 종류
  1. BeanFactory
     * DI의 기본사항을 제공하는 가장 단순한 컨테이너
     * 팩토리 패턴을 구현한 것
     * Bean을 생성하고 분배하는 책임을 지는 클래스
     * Bean의 정의는 즉시 로딩, 하지만 Bean 인스턴스 생성은 Lazy Loading한다.
     * 처음으로 getBean()이 호출된 시점에서야 해당 빈을 생성(Lazy Loading)
  2. ApplicationContext
     * BeanFactory인터페이스를 상속 받은 하위 인터페이스
     * BeanFactory와 유사한 기능을 제공하지만 좀 더 많은 기능을 제공한다.
     * 국제화가 지원되는 텍스트 메시지를 관리해준다.
     * 이미지같은 파일 자원을 로드 할 수 있는 포괄적인 방법을 제공한다.
     * Listener로 등록된 Bean에게 이벤트 발생을 알려준다.
     * Context초기화 시점에서 모든 싱글톤 Bean을 미리 로드한 후 어플리케이션 기동
* Spring Container 생명주기
  1. 생성
  2. 설정
  3. 사용
  4. 종료

## [5]Spring Bean

* 자바 객체
* 스프링 컨테이너에 의해서 만들어지고 관리되면 Spring Bean
* 다양한 Annotation으로 스프링 컨테이너에 Bean을 등록한다.
* 사용자가 직접 컨트롤 하지 않고 컨테이너가 Bean을 주입해준다.

## [6]POJO

* 우리가 매일 사용하는 자바 객체(getter, setter가 있는 그것)
* 스프링을 모두 제거해도 POGO는 작동을 해야 하는 것이 스프링의 철학

## [7]Spring Life Cycle

![image](https://user-images.githubusercontent.com/57162257/107232651-fe42da00-6a64-11eb-9aaa-ea97a1ad9710.png)



# 11.Lombok

* IDEA와 Build도구에 자동으로 연결되는 Java라이브러리
* Model을 만들고 각 멤버 변수와 관련된 Method를 자동으로 만들어준다
* 다양한 Annotation을 사용해 코드가 간결해 진다.
* AnnotationProcessor를 이용해 Compile시점에 코드를 생성해준다.
* 라이브러리를 도입하기 전엔 항상 모든 팀원 간의 합의가 있어야한다.

## [1]@Data

* 모든 멤버 변수의 생성자 및 접근자, toString,equals, hashCode등 모든 메소드 자동 생성.

* 만능 Annotation이지만 사용하지 않는 메소드까지 만들어 주므로 사용시 고민할 필요가 있음.

* Builder클래스까지는 자동으로 만들어주지않음.

  ```java
  @Data
  public class TestData{
      private int userIdx;
      private String name;
      private String email;
  }
  ```

  ![image](https://user-images.githubusercontent.com/57162257/107236102-b756e380-6a68-11eb-8a2b-d920cf75ee0a.png)

  

## [2]@Getter & @Setter

* @Setter : 필드에 대해 자동으로 Setter메소드를 만들어 준다.
* @Getter : 필드에 대해 자동으로 Getter메소드를 만들어 준다.

## [3]@NoArgsConstructor

* Default 생성자 자동 생성
* 같은 기능의 생성자가 이미 존재할 경우 Compile Error

## [4]@AllArgsConstructor

* 모든 필드에 대한 생성자 자동 생성

## [5]@NonNull

* 클래스가 아닌 필드에 붙이는 Annotation
* RunTime시에 Null체크를 통해 NullPointerException발생(실행시켰을떄)
* Compile시에는 Null체크하지 않는다.

## [6]@RequireArgsConstructor

* @NonNull 이나 final 키워드가 붙여진 필드의 생성자 자동 생성

## [7]@Builder

* 자동으로 Builder클래스 및 메소드 생성
  ![image](https://user-images.githubusercontent.com/57162257/107238356-17e72000-6a6b-11eb-8f06-d1846348fe1e.png)

  ![image](https://user-images.githubusercontent.com/57162257/107238669-76ac9980-6a6b-11eb-9beb-3a867f7f3811.png)

  

## [8]@Value

* Immutable Class로 만드는 Annotation

* 모든 필드 값 private final

* Getter메소드는 생성하지만 Setter메소드는 생성하지 않는다.

* 해당 클래스는 더 이상 상속이 불가능.

* AllArgsConstructor자동 생성

* **@NonFinal** : 해당 필드의 final을 선언하지 않을떄 사용

* **@With(AccessLevel.PROTECTED)** : 해당 필드의 값을 변경한 새로운 객체를 만들어준다.

  ![image](https://user-images.githubusercontent.com/57162257/107240016-cc357600-6a6c-11eb-97fc-a4e8d2fc5650.png)

  ![image](https://user-images.githubusercontent.com/57162257/107240051-d5bede00-6a6c-11eb-81de-df7ab17aefd2.png)



## [9]@Slf4j

* 콘솔창에 로그 출력
* 로그 출력으로 System.out.println을 사용하지 말자.

![image](https://user-images.githubusercontent.com/57162257/107240349-27ffff00-6a6d-11eb-95e6-b856bcebbf2f.png)



# 12.Business Logic Layer

* 비즈니스 로직(핵심 기능)을 구현하는 웹 애플리케이션의 중심
* 웹 애플리케이션의 성공은 비즈니스 로직
* 보통 트랜잭션의 단위가 된다.
* 부품화
* 유지 보수의 핵심
* Interface를 상요해 제약을 걸고, 유지 보수의 무게를 좀 더 둔다.
* 중복되는 기능은 가능한 별도의 Interface로 분리 하는 것을 항상 고민한다.

## ACID

* 트랜잭션의 속성
* Atomicity : 원자성, 트랜잭션 내의 모든 처리는 전부 성공하거나, 전부 실패해야한다.
* Consistency : 일관성, 데이터에 일관성이 있어야한다.
* Isolation : 고립성, 서로 다른 트랜잭션끼리 영향을 줄수 없다.
* Durability : 영속성/지속성, 트랜잭션의 결과는 지속되야한다.

## [1]ResponseEntity

* HTTP StatusCode를 함께 전송해주기 위해 사용한다.
* ResponseEntity는 StatusCode, Headers, Body를 설정할수있다.
* 내부적으로 HttpEntity를 상속한다.

![image](https://user-images.githubusercontent.com/57162257/107331879-ca17f980-6af6-11eb-88eb-94a68c3aabb4.png)

## [2]@Service

* Business Logic중 애플리케이션에서 사용할 수 있게 컴포넌트(부품화)한 부분을 Service Layer
* Bean으로 등록해 언제,어디서든 사용할 수 있는 Class(재사용)
* Spring IoC컨테이너에 서비스Bean으로 등록

![image](https://user-images.githubusercontent.com/57162257/107331814-b5d3fc80-6af6-11eb-876d-46e10a49ea1c.png)

UserService인터페이스
![image](https://user-images.githubusercontent.com/57162257/107333689-28de7280-6af9-11eb-88ea-12582f332fa4.png)

UserServiceImpl클래스
![image](https://user-images.githubusercontent.com/57162257/107333772-44497d80-6af9-11eb-85f2-f7feda2e3913.png)

Interface구현체의 장단점.

* Interface 및 구현체 사용할 경우
  * 장점 : 유지보수가 쉬워짐, 좀 더 안전하다(강제성)
  * 단점 : 불 필요한 작업이 많아진다, 설계가 어렵다.
* 사용하지않는 경우
  * 장점 : 불필요한 작업이 줄어든다, Interface에 제약을 받지 않기 떄문에 자유롭게 수정이 가능하다, 개발 속도가 빠르다.
  * 단점 : 같은 기능 다른 구현을 할 경우 비 효율적이다.

# 13.AWS

1. ec2 인스턴스 생성
2. 생성된 ec2인스턴스에 java 설치
   sudo apt install default-jre
   sudo apt install openjdk-11-jre-headless
   sudo apt install openjdk-8-jre-headless
   sudo update-alternatives --config java(8버전이 많이 사용됨으로 해당 명령어로 8버전으로 바꿔준다.)
   java -version
3. maven설치
   sudo apt list maven
   sudo apt-get install maven
   mvn -v
4. Timezon변경
   sudo dpkg-reconfigure tzdata
   Asia -> Seoul
5. 배포[intellij]
   1. 오른쪽 화면의 maven뷰를 열고 LifeCycle-package더블클릭
   2. 터미널에서 mvnw package입력
   3. Build로그에서 Building jar파일명과 위치 확인.
   4. 빌드된 .jar파일위치로 가서 확인
   5. mobaxterm을 통해 ec2서버로 이동한후 .jar파일을 ec2서버에 옮겨준다.(인바운드에 현재 서버 포트를 열여줘야한다.)
   6. java -jar [파일이름].jar 실행시켜주면 서버 배포완료
   7. 무중단 배포를 위해 **nohup java -jar [파일이름].jar**
      ![image](https://user-images.githubusercontent.com/57162257/107357261-30ac1000-6b15-11eb-8bb6-b53e26c04d62.png)
   8. netstat -tnlp로 현재 프로세스 확인
   9. kill PID 로 죽이고 싶은 프로세스 죽이면 끝.

# 14.Database(데이터베이스,DB)

* 체계화된 데이터의 모임이다.
* 여러 사람이 공유하고 사용할 목적으로 통합 관리되는 정보의 집합이다.
* SQL을 사용하는 RDB와 SQL을 사용하지 않는 NoSQL 모델이 있다.
* 데이터베이스의 특징
  * 실시간 접근성
  * 지속적인 변화
  * 동시 공유
  * 내용에 대한 참조
  * 데이터 논리적 독립성

## [1]RDB(Relation Database,관계형 데이터베이스)

* Key-Value들의 간단한 관계를 테이블화 시킨 매우 간단한 원칙의 데이터베이스이다.
* 데이터를 컬럼(Coluimn)과 로우(row)를 이루는 하나 이상의 테이블로 정리한다.
* 각 테이블은 하나의 Entity타입을 대표한다.
* 각 로우는 그 엔티티 종류의 인스턴스를 대표하며 컬럼은 그 인스턴스의 속성이 되는 값들을 대표한다.

![image](https://user-images.githubusercontent.com/57162257/107471324-4a4e6580-6bb0-11eb-8d09-b9b7c75bbdb7.png)

* 테이블(Table) : 같은 속성을 공유하는 튜플의 모임
* 컬럼(Column),속성(Attribute),필드(Field) : 튜플의 이름
* 로우(Row), 튜플(Tuple), 레코드(Recode) : 하나의 항목을 대표하는 데이터.

![image-20210210151152083](C:\Users\leehyunjong\AppData\Roaming\Typora\typora-user-images\image-20210210151152083.png)

* 키(Key) : 테이블의 각 Row에는 저만의 고유 키가 있다. 한 테이블 안의 로우는 다른 테이블들의 Row로 연결이 가능.
* 관계(Relationships) : 테이블 간 둘 다 존재 한다. **1:1**, **1:N**, **N:M** 관계가 있다.
* 트랜잭션(Transacrtion) : DBMS가 효율적이고 정확하게 운용되기 위해서는 ACID트랜잭션을 갖추고 있어야한다.

### 데이터 무결성(Data Integrity)

* 완전한 수명 주기를 거치며 데이터의 정확성과 일관성을 유지하고 보증하는것을 나타냄.
* RDBMS의 중요한 기능
* 무결성 제한의 유형
  * 개체 무결성(Entity Integrity) : 고유 키의 개념과 관련됨.
    개체 무결성은 모든 테이블이 기본 키(Primnary Key)를 가져야 하고, 기본 키로 선택된 컬럼은 고유해야 하며 빈 값은 허용하지 않는다.
  * 참조 무결성(Referential Integrity) : 외래 키(외부 키)의 개념과 관련된다.
    외래 키 값이 특정 테이블의 기본 키 값을 참조하는 것이다.또는 빈 값을 허용한다.
    ex)참조 무결성이 깨지는 경우 : A 테이블을 참조하는 B테이블이 있다. 근데 A테이블에 B테이블의 특정 튜플이 참조하는 튜플이 A테이블에 존재하지 않는다면 참조 무결성이 꺠지는 경우이다.
    * 참조 무결성을 지키기위해 RESTRICTED, CASCADE, SET NULL이라는 개념을 외래 키에 적용할수 있다.
      * **RESTRICTED** : 레코드를 변경 또는 삭제하고자 할 때 해당 레코드를 참조하고 있는 개체가 있다면, 변경 또는 삭제 연산을 취소한다.
      * **CASCADE** : 레코드를 변경 또는 삭제하면, 해당 레코드를 참조하고 있는 개체도 변경 또는 삭제된다.
      * **SET NULL** : 레코드를 변경 또는 삭제하면, 해당 레코드를 참조하고 있는 개체의 값을 NULL로 설정한다.
  * 도메인 무결성(Domain Integrity) : 테이블에 존재하는 필드의 무결성을 보장하기 위한 것.
    필드의 타입, NULL값의 허용 등에 대한 사항을 정의, 올바른 데이터가 입력 되었는지를 확인하는것.
    ex)도메인 무결성이 깨지는 경우 : 주민번호 필드에 알파벳이 입력되는 경우.

## [2]정규화

* RDB설계에서 중복을 최소화하게 데이터를 구조화하는 과정.
* 기본 정규화 : 1NF, 2NF, 3NF. BCNF
* 고급 정규화 : 4NF, 5NF
* 새로운 형태의 데이터가 삽입될 때 테이블 설계를 재구성할 필요성을 감소시킨다.
* 정규화가 잘 되 있다면 기획자가 추가로 요구하는 기능을 추가하는데 무리가 없다.
* 과도한 정규화는 복잡한 DB모델링이 될 수 있다.
* 떄로는 정규화를 하지 않는 것이 성능상에 더 이점일 수 있다.
* DB모델링 및 정규화 작업을 가장 신경써야 한다.
* 효과
  * 보다 간단한 연산자에 의해 효과적인 연산이 가능해지도록한다.
  * 삽입 이상, 삭제 이상, 갱신 이상의 문제가 발생하지 않도록한다.
  * 새로운 형태의 데이터가 삽입될때 테이블 설계를 재구성할 필요성을 감소시킨다.
  * 자료의 저장 공간을 최소화한다.
  * 자료의 불일치를 최소화한다.
  * 자료 구조를 안정화시킨다.

### *상태이상

* 테이블 내 데이터의 중복과 종속으로 인해 발생되는 오류
* 여러 가지 사실을 하나의 테이블로 표현하기 떄문에 발생
* **삭제 이상** : 원하지 않는 자료가 삭제되거나, 원하는 자료가 삭제되지 않는 이상
* **삽입 이상** : 원하지 않는 자료가 삽입되거나, 원하는 자료가 삽입되지 않는 이상
* **갱신 이상** : 데이터를 조회할 때 데이터가 모호해지거나 일관성이 없어지는 이상



### 1정규화

![image](https://user-images.githubusercontent.com/57162257/107476886-17a96a80-6bba-11eb-9868-d060556f3735.png)

### 2정규화

![image](https://user-images.githubusercontent.com/57162257/107476918-2b54d100-6bba-11eb-9e56-ced4c74a8b85.png)

### 3정규화

![image](https://user-images.githubusercontent.com/57162257/107477019-5fc88d00-6bba-11eb-9636-3b474ca7c370.png)

### BCNF

![image](https://user-images.githubusercontent.com/57162257/107477044-6b1bb880-6bba-11eb-9e4b-fc7645ecc046.png)

## [3]MySQL

### DDL(Data Definition Language)

* 데이터 정의어
* 테이블과 같은 데이터 구조를 정의하는데 사용되는 명령어
* CREATE, ALTER, DROP, RENAME, TRUNCATE
  * CREATE : 테이블 생성
  * ALTER : 테이블 수정
  * DROP : 테이블 삭제

### DML(Data Manipulation Language)

* 데이터 조작어
* SELECT, INSERT, UPDATE, DELETE
  * SELECT : 데이터 조회
    JOIN : 테이블간의 데이터 중에 조건이 맞는 데이터를 가상테이블을 생성해서 붙여준다. 
  * INSERT : 데이터 삽입
  * UPDATE : 데이터 수정
  * DELETE : 데이터 삭제

## [4]My Batis

* Java Persistence Framework의 하나
* XML이나 Annotation을 사용해서 Stored Procedure나 SQL문으로 객체들을 연결시킨다.
* JDBC로 처리하는 상당 부분의 코드와 파라미터 설정 및 결과 매핑을 대신해준다.
* SQL을 명시해 줘야 하기 때문에 ORM으로 보기 힘들다.
* 동적 SQL을 처리할수 있다.

## [5]@Mapper

* Spring IoC컨테이너에 서비스 Bean으로 등록
* 해당 인터페이스에 등록된 SQL Annotation을 토대로 실제 SQL문을 실행시켜 준다.
* 3 이상 버전부터 mapper.xml 대신 interface클래스의 Annotation을 통해 SQL을 사용할 수 있다.
* 가급적 SQL키워드는 대문자, 나머지는 소문자로 작성하는것이 가독성에 좋다.
* 서비스(@Service)와 사용법이 같다.

### @Select

* 조회 Annotation
* #{value}로 동적 바인딩을 처리하고 @Param("value")로 값을 명시한다.

![image](https://user-images.githubusercontent.com/57162257/107490041-b212a980-6bcc-11eb-858b-f27ce32103b7.png)

### @Insert

* 데이터 삽입 Annotation
* 반환 값으로 AI값을 받아오고 싶으면 @Options Annotation을 이용해 AI키를 명시해 준다.

![image](https://user-images.githubusercontent.com/57162257/107490582-6a405200-6bcd-11eb-8c99-711a37b3a7b2.png)

### @Update & Delete

* 수정,삭제 Annotation

![image](https://user-images.githubusercontent.com/57162257/107490923-db800500-6bcd-11eb-9dc2-4d8dd2fce054.png)

# SpringBoot Architecture

![image](https://user-images.githubusercontent.com/57162257/107491511-97d9cb00-6bce-11eb-8f4c-03ea06849915.png)

# MySQL 연동

1. Dependency - Lombok, web, MySQL 설치

2. application.properties에서

   ```java
   #mysql
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.datasource.url=jdbc:mysql://DB주소:포트/스키마?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC
   spring.datasource.username=DB계정
   spring.datasource.password=DB비밀번호
   
   spring.jpa.hibernate.ddl-auto=옵션
   spring.jpa.generate-ddl = boolean
   spring.jpa.show.sql = boolean
   logging.level.org.sopt.seminar4.mapper=TRACE
   ```

3. spring.jpa.hibernate.ddl-auto에서 create, create-drop, update, validate, none 옵션을 설정할수 있다.

   * **create** : JPA가 DB와 상호작용할 떄 기존에 있던 스키마를 삭제하고 새로 만드는 것을 뜻함
   * **create-drop** : JPA종료 시점에 기존에 있었던 테이블을 삭제한다.
   * **update** : JPA에 의해 변경된 부분만 반영한다.
   * **validate** : 엔티티와 테이블이 정상 매핑되어 있는지만 검증.
   * **none** : 초기화 동작을 사용하지 않습니다.  

   spring.jpa.generate-dll : spring.jpa.hibernate.dll-auto 옵션을 사용할것인지 결정하는 프로퍼티, 기본적으로 false로 되어있음.

   spring.jpa.show-sql : JPA가 생성한 SQL문을 보여줄 지에 대한 여부를 알려주는 프로퍼티

4. .gitignore에는 

   ```java
   application.propertiese
   ```

   를 꼭 설정해주어야한다.

# 15.Spring Data JPA

- 객체와 관계형 데이터베이스를 매핑한다.
- 객체와 테이블을 Mapping하기 떄문에 SQL을 직접 날리는 것이 아니라 마치 Java에서 라이브러리/메소드 사용하듯이 사용하면 된다.
- Hibernate등이 있다.
- 장점
  1. 객체 지향적인 코드로 인해 더 직관적이고 비즈니스 로직에 집중할 수 있게 도와준다.
  2. 직접SQL문을 작성할 필요가 없다.
  3. 재사용 및 유지 보수의 편리성이 증가한다.
  4. DBMS에 대한 종속성이 줄어든다.
- 단점
  1. 모든 쿼리를 ORM으로만 작성하기엔 쿼리가 복잡해지면 사용하기 어렵다.
  2. 많은 수의 레코드를 찾은 빈도로 벌크 수행을 한다.
  3. 프로시저가 많은 시스템에선 ORM의 객체 지향적인 장점을 활용하기 어렵다.

## [1]JPA(Java Persistence API)

* Java진영의 ORM표준 기술.
* 가장 많이 사용하는 것은 Hibernate기반으로 만들어 진 것이다.
* 개발자가 직접 SQL을 작성하는 것이 아니라 JPA가 제공하는 API를 사용하면 된다.
* 실행 시점에 자동으로 SQL을 만들어서 실행한다.
* 객체 모델링과 관계형 데이터베이스 사이의 차이점을 해결해 준다.
* 실행 시점에 자동으로 SQL을 만들어서 실행한다. SQL을 직접 작성하는 것이 아니라면 어떤 SQL이 실행될 것인지만 생각하면된다.
* 조회된 결과 역시 자동으로 객체에 매핑해준다.
* 직접 SQL을 작성하게 할 수도 있다.
* 데이터베이스에 종속적이지 않아 쉽게 데이터베이스를 교체할수 있다.
* 테이블 내용이 변경되어도 SQL을 수정할 필요가 없다.
* Mybatis는 SQL Mapper이다.
* **Entity Manager, Persistence Context가 기본적인 구성요소이다.**
* **Entity Manager가 Entity를 저장,조회,수정,삭제하는 일을 관리하고 처리한다.**
* **이러한 작업을 할 때 Persistence Context을 이용한다.**
* **Persistence Context는 Entity Manager를 통해서만 접근이 가능하다.**

## [2]Spring Data JPA

* Spring Framework에서 JPA를 편리하게 사용할 수 있도록 지원하는 프로젝트
* CRUD를 처리하기 위한 공통 인터페이스를 제공한다.
* Interface만 작성하면 Runtime시에 Spring Data JPA가 구현 객체를 동적으로 생성해 주입해준다.
* 직접 작성한 Interface는 메소드 이름을 분석해서 JPQL을 실행한다.
* Mybatis Mapper와 동시에 사용이 가능하다(같은 DB Schema를 사용한다는 전제 하에)

### 연결

[**application.properties**]
![image](https://user-images.githubusercontent.com/57162257/107504434-a0d29880-6bde-11eb-9f24-d673d969abbb.png)

* JPA strategy전략
  * SpringPhysicalNamingStrategy : camelCase를 undeer_score형태로 변경
  * PhysicalNamingStrategyStandardImpl: 변수 이름을 그대로 사용

[**pom.xml**]
![image](https://user-images.githubusercontent.com/57162257/107504450-a92ad380-6bde-11eb-9e1c-58f9c3bb4ae9.png)

## [3]구조

## **domain** 

테이블과 매핑할 객체를 선언해놓음

### @Entity

* 해당 Class를 DB의 테이블과 매핑한다고 명시, 이렇게 명시한 Class를 **Entity Class**라고한다.
* Spring Data JPA를 사용한다면 꼭 명시해야 한다.

### @Table

* Entity Class에 매핑할 테이블 정보를 알려준다고 명시
* name속성을 이용해 테이블 이름을 명시할 수 있다.

### @Id

* 테이블의 기본키와 매핑한다고 알려주는 Annotation

### @Column

* 테이블의 컬럼과 매핑한다고 알려주는 Annotation
* name속성을 이용해 컬럼 이름을 명시할 수 있다.
* Annotation을 명시하지 않으면 Class의 필드명으로 컬럼을 매핑한다.

### @Enumerated

* Enum타입을 매핑할 때 사용한다.

### @Temporal

* 날짜 타입을 매핑할 때 사용하는 Annotation이다.
* TemporalType속성의 값으로 DATE,TIME,TIMESTAMP세가지를 사용할 수 있다.

### @Transient

* 해당 필드를 매핑 하고 싶지 않을 때 명시하는 Annotation
* 해당 필드를 조회하지 않고, 저장도 하지 않는다.



## repository

```java
public interface ItemRepository extends JpaRepository<Item,Integer>{}
```

* interface만 만들어 놓으면 JPA가 알아서 구현체를 만들어 실행시켜준다.

* DB의 테이블을 Entity의 개념으로 사용한다.

* JpaRepository를 상속받아야하고, Generic의 첫번째 인자로는 Entity, 두번째 인자는 Entity의 id컬럼 타임.

* JpaRepository에는 기본 메소드들(CRUD)이 구현되있음.

* ORM메소드를 사용자선언해줄수 있다.
  ex)

  ```java
  public interface ItemRepository extends JpaRepository<Item,Integer>{
      Optional<Item> findById(int id); //객체 하나
      Iterable<Item> findByPart(String part); //list
  }
  ```



# 16.S3

**파일업로드 시나리오**

- DB에는 진짜 파일이 아닌 파일이 저장된 S3의 주소/경로/url을 저장한다.
- File Upload Service & S3 Service를 이용해 파일을 S3에 저장하고, 파일이 저장된 경로를 DB에 저장한다.
- DB에는 파일이 저장된 경로를 저장하고, 이를 클라이언트에게 반환한다.
- Node의 S3 module은 반환값으로 자동으로 파일이 저장된 URL을 반환 값으로 줬지만, Spring은 직접URL을 만들어야한다.(버킷 주소+파일 이름 형식.)

1. 클라이언트에게 Multipart-form/data 형식으로 파일을 전송 받는다. 이떄 파일의 데이터타입은 MultipartFile이다.
2. FileUploadService/S3Service를 통해 파일을 S3에 업로드하고, 파일이 저장된 URL을 DB에 저장한다.
3. 클라이언트가 파일을 요청 시 파일이 아닌 파일이 저장된 경로를 반환한다.
4. 따라서 클라이언트로부터 데이터를 받을 때는 MultipartFile데이터 타입으로 받지만, 반환할떈 String타입으로 반환.

1. **[application.propertiese]**

   ```java
   #AWS S3
   cloud.aws.credentials.accessKey=엑세스키
   cloud.aws.credentials.secretKey=비밀엑세스키
   cloud.aws.stack.auto=false
   
   cloud.aws.s3.bucket=버킷이름
   cloud.aws.region.static=ap-northeast-2
   
   #AWS S3 Bucket URL
   cloud.aws.s3.bucket.url=https://버킷이름.s3.ap-northeast-2.amazonaws.com/
   ```

2. **[api/controller]**

   ```java
   @PostMapping("")
   public ResponseEntity signup(SignUpReq signupReq, @RequestPart(value="profile",required=false)final MultipartFile profile){
       try{
           if(profile != null){signupReq.setProfile(profile)};
           return new ResponseEntity(userService.save(signupReq),HttpStatus.OK);
       }catch(Excetopn e){
           log.error(e.getMessage());
           return new ResponseEntity(FAIL_DEFAULT_RES,HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }
   ```

   * SugnUpReq signupReq 처럼 **아무런 Annotation을 명시하지않고 객체로 받으면 form-data로 받게 된다.**
   * @RequestPart Annotation을 통해 Multipart중에서 profile 키 값의 파일을 MultipartFile타입의 profile객체로 받는다.(formdata로 사진을 보내면 MultipartFile타입의 profile객체로 받는다.)

   ***전송 타입**(Content-type)

   * Application/json

     ```java
     @PostMapping("test")
     public String test(@RequestBody final SignUpReq signUpReq){
         return signUpReq.toString();
     }
     ```

     * @RequestBody는 Content-type이 Appliocation/json인 body만 받는다.
     * 프론트엔드는 json타입으로 body객체를 보내주어야한다.
     * 아무 Annotation을 붙이지않는다면 x-www-form-urlencoded전송 타입으로 받는다.
     * 따라서 사전에 프론트엔드와 정확히 협의하고 가야한다.
     * 가능한 Application/json을 사용하자.

   * Application/x-www-form-urlencoded

     ```java
     @PostMapping("test")
     public String test(final SignUpReq signUpReq){
         return signUpReq,toString();
     }
     ```

     * key&value형식으로 들어옴.
     * Servlet Container는 Content-type이 x-www-form-urlencoded이면 request의 body를 읽어 Map형태로 변환
     * body를 인코딩해서 사용해야 하는데, node.js의 request라이브러리는 이 작업을 내부적으로 처리해서 두 전송타입을 구분하지 않아도 된다.

3. **[service/UserService]**

   ```java
   @Transactional
   public DefaultRes save(SignUpReq signUpReq){
       try{
           if(signUpReq.getProfile() != null)
               signUpReq.setProfileUrl(s3FileUploadService.upload(signUpReq.getProfile()));
           userMapper.save(signUpReq);
           return DefaultRes.res(StatusCode.CREATED,ResponseMessage.CREATED_USER);
       }catch(Excetion e){
           TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
           return DefaultRes.res(StatusCode.DB_ERROR,ResponseMessage.DB_ERROR);
       }
   }
   ```

4. **[mapper/UserMapper]**

   ```java
   @Insert("INSERT INTO user(name,part,prfileUrl) VALUES(#{signUpReq.name),#{signUpReq.part),#{signUpReq.profileUrl}}})")
   int save(@Param("signUpReq")final SignUpReq signUpReq)
   ```

   

# 17.Authorization & Authentication

## **Authorization** 

* 권한부여
* 해당 자원에 대해서 사용자가 그 자원을 사용할 권한이 있는지 체크하는 권한 체크 과정

## Authentication

* 인증과정
* 사용자가 서비스를 사용하는 것이 가능하는지를 확인하는 절차.

## Cookie & Session & Token

* **Cookie**

  * 웹브라우저 Local에 저장되는 Key와 Value가 들어있는 작은데이터 파일이다.
  * 이름,값,만료 날짜, 경로 정보가 들어있다.
  * 일정 시간동안 데이터 저장 가능.
  * 클라이언트의 상태 정보를 Local에 저장했다가 참조한다.
  * 쿠키는 사용자가 따로 요청하지않아도 브라우저가 요청시에 Header에 넣어서 자동으로 서버에 전송
  * [시나리오]
    1. 브라우저에서 웹 페이지 접속
    2. 클아이언트가 요청합 웹 파이지를 받으면서 쿠키를 클라이언트(Local Storage)에 저장
    3. 클라이언트가 재요청시 요청과 함께 쿠키 값도 전송
    4. 지속적으로 로그인 정보를 가지고 있는 것처럼 사용한다.

* **Session**

  * 일정 시간동안 같은 브라우저로 들어오는 일련의 요구를 하나의 상태로 보고 그 상태를 유지하는 기술.
  * 웹 브라우저를 통해 웹 서버에 접속한 이후 브라우저를 종료할 떄 까지 유지되는 상태
  * 클라이언트가 요청을 보내면 Servlet Container가 클라이언트에게 유일한 세션ID를 부여.
  * 세션은 서버 메모리에 저장됨. 서버가 리셋되면 세션데이터 역시 사라짐.
  * 세션을 구분하기 위해 ID가 필요하고 쿠키에 ID만 저장해놓음
  * [시나리오]
    * 클라이언트가 서버에 접속 시 세션ID를 발급한다.
    * 서버에서는 클라이언트로 발급해준 세션 ID를 쿠키를 사용해 저장
    * 클라이언트는 다시 접속할 때 이 쿠키를 이용해 세션 ID값을 서버에 전달
    * 서버는 이 세션 ID값으로 클라이언트를 구분.

* **Token**

  * 상태를 유지하지 않음
  * 사용자의 인증 정보를 서버네 세션,쿠키에 담아두지 않는다.
  * 인증 정보를 다른 애플리케이션에 전달할 수 있다(OAuth)
  * 서버 기반 인증의 문제점 - 세션,확정성 CORS의 문제점을 보완할 수 있다.

* **JWT**

  * 토큰 기반 인증 시스템의 구현체
  * JSON객체 사용
  * 가볍고 자가수용적
  * 쉽게 HTTP헤더, URL파라미터 등으로 전달될 수 있다.

  ![image](https://user-images.githubusercontent.com/57162257/107638151-879b1c00-6cb2-11eb-8ecf-17815bb0a2a9.png)

  * 헤더 : 토큰의 타입(JWT), 해싱 알고리즘 명시(HMAC SHA256)
  * 내용 : 실제 정보(claim)가 들어있다.
  * 서명 : 토큰의 유효성 검증을 위해 서명 작업을 거친다. 헤더의 인코딩 값과 내용의 인코딩 값을 합친 후 비밀키로 해쉬를 하여 생성한다.



## [1]로그인 구현

[**pom.xml**]
![image](https://user-images.githubusercontent.com/57162257/107638862-7c94bb80-6cb3-11eb-9daa-b0d238f8f763.png)

1. LoginController-login()
2. authService-login()
3. userMapper-findByNameAndPassword()
4. 사용자가 디비에존재하면 JwtService-create() //토큰생성
   사용자가 디비에 존재하지않으면 실패DefaultRes

## [2]JWT연결

[pom.xml]

```
<dependency>
            <groupId>com.auth0</groupId>
            <artifactId>java-jwt</artifactId>
            <version>3.12.1</version>
        </dependency>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.6</version>
        </dependency>
```

1. [loginController] : 로그인 정보를 body를 통해 받는다.
2. [authService] : 등록된 정보인지 확인
3. [JwtService] : 등록된 정보라면 JwtService의 **create메소드**를 통해 토큰값 반환(토큰생성빌더 -> 토큰옵션(토큰 생성자, payload작성, 토큰시간 등)작성 -> 토큰 해싱)
4. [JwtService] : 반환된 토큰을 TokenRes라는 클래스의 객체에 담아서 반환해준다.
5. 클라이언트에서 res된 token값을 확인한다.

***Postman**

* Header에 Authorization에 jwt토큰값을 넣어준다.





## [3]Spring AOP(Aspect Oriented Programming,관점 지향 프로그래밍)

**AOP는 관점지향 프로그래밍으로 기능을 핵심 비즈니스 기능과 공통 기능으로 구분하고, 공통 기능을 개발자의 코드 밖에서 필요한 시점에 적용하는 프로그래밍 방법** 

* 애플리케이션 전체에 걸쳐 사용되는 기능들을 재사용하도록 지원하는 것이다.
* 가로(횡단)영역의 공통된 부분을 잘라냈다고 해서 크로스 컷팅(Cross-Cutting)이라고도 불린다.
* 로깅,트랜잭션,보안 등 사용
* 로직 주입
* 프록시 패턴과 유사하다.

### 순서

1. Client에서 Header에 token이 포함된 요청이 들어온다.

2. Controller의 API에서 **@Auth**어노테이션에 의해서 token 유효성 검사를 실시한다.

   ```java
   //메소드에 적용
   @Target(ElementType.METHOD)
   //런타임시까지 참조 가능
   @Retention(RetentionPolicy.RUNTIME)
   //Java Doc에도 표시
   @Documented
   //상속가능
   @Inherited
   public @interface Auth {
   }
   ```

   

3. @Auth어노테이션이 실제 실행하는 기능은 **AuthAspect클래스**이다.

   ```java
   @Component
   @Aspect //@Aspect어노테이션을 적용해줌으로써 aspect사용가능.
   public class AuthAspect{
       ...
   }
   ```

   

   ```java
   //항상 @annotation 패키지 이름을 실제 사용할 annotation 경로로 맞춰줘야 한다.
   //Auth어노테이션에 사용된 곳에서 토큰 유효성검사 실시.
   //@Around 어노테이션을 사용했으므로 유효성검사가 끝내고 pjp.proceed()가 반환되고 @Around는 종료된다.
       @Around("@annotation(org.sopt.seminar4.utils.auth.Auth)")
       public Object around(final ProceedingJoinPoint pjp) throws Throwable {
           final String jwt = httpServletRequest.getHeader(AUTHORIZATION);
           //토큰 존재 여부 확인
           if (jwt == null) return RES_RESPONSE_ENTITY;
           //토큰 해독
           final JwtService.Token token = jwtService.decode(jwt);
           //토큰 검사
           if (token == null) {
               return RES_RESPONSE_ENTITY;
           } else {
               final User user = userMapper.findByUserIdx(token.getUser_idx());
               //유효 사용자 검사
               if (user == null) {
                   return RES_RESPONSE_ENTITY;}
               return pjp.proceed(pjp.getArgs());
           }
       }
   ```

   * @Aspect어노테이션에 선언된 클래스의 메소드에 아래 어노테이션을 선언해주면, 특정 함수 실행 전후의 특정처리가 가능해진다.

     * @Pointcut : aspectJ를 적용할 타겟을 정의해준다. 전체 컨트롤러의 함수대상, 특정 어노테이션을 설정한 함수대상, 특정 메소드 대상 등 개발자가 적용하길 원하는 범위를 정의하는 어노테이션
     * @Before : aspectJ를 적용할 타겟 메소드가 실행되기 '전'수행됨
     * @AfterReturning: aspectJ를 적용할 타겟 메소드가 실행된 '후'수행됨(제일 마지막에 수행)
     * @Around : aspectJ를 적용할 타겟 메소드 실행 전, 후 처리를 모두 할 수 있음

     ```java
     import org.aspectj.lang.JoinPoint;
     import org.aspectj.lang.ProceedingJoinPoint;
     import org.aspectj.lang.annotation.*;
     import org.slf4j.Logger;
     import org.slf4j.LoggerFactory;
     import org.springframework.stereotype.Component;
     
     @Aspect
     @Component
     public class LogAopHelperCLS {
     
         private static final Logger LOGGER = LoggerFactory.getLogger(LogAopHelperCLS.class);
     
         /**
          *   @GetMapping 설정된 메소드 또는 클래스 설정
          *   GetMapping 노테이션이 설정된 특정 클래스/메소드에만 AspectJ가 적용됨.
          */
         @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
         public void GetMapping(){ } 
         //위의 Auth어노테이션을 사용하지않고 @Pointcut 어노테이션을 사용해서 GepMapping메소드(특정 함수)를 선언해줄수있다.
     
         /**
          * @param joinPoint
          */
         @Before("GetMapping()")
         public void before(JoinPoint joinPoint) {
             LOGGER.info("=====================AspectJ TEST  : Before Logging Start=====================");
             LOGGER.info("=====================AspectJ TEST  : Before Logging End=====================");
         }
     
         /**
          * @param joinPoint
          * @param result
          */
         @AfterReturning(pointcut = "GetMapping()", returning = "result")
         public void AfterReturning(JoinPoint joinPoint, Object result) {
             LOGGER.info("=====================AspectJ TEST  : AfterReturning Logging Start=====================");
             LOGGER.info("=====================AspectJ TEST  : AfterReturning Logging END=====================");
         }
     
         /**
          *
          * @param joinPoint
          * @return
          * @throws Throwable
          */
         @Around("GetMapping()")
         public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {
             LOGGER.info("=====================AspectJ TEST  : Around Logging Start=====================");
             try {
                 Object result = joinPoint.proceed();
                 LOGGER.info("=====================AspectJ TEST  : Around Logging END=====================");
                 return result;
             }catch (Exception e) {
                 LOGGER.error("=====================AspectJ Around Exception=====================");
                 LOGGER.error(e.toString());
                 return null;
             }
         }
     
     }
     ```

     **[Controller]**

     ![image](https://user-images.githubusercontent.com/57162257/107846044-dcaa6f80-6e23-11eb-88a4-f9e88a3fd101.png)

     

     **[log]**

     ![image](https://user-images.githubusercontent.com/57162257/107846064-17aca300-6e24-11eb-81a4-46a514cec0ba.png)

     @Around시작 -> @Before 시작,종료 -> @Around종료 -> @AfterReturning 시작,종료

     (pjp == joinPoint)

     **@Around의 시작과 종료는 joinPoint.proceed()전후로 나뉜다.**

     **joinPoint.proceed()는 실제 타겟 함수 실행 결과를 처리하고 있다.**

     ```
     proceed()에서 정상적으로 메서드를 실행한 후 리턴 값을 주는데 가로채서 어떤 action을 한 후에 기존 리턴 값을 되돌려 주지 않으면 가로챈 AOP가 결과값을 변경한 것, 지워버린 것과 다름없다.
     즉, pjp.proceed()는 기존 메서드를 실행시켜주는거고 aspect는 실행시킨 기존 메소드의 반환값을 그대로 반환해주어야한다.
     
     출처: https://jeong-pro.tistory.com/171 [기본기를 쌓는 정아마추어 코딩블로그]
     ```

     

# 18.Architecture(아키텍처)

* 시스템이 어떻게 작동하는지를 설명하는 프레임워크, 구조, 뼈대, 골격이다.
* 시스템 구성 및 동작 원리를 나타낸다.
* 소프트웨어 디자인 패턴과 유사하지만 더 큰 범주에 속한다.

## 1.Monolithic Architecture(모놀리식 아키텍쳐)

* 일체형 구조
* 하나의 애플리케이션(서버)안에 모든 로직이 들어가 있다.
* 하나의 뭔가를 수정하려면 전체 애플리케이션(서버)를 다시 빌드하고 배포해야한다.
* 개발이 단순하고 배포 역시 단순하다.
* 애플리케이션(서버) 구조가 커지고, 팀 규모가 성장하면 여러 단점이 발생한다.
* 코드, 프레임워크에 강제 당하게 된다(ex. Spring Framework를 상요한다면 나 역시 Spring Framework를 사용해 개발해야한다.)

![image](https://user-images.githubusercontent.com/57162257/107842115-56cafc00-6e04-11eb-9a51-3306c620acef.png)

## 2.Micro Service Architecture(MSA, 마이크로 서비스 아키텍처)

* 각 컴포넌트를 서비스라는 개념으로 정의한다.
* REST API같은 표준 인터페이스로 그 기능을 외부로 제공한다.
* 쉽게 생ㄱ가해 각각의 서비스별로 서버를 분리하고 따로 개밣 ㅏ는 것이다.
* MSA에서는 URI/API설계가 무척 중요하다
* 애플리케이션 로직을 분리해서 여러 개의 애플리케이션으로 나눠서 배포한다.
* API Gateway를 사용하면 모든 API에 대해 End Point를 통합함으로써 다른 서비스, 클라이언트 간의 통신하는 것에 도움을 준다.
* 개발에 있어서 언어/프레임워크에 구속 받지 않게 된다.
* 설계가 무척 중요하기 때문에 초기 프로젝트 설계 시간이 오래 걸린다.
* 단시간에 개발하기에는 적합한 아키텍처는 아니다.
* 추후 서비스 확장에 유리한 구조이다.

![image](https://user-images.githubusercontent.com/57162257/107842240-f8eae400-6e04-11eb-9ed2-257088e9965d.png)

![image](https://user-images.githubusercontent.com/57162257/107842250-18820c80-6e05-11eb-8a98-f7b3c06f4a61.png)

### *API Gateway

* API Gateway는 프론트엔드 혹은 다른 플랙폼이 들어오는 출입구이다. 오직 API Gateway를 통해서만 정보를 얻는다. 그 내부가 어떤 구조, 어떤 통신, 스키마 설계 등은 중요하지 않다.
* API Gatewway는 각 서비스가 어떤 구성, 어떤 스키마 등을 가졌는지 내용을 알 필요가 없다.
  오직 기능만 알고 있으면 된다. 다시 말해서 각 서비스에선 API Gateway에게 기능만 알려주면 될 정도로 기능이 명확해야한다.
* API Gateway입장에선 내부를 생각하는 것 보단 외부에서 볼 떄 어떤 의미로 나뉘는지가 중요하다. 그렇지 않으면 API Gateway를 쓸 필요가 없다.



# 19.REST API

## 프로젝트단계

1. 프로젝트 기능 확정
2. 개능별 개발 우선순위 부여 및 역할 분배
3. URI설계 & DB설계
4. URI설계를 토대로 API설계
5. 작업시작...
6. 기능 테스트
7. API문서 작성
8. 클라이언트 연동
9. 버그 수정

## [1]URI(Uniform Resource Identifier,통합 자원 식별자)

* 자원을 나타내는 유일일한 주소이자 자원을 식별할 수 있는 문자열이다.
* 하위 개념으로 URL,URN이 있다.
* 어떤 자원의 위치를 의미하고, Method가 그 위치에 대한 행위를 뜻한다.
* 네이밍이 직관적이여야한다.
* 형용사보다는 명사가 이해하기 좋고 복수 형태로 만든다.
* URI설계에 정답은 없지만 항상 일관된 규칙으로 작성해야 혼동하지 않는다.

## [2]URL(Uniform Resource Locator,통합 자원 지시자)

* 네트워크 상에서 해당 자원이 어디 있는지를 알려주기 위한 규약.
* 특정 서버의 한 자원에 대한 구체적인 위치를 나타낸다.
* 127.0.0.1:8080/users?name=이현종 은 URI
* 127.0.0.1:8080/users/이현종/profile.jpg는 URL이면서 URI

## [3]API(Application Programming interface)

* 서버 애플리케이션의 기능을 사용하기 위한 수단/방법
* 프론트 엔드는 다양한 API를 사용해 서버의 기능을 사용한다.

## [4]REST API(REpresentational State Transfer API)

* Resource-URI, Method, Message 3가지 요소로 구성된다.
* 모든 것을,자원,명사로 표현
* Self-Descriptiveness(자체 표현 구조) : REST API메시지만 보고도 이를 쉽게 이해 할 수 있는 자체 표현 구조로 되어있다.
* Stateless(무상태성) : 상태 정보를 저장하지 않고 각 API서버는 들어오는 요청만을 들어오는 메시지로 처리하면 된다.







# *서치

## Array &  List

[https://velog.io/@adam2/Array%EC%99%80-List%EA%B7%B8%EB%A6%AC%EA%B3%A0-Java-List]

### Array

* index로 해당 원소에 접근가능
* 연속된 메모리의 공간으로 이루어짐
* **장점 :** 인덱스를 통한 검색이 용이함, 연속적이므로 메모리 관리가 편하다.
* **단점 :** 크기가 고정되어있기때문에 삭제되면 상태를 빈 공간으로 남겨야함(메모리 낭비), 정적이므로 배열의 크기를 컴파일 이전에 정해주어야함.
* **데이터의 크기가 정해져있고, 추가적인 삽입 삭제가 일어나지 않으며 검색을 필요로 할때 유리**

### List

* 순서가 있는 엘리먼트의 모임으로 배열과는 다르게 빈 엘리먼트는 절대 허용하지않는다.
* 리스트의 index는 몇 번째 데이터인가 정도(순서)의 의미를 가짐
* 포인터를 통한 접근
* **장점 :** 포인터를 통해 다음 데이터의 위치를 가르키고 있어 삽입 삭제의 용이, 동적이므로 크기가 정해져있지 않음, 메모리의 재사용 편리
* **단점 :** 검색기능이 좋지않음, 포인터를 통해 다음 데이터를 가르키므로 추가적인 메모리 공간 발생
* **데이터의 크기가 정해져있지 않고, 삽입 삭제가 많이 일어나며, 검색이 적은 경우**

### ArrayList

* Array와 ArrayList는 인덱스로 객체를 관리한다는 점에서 동일하지만, 크기를 동적으로 늘릴수 있다.
* 설정한 저장 용량을 넘어서 더 많은 객체가 들어오면, 배열 크기를 1.5배로 증가시킴.
* 중간위치의 객체에 변화가있을때 뒤에 있는 객체들의 위치가 한꺼번에 움직이게됨.
* Array는 primitive type(int, byte, char 등)과 object를 담을수 있지만, ArrayList는 object element만 담을 수 있다.
* 길이에 대해 size() 메소드 사용.
* **장점 :** 데이터를 검색할떄 매우 빠름
* **단점 :** 저장 용량을 늘리는데 많은 시간이 소요, 저장된 데이터를 삭제하는 데도 많은 시간이 소요 
* **잦은 원소의 이동, 삭제가 발생할 경우 ArrayList보다 LinkedList를 사용하는 것이 좋음.**
  ![image](https://user-images.githubusercontent.com/57162257/107219035-d8f9a000-6a53-11eb-97b7-2fc7d6107568.png)

### LinkedList

* 노드 간에 연결을 통해서 리스트로 구현된 객체.
* 다음 노드의 위치 정보만 가지고 있으며 인덱스를 가지고 있지 않기 때문에 탐색시, 순차접근만 가능(노드 탐색 시 시간이 많이 소요됨)
* 추가/삭제는 위치정보의 수정만으로 가능하기 떄문에 성능이 좋음.
* **장점 :** 저장 용량을 늘리는 과정이 간단, 저장된 데이터를 삭제하는 과정도 매우 간단.
* **단점 :** 데이터를 검색할 때 매우 느림 

![image](https://user-images.githubusercontent.com/57162257/107219202-18c08780-6a54-11eb-8966-77df1c7b0e6f.png)

![image](https://user-images.githubusercontent.com/57162257/107219277-3130a200-6a54-11eb-8760-db93041755f2.png)

### 메소드

* add : element
* get : index
* set : index,element
* remove : index



## Hash

**Array**는 내부 인덱스를 이용해서 자료의 검색이 한번에 이루어지기 때문에 **빠른 검색속도**, 데이터의 삽입및 삭제시 데이터가 밀리기때문에 **이동하는 시간이 오래걸림**

**List**는 삽입,삭제시 인근 **노드들의 참조값만 수정해줌으로써 빠른처리**, 검색을 할때 **해당 노드를 찾기 위해 처음부터 순회검색을 하기떄문에 데이터가 많을수록 효율이 떨어짐**

**Hash**는 내부적으로 배열을 사용하여 데이터를 저장하기 때문에 **빠른 검색속도**, 특별한 알고리즘을 사용하여 데이터 삽입,삭제시 **데이터를 밀어내는 작업이 필요없고 데이터와 관련된 고유숫자를 만들어 낸뒤 인덱스로 사용.**



![image](https://user-images.githubusercontent.com/57162257/108718731-18e77980-7562-11eb-89f6-66c81c8f06b6.png)

### 해싱(Hashing) 

해싱이랑 해시함수(hash function)를 이용해서 데이터를 해시테이블(hash table)에 저장하고 검색하는 기법

* HashMap

저장은 느리지만 많은 양의 데이터를 검색하는데 뛰어난 성능을보임

- TreeMap

HashMap에 비해 저장이 빠르지만 데이터를 가져올 때 약간 느리다



### [Map인터페이스]

* 키(key)와 값(value)를 하나의 세트로 묶어서 저장하는 컬렉션 클래스를 구현하는 데 사용

* 데이터와 중복된 키와 값을 저장하면, 기존의 값은 없어지고 마지막에 저장된 값이 남게 된다.

### [HashMap]

* 해시맵을 사용하면 데이터 저장은 느리지만 많은 양의 데이터를 검색하는데 있어서 뛰어나다.
* HashMap은 Map을 구현했으므로 키(key)와 값(value)을 묶어서 하나의 데이터(entry)로 저장한다.
* 키와 값이 null을 허용하기 떄문에 데이터가 빠져있어도 문제되지 않음.





## Optional

### 정의

T타입의 객체를 포장해 주는 래퍼 클래스(Wrapper Class)이다. 따라서 Optional 인스턴스는 모든 타입의 참조 변수를 저장할 수 있다.

* **of()**
  null이 아닌 명시된 값을 가지난 Optonal객체를 반환.
  만약 of()메소드를 통해 생성된 Optional객체에 null이 저장되면 NullPointerExceoption예외 발생.
  그래서 참조 변수의 값이 만에 하나 null이 될 가능성이 있다면, ofNullable()메소드를 사용하여 Optional객체를 생성하는 것이 좋다.
* **ofNullable()**
  명시된 값이 null이 아니면 명시된 값을 가지는 Optional객체를 반환하며, 명시된 값이 null이면 비어있는 Optional객체를 반환.

```java
Optional<String> opt = Optional.ofNullalbe("자바 Optional 객체");
System.out.println(opt.get());
//=> 자바 Optional 객체
```

### 접근방법

* **get()**
  get메소드를 통해 Optional객체에 저장된 값에 접근할수 있다.
  만약 Optional객체에 저장된 값이 null이라면, NoSuchElementExceoption예외 발생.
  따라서 get()메소드를 호출하기 전에 isPresent()메소드를 사용하여 Optional객체에 저장된 값이 null인지 아닌지 먼저 확인후 호출하는 것이 좋다.

* **isPrsent()**
  Optional객체에 저장된 값이 있다면 **ture반환**, null을 가지고있다면 **false반환**

  ```java
  Optional<String> opt = Optional.ofNullable("자바 Optional 객체");
  if(opt.isPresent()){
      System.out.println(opt.get());
  }
  //=> 자바 Optional 객체
  
  ```

* **orElse()**
  저장된 값이 존재하면 그 값을 반환하고, 값이 존재하지 않으면 인수로 전달된 값을 반환함.

* **orElseGet()**
  저장된 값이 존재하면 그 값을 반환하고, 값이 존재하지 않으면 인수로 전달된 람다 표현식의 결괏값을 반환함.

* **orElseThrow()**
  저장된 값이 존재하면 그 값을 반환하고, 값이 존재하지 않으면 인수로 전달된 예외를 발생시킴.

```java
Optional<String> opt = Optional.empty(); //Optional를 null로 초기화함

System.out.println(opt.orElse("빈 Optional 객체"));
System.out.println(opt.orElseGet(String::new));
//=> 빈 Optional 객체
```

## Iterable

Colliection을 상속하는 최상위 클래스로 **Literable< T >**로 사용

Iterable의 각 배열을 참조하기 위해선 for문이나 forEach문을 사용해야한다는데 forEach문이 최선의 방법이라고 한다.(javascript에선 map)

### convert iterable to list

```java
Iterable<Item> itemList = itemRepository.findAll();
List<Item> convertList = new arrayList<>();

itemList.forEach(convertList::add);
```



## WebServer

* 웹 서버는 웹 브라우저 클라이언트로부터 HTTP요청을 받아 정적인 컨첸츠(.html .jpeg .css등)를 제공하는 컴퓨터 프프로그램
* WAP을 사용하지않고 WebServer만 사용하면 WAP을 사용하는것보다 더 빠르고 효율적으로 정적인 데이터를 받을수 있다.

## WAS

![image](https://user-images.githubusercontent.com/57162257/107148672-db96bf80-6997-11eb-9232-b8fd10b52084.png)

* WAS(Web Application Service) = Web Server + Web Container
* WAS는 DB조회나 다양한 로직 처리를 요구하는 동적인 컨텐츠를 제공하기 위해 만들어진 Appliocation Server
* 톰캣은 webserver(정적 data처리)에서 넘어온 동적인 페이지를 읽고 프로그래밍을 실행하고 결과를 html로 재구성해서 webServer(apach)로 돌려준다

## TomCat

* Apache소프트웨어 재단에서 개발한 Servlet Container(Web Container)만 있는 Web Application Server이다. = WAS
* Web Server와 연동하여 실행할 수 있는 자바 환경을 제공한다.
* Servlet이나 JSP를 실행하기 위한 Servlet Container을 제공한다.
* 정적 컨텐츠를 로딩 하는데 웹 서버보다 수행 속도가 느리다.



## 영속성(Persistence)

* 데이터를 생성한 프로그램이 종료되더라도 사라지지 않는 데이터의 특성을 말함.
* 영속성을 갖지 않는 데이터는 단지 메모리에서만 존재하기 떄문에 프로그램이 종료되면 모두 잃어버리게 된다. 때문에 파일 시스템,관계형 데이터베이스, 혹은 객체 데이터베이스 등을 활용하여 데이터를 영구적으로 저장하여 영속성을 부여함.

## SQL Mapper

* SQL Mapper는 SQL문장으로 직접 데이터베이스를 다룬다.
* MyBatis, jdbcTemplates 등..

## ORM

* 데이터베이스 객체를 자바 객체로 매핑함으로써 객체 간의 관계를 바탕으로 SQL을 자동으로 생성.
* Persistence(영속성) API라고도 할수있음.
* JPA, Hibernate등

## JDBC(Java Database Connectivity)

* JDBC는 DB에 접근할 수 있도록 Java에서 제공하는 API이다. ㅁ보든 Java Data Access기술의 근간으로 모든 Persistence Framework는 내부적으로 JDBC API를 사용한다.
  ![image](https://user-images.githubusercontent.com/57162257/107179473-e5f99d80-6a19-11eb-8634-0c48f386e8d9.png)

## JPA(Java Persistent API)

![image-20210208142915108](C:\Users\leehyunjong\AppData\Roaming\Typora\typora-user-images\image-20210208142915108.png)

* 자바 ORM기술에 대한 API표준 명세로 Java에서 제공하는 API
* 사용자가 원하는 JPA구현체(Hibernate 등)를 선택해서 사용할수 있다.

## Mybatis

![image-20210208143047774](C:\Users\leehyunjong\AppData\Roaming\Typora\typora-user-images\image-20210208143047774.png)

* 개발자가 지정한 SQL, 저장 프로시저 그리고 몇 가지 고급 매핑을 지원하는 SQL Mapper.
* mybastis의 장점은 SQL에 대한 모든 컨트롤을 하고자 할 때 매우 적합하다, SQL쿼리들의 최적화가 잘되어 있을떄 유용하다.
* 단점은 애플리케이션과 데이터베이스 간의 설계에 대한 모든 조작을 하고자 할 때는 적합하지 않다. 왜냐면 애플리케이션과 데이터베이스의 구조화가 잘 되도록 많은 설정부분을 바꾸어야 하기 때문이다.



# JPA연관관계 매핑

객체를 테이블에 맞추어 데이터 중심으로 모델링하면, 협력 관계를 만들수 없다.

![image](https://user-images.githubusercontent.com/57162257/108024521-123f9a80-7068-11eb-9e93-d16f1ccb1b5b.png)

member를 찾기 위해서 team의 pk값을 가져와 member가 가지고있는 team-Id를 참조하는 방식으로 많은 비용이 들게되고 **객체 지향스럽지 않은 방법**이다.

## [1]단방향 연관관계

![image](https://user-images.githubusercontent.com/57162257/108024658-55017280-7068-11eb-88c8-d5c5ac88f996.png)

* 객체 연관 관계

  - Member객체는 Member.team 필드(멤버 변수)로 Team 객체와 연관관계를 맺음.
  - **Member객체와 Team객체는 단방향 관계.**
  - Member객체는 Member.team필드를 통해 Team을 알수 있지만 Team객체로는 등록된 회원을 알 수 없다.
  - 즉, member->team 조회는 member.getTeam()으로 가능하지만 team->member를 접근하는 필드가 없다.

* 테이블 연관 관계

  - MEMBER 테이블은 TEAM테이블의 TEAM_ID를 외래 키로 TEAM 테이블과 연관관계를 맺는다

  - **MEMBER테이블과 TEAM테이블은 양방향 관계.**

  - MEMBER테이블의 TEAM_ID외래키를 통해 회원과 팀을 조인할수 있고, 반대로 팀과 회원도 조인할수 있다.

  - 예를 들어, MEMBER테이블의 TEAM_ID외래 키 하나로 MEMBER JOIN TEAM과 TEAM JOIN MEMBER 둘다 가능하다는것.

    ```sql
    [회원과 팀을 조인하는 SQL]
    SELECT * FROM MEMBER M JOIN TEAM T ON M.TEAM_ID = T.TEAM_ID
    ```

    ```sql
    [팀과 회원을 조인하는 SQL]
    SELECT * FROM TEAM T JOIN MEMBER M ON T.TEAM_ID = M.TEAM_ID
    ```

* 객체 연관관계와 테이블 연관관계의 가장 큰 차이

  - 참조를 통한 연관관계는 언제나 단방향이다. 객체간에 연관관계를 양방향으로 만들고싶으면 반대쪽에도 필드를 추가해서 참조를 보관해야 한다.
    이렇게 **양쪽에서 서로 참조하는 것을 양방향 연관관계**라고함.
  - 하지만, 정확히 얘기해서는 양방향 관계가 아닌 서로 다른 단방향 관계 2개인것이다.
  - 테이블은 외래 키 하나로 양방향으로 조인할 수 있다.

* 객체 연관관계 vs 테이블 연관관계 정리

  * 객체는 참조(주소)로 연관관계를 맺는다.
  * 테이블은 외래 키로 연관관계를 맺는다.
  * 참조를 사용하는 객체의 연관관계는 단방향이다.
  * 외래키를 사용하는 테이블의 연관관계는 양방향이다(A JOIN B가 가능하면 B JOIN A도 가능하다는 말씀.)

### (1)연관관계 사용

1.Member.Team class

```java
[Member.java]
package org.sopt.seminar8.domain;

import javax.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;
}

```

```java
[Team.java]
package org.sopt.seminar8.domain;

import javax.persistence.*;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}

```

2.연관관계 저장

```java
//팀 저장
Team team = new Team();
team.setName("Chelsea");
em.persist(team);

//멤버 저장
Member member = new Member();
member.setName("아자르");
member.setTeam(team);
em.persist(member);
```

3.참조로 연관관계 조회

```java
//조회
Member findMember = em.find(Member.class, member.getId()); //아자르객체가 반환됨.
//참조를 사용해서 연관관계 조회
Team findTeam = findMember.getTeam(); //Chelsea객체가 반환됨.
```

4.연관관계 수정

```java
//새로운 팀
Team teamB = new Team();
teamB.setName("RealMadrid");
em.persist(teamB);
//회원에게 새로운 팀설정
member.setTeam(teamB); //아자르에게 Team필드에 RealMadrid가 적용됨.
```

#### @JoinColumn 

왜래키를 매핑할때 사용하는 어노테이션

* @JoinColumn 사용시 기본값은 **"필드명"+"_"+"테이블의 기본 키 컬럼명"**
* @JoinColumn어노테이션은 생략이 가능하다. 생략하면 외래 키를 찾을 때 기본값 전략을 사용한다.
* name옵션 : 매핑할 왜래 키 이름



## [2]양방향 연관관계

![image](https://user-images.githubusercontent.com/57162257/108027886-37cfa280-706e-11eb-9662-646c216ad9ed.png)

* 객체 연관관계
  * 회원 -> 팀 (Member.team)
  * 팀 -> 회원 (Team.members)

![image](https://user-images.githubusercontent.com/57162257/108027958-5afa5200-706e-11eb-983a-7a716157453e.png)

* 테이블 연관관계
  * 객체 연관관계에는 Team객체에 members라는 Member객체를 참조하는 필드가 있는데 테이블 연관관계에는 members가 없다. 그이유는 아까 위에서 설명한 듯이 데이터베이스 테이블은 외래 키 하나로 양방향으로 조회할수 있기 떄문이다.
  * MEMBER테이블의 튜플이 소속된 팀을 알고싶다면 TEAM테이블의 TEAM_ID로 MEMBER테이블에 조인하면된다.
  * TEAM테이블의 튜플에 소속된 MEMBER튜플들을 알고 싶으면 MEMBER의 TEAM_ID로 TEAM테이블에 JOIN하면 된다.

### (1)연관관계 사용

1.양방향 관계 매핑

```java
[Member.java]
@Setter
@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;
}
```

```java
[Team.java]
@Setter
@Getter
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //양방향 매핑(with Member)
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<Member>();
}
```

Team클래스에서 팀에 소속된 member객체들을 매핑하기 위해서 @OneToMany매핑 정보를 사용.
@OneToMany의 mappedBy속성은 양방향 매핑일때 사용하는데,**반대쪽 매핑의 필드 이름을 값**으로 주면 된다.
Member클래스의 Team을 참조하는 필드명이 team이므로 mappedBy = "team"을 값으로 준다.

2.일대다 컬렉션 조회

```java
//member1 : (id=1, username="회원1", team="team1")
//member2 : (id=2, username="회원2", team="team1")

Team team = em.find(Team.class, "team1");
List<Member> members = team.getMembers(); //(팀->회원) 객체 그래프 탐색

for(Member member:members){
    System.out.println("member.username = "+ members.getUsername());
}
// === 결과 ===
// member.username = 회원1
// member.username = 회원2
```



### (2)연관관계의 주인

Team의 @OneToMany에 mappedBy속성이 있는 이유

객체 연관관계는 회원->팀 연관관계(단방향), 팀->회원 연관관계(단방향)으로 총 두개의 연관관계를 맺는다.
테이블 연관관계는 회원<->팀 연관관계(양방향)으로 총 한개의 연관관계를 맺는다.

Entitiy를 양방향 연관관계로 설정하면 객체의 참조는 두개인데 외래키 참조는 하나이다. 따라서 **둘 사이에 차이가 발생하게되고 두 객체 연관관계중 하나를 정해서 테이블의 외래키를 관리해야하는데 이것은 연관관계 주인이라고한다.** 



#### -양방향 메핑의규칙 : 연관관계의 주인

* 양방향 매핑을 해야할떄의 규칙은 두 연관관계(단방향 두개) 중 하나를 연관관계의 주인으로 정해야한다.
* 연관관계의 주인만이 데이터베이스 연관관계와 매핑되고 외래 키를 관리(등록, 삭제, 수정) 할 수 있다.
* 반면, 주인이 아닌 쪽은 읽기만 할수 있다.
* **주인은 mappedBy속성을 사용하지않는다.**
* **주인이 아니면 mappedBy속성을 사용해서 속성의 값으로 연관관계의 주인을 지정해야한다.**

#### -연관관계의 주인은 외래 키가 있는 곳

* 연관관계의 주인은 테이블에 외래 키가 있는 곳으로 정해야한다.

* ![image](https://user-images.githubusercontent.com/57162257/108032150-5f763900-7075-11eb-98c1-2b87d21dc507.png)

  위의 코드에서는 Member테이블에 Team의 외래키를 가지고 있으므로 Member.team이 주인이 되게된다.
  그러므로 Team.members는 주인이 아니게 되므로 mappedBy속성을 Member엔티티의 team필드를 가리켜 주인이 아님을 설정한다.

* 데이터베이스 테이블의 다대일, 일대다 관계에서는 항상 **다** 쪽이 외래 키를 가진다. 다 쪽인 @ManyToOne은 항상 연관관계의 주인이 되므로 mappedBy를 설정할수없다. 따라서 @ManyToOne에는 mappedBy속성이 없고 @OneToMany에는 mappedBy속성이 있는것이다.



### (3)양방향 연관관계 저장

위에 설명했던 것처럼 양방향에서 연관관계를 설정해줄때 연관관계의 주인이 외래키를 관리할수 있다.

```java
public void save(){
    //팀1 저장
    Team team1 = new Team("team","팀1");
    em.persist(team1);
    //회원1 저장
    Member member1 = new Member("member1","회원1");
    member1.setTeam(team1); //연관관계 설정 member1 -> team1
    em.persist(member1);
    //회원2 저장
    Member member2 = new Member("member2","회원2");
    member2.setTeam(team2); //연관관계 설정 member2 -> team1
}
```

Member.team필드를 통해서 회원과 팀의 연관관계를 설정하면

![image](https://user-images.githubusercontent.com/57162257/108033013-afa1cb00-7076-11eb-9bcb-0bdb05293c20.png)

TEAM_ID외래 키에 팀의 기본 키 값이 저장되어 있다.

```java
team1.getMembers().add(member1);
team1.getMembers().add(member2);
```

이런 코드가 있어야 할거 같지만 연관관계의 주인이 아니므로 외래 키에 영향을 주지 않는다.

```java
Member member1 = new Member("member1","주인1");
em.persist(member1);

Member member2 = new Member("member2","주인2");
em.persist(member2);

Team team1 = new Team("team1","팀1");
team.getMembers().add(member1);
team.getMembers().add(member2);
em.persist(team1);
```

이런 코드로 저장을 한다면
![image](https://user-images.githubusercontent.com/57162257/108033029-b7fa0600-7076-11eb-9bd9-2de3390622eb.png)

연관관계의 주인인 Member.team에 아무 값도 입력하지 않았기 떄문에, TEAM_ID외래 키의 값도 null로 저장된다.



하지만!!!!!!!!!!!!!!!!!!!!!!!!!!



#### 순수한 객체까지 고려한 양방향 연관관계

를 생각한다면 사실 **객체 관점에서 양쪽 방향에 모두 값을 입력해주는 것이 안전하다.** 양쪽 방향 모두 값을 입력하지 않는다면 JPA를 사용하지 않는 순수한 객체 상태에서 심각한 문제를 발생시킬수 있기때문이다.

```java
public void save(){
    Team team1 = new Team("team1","팀1");
    em.persist(team1);
    
    Member member1 = new Member("member1","회원1");
    member1.setTeam(team1);
    team1.getMembers().add(member1);
    em.persist(member1);
    
    Member member2 = new Member("member2","회원2");
    member2.setTeam(team1);
    team1.getMembers().add(member2);
    em.persist(member2);
}
```

이와 같은 코드를 통해서 JPA를 사용하지 않는 순수한 객체들도 사용가능하고, 테이블의 외래 키도 정상 입력되도록 한다.



#### -연관관계 편의 메서드

양방향 관계는 결국 양쪽 다 신경써줘야한다. member.setTeam()이나 team.getMembers().add()를 각각 호촐해주어야하는데 실수로 하나만 호출해서 양방향이 꺠질수도 있기떄문에 Member클래스의 setTeam()메서드를 수정해준다.

```java
public class Member{
    private Team team;
    public void setTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }
}
```

이렇게 setTeam()을 리펙토링하므로써 **member.setTeam(team)** 하나의 메소드를 통해 양방향을 지킬수 있게된다.



#### -연관관계 편의 메서드 작성 시 주의사항

```java
member.setTeam(team1);
member.setTeam(team2);
Member findMember = team1.getMember(); //회원과 팀은 다대일 관계라 한명의 회원이 여러팀에 들어가있을수 없다. 하지만 team1에 member가 여전히 조회중.
```

team1에서 team2로 변경할때  team1 -> member관계를 제거하지 않았기 떄문에 team1.getMembers()를 실행했을때 member1이 남아있다. 따라서 연관관계를 변경할 떄는 기존 팀이 있으면 기존 팀과의 연관관계를 삭제하는 코드를 추가해야한다.

```java
public class Member{
    private Team team;
    public void setTeam(Team team){
        if(this.team != null){
            this.team.getMembers().remove(this); //team과 연관관계를 매핑하려고하는데 해당 member객체가 이미 다른 team과 연관관계가 맺어져있다면 해당 객체에서 매핑하고있는 team의 members리스트에서 해당 객체를 제거해준다.
        }
        this.team = team;
        team.getMembers().add(this);
    }
}
```

위의 코드처럼 이미 매핑되어있던 team의 members리스트에서 해당 member객체를 제거해주면 team1에서 더이상 해당 memeber를 조회하지 않을것이다.



## [3]다양한 연관관계 매핑

### (1)일대다 단방향

* 대다 단방향은 일대다(1:N)에서 일(1)이 연관관계의 주인이다.
* 일대다 단방향 관계를 매핑할 때는 @JoinColumn을 꼭 사용해야한다.
* **일대다 단방향 매핑보다는 다대일 양방향 매핑을 선호함.**

### (2)일대다 양방향

* **다대일 양방향을 사용하자**

### (3)일대일

* 양쪽이 서로 하나의 관계만 가진다.(회원과 사물함의 관계)

* 일대일관계는 주 테이블이나 대상 테이블 둘 중 어느곳이나 외래키를 가질수 있다.

* 외래 키에 데이터베이스 유니크(UNI)제약 조건이 추가되야 한다.

* 주 테이블에 외래 키

  * 주 객체가 대상 객체를 참조하는 것처럼 주 테이블에 외래 키를 두고 대상 테이블을 참조
  * 외래 키를 참조와 비슷하게 할 수 있어서 객체지향 개발자들이 선호
  * 장점 : 주 테이블이 외래 키를 가지고 있으므로 주 테이블만 확인해도 대상테이블과 연관관계가 있는지 알 수 있다.

* **단방향**
  ![image](https://user-images.githubusercontent.com/57162257/108055680-a4a96380-7093-11eb-9220-7093227fd1e2.png)
  MEMBER가 주 테이블이고 LOCKER는 대상 테이블
  회원은 하나의 Locker만 가지고, Locker는 하나의 회원에 의해서만 사용되는 경우.
  @OneToOne어노테이션을 사용하고, 이 관계는 다대일 단방향(@ManyToOne)과 거의 비슷하다.

* **양방향**
  ![image](https://user-images.githubusercontent.com/57162257/108055758-c0ad0500-7093-11eb-96f7-7399f540006a.png)
  양방향이므로 연관관계에서 주인을 정해주어야하는데, MEMBER테이블이 외래 키를 가지고 있으므로 MEMBER엔티티의 ember.locker가 연관관계의 주인이다.
  따라서 반대 매핑인 Locker.member는 mappedBy를 선언해서 연관관계의 주인이 아니라는것을 설정해주어야한다.

  

* 대상 테이블 외래 키

  * 전통적인 데이터베이스 개발자들은 보통 대상 테이블에 외래 키를 두는 것을 선호
  * 장점 : 테이블 관계를 일대일에서 일대다로 변경할 때 테이블 구조를 그대로 유지할 수 있다.
  * **단방향**
    ![image](https://user-images.githubusercontent.com/57162257/108056302-6f514580-7094-11eb-970d-aa18aeef0319.png)
    위 처럼 일대일 관계 중 대상 테이블에 외래 키가 있는 단방향 관계는 JPA에서 지원하지 않음으로 위 모양으로 매핑할 수 있는 방법은 없다.
    그러므로 단방향 관계를 Locker에서 Member방향으로 수정하거나, 양방향 관계로 만들고 Locker를 연관관계의 주인으로 설정해야한다.
  * **양방향**
    ![image](https://user-images.githubusercontent.com/57162257/108056330-7415f980-7094-11eb-9f67-61c8b0929022.png)
    대상 테이블에 외래키를 두고 싶으면 주 엔티티인 Member엔티티대신에 대상 엔티티안 Locker엔티티를 주인으로 설정하여 Locker테이블의 외래키를 관리하게 한다.

  

  

  ### (4)다대다

  * 실무에서는 안쓴다고 한다.

  

  

  ## [4]임베디드 타입(복합 값 타입)

  새로운 값 타입을 직접 정의해서 사용할 수 있는데, JPA에서는 이것을 임베디드 타입(embedded type)이라 한다.
  중요한 것은 직접 정의한 임베디드 타입도 int,String 처럼 값 타입이라는 것이다.

  * 임베디드 타입을 사용하지 않았을때

    ```java
    @Entity
    public class Member{
        @Id
        @GeneratedValue
        private Long id;
        private String name;
        
        //근무기간
        @Temporal(TemporalType.DATE)
        Date startDate;
        @Temporal(TemporalType.DATE)
        Date endDate;
        
        //집 주소 표현
        private String city;
        private String street;
        private String zipcode;
    }
    ```

    

  이 엔티티를 설명하려면 ```"회원 엔티티는 이름, 근무 시작일, 근무 종료일, 주소 도시,주소 번지, 주소 우편 번호를 가진다."``` 로 설명할수 있다.

  하지만 정보를 장황하게 설명하기 보다는 주요 카테고리로 설명을 하는것이 조금더 효율적일것이다

  ```회원 엔티티는 이름, 근무기간, 집 주소를 가진다.``` 라는 식으로.. 

  엔티티가 상세한 데이터를 그대로 가지고 있다는 것은 객체지향적이지 않으면 응집력만 떨어트리는것이다.

  대신 근무기간, 주소 같은 타입이 있다면 코드는 더 명확해질 것이다.

  * 임베디드 타입을 사용

    ```java
    @Entity
    public class Member{
        @Id
        @GeneratedValue
        private Long id;
        private String name;
        
        @Embedded
        private Period workPeriod; //근무기간
        
        @Embedded
        private Address homeAddress; //집주소
    }
    ```

    ```java
    //근무기간 임베디드 타입
    @Embeddable
    public class Period{
        @Temporal(TemporalType.DATE)
        Date startDate;
        @Temporal(TemporalType.DATE)
        Date endDate;
    }
    ```

    ```java
    //집주소 임베디드 타입
    @Embeddable
    public class Address{
        @Column(name="city")
        private String city;
        private String street;
        private String zipcode;
    }
    ```

  

  ### (1)임베디드 타입 사용하는 방법

  * @Embeddable : 값 타입을 정의하는 곳에 표시
  * @Embedded : 값 타입을 사용하는 곳에 표시
  * 임베디드 타입은 기본 생성자 필수!
  * 임베디드를 사용한 구조
    ![image](https://user-images.githubusercontent.com/57162257/108058539-89405780-7097-11eb-8106-a868e3fedc1a.png)
  * 장점
    * 재사용
    * 높은 응집도

  

  #### **임베디드 타입과 테이블 매핑**

  ![image](https://user-images.githubusercontent.com/57162257/108058946-1a173300-7098-11eb-8170-d343792b9fe0.png)

  * 임베디드 타입은 엔티티의 값일 뿐
  * 값이 속한 엔티티의 테이블에 매핑한다.
  * 베디드 타입 덕분에 객체와 테이블을 아주 세밀하게(fine-grained)매핑하는 것이 가능.
  * 잘 설계한 ORM애플리케이션은 매핑한 테이블의 수보다 클래스의 수가 더 많다.

  

  #### 임베디드 타입과 연관관계

  ```java
  @Entity
  public class Member{
      
      @Embedded
      Address address; //임베디드 타입 포함
      PhoneNumber phoneNumber; //임베디드 타입 포함
  }
  
  @Embeddable
  public class Address{
      String street;
      String city;
      String state;
      @Embedded
      ZipCode zipcode; //임베디드 타입 포함
  }
  
  @Embeddable
  public class Zipcode{
      String zip;
      String plusFour;
  }
  
  @Embeddable
  public class PhoneNumber{
      String areaCode;
      String localNumber;
      
      @ManyToOne
      PhoneServiceProvider provider; //엔티티 참조
  }
  
  @Entity
  public class PhoneServiceProvider{
      @Id
      @GeneratedValue
      String name;
  }
  ```

  

  #### @AttributeOverride : 속성 재정의

  임베디드 타입에 정의한 매핑정보를 재정의하려면 엔티티에 @AttributeOverride를 사용하면 된다.

  예를 들어 회원에게 주소가 하나 더 필요하다면

  ```java
  @Entity
  public class Member{
      @Id
      @GeneratedValue
      private Long id;
      private String name;
      
      @Embedded
      Address homeAddress;
      
      @Embedded
      Address companyAddress;
  }
  ```

  이렇게 추가해줄수 있지만 이때 테이블에 매핑하는 컬럼명(Address)이 중복되는 것이다. 이떄 @AttributeOverrides를 사용해서 매핑정보를 재정의해야한다.

  ```java
  @Entity
  public class Member{
      ...
          
     	@Embedded
      Address homeAddress;
      
      @Embedded
      @AttributeOverrides({
          @AttributeOverride(name="city", column=@Column(name="COMPANY_CITY")),
          @AttributeOverride(name="street", column=@Column(name="COMPANY_STREET")),
          @AttributeOverride(name="zipcode",column=@Column(name="COMPANY_ZIPCODE"))
      })
      Address companyAddress;
  }
  ```

  위의 코드처럼 재정의해준다면 재정의한 이름으로 테이블이 생성된다.

  * ```@AttributeOverrides는 엔티티에 설정해야한다. 임베디드 타입이 임베디드 타입을 가지고 있어도 엔티티에 설정해야한다.```
  * ```임베디드 타입이 null이면 매핑한 컬럼 값은 모두 null이 된다.```

  