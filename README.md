# Spring-Security-Demo

## Spring.initailizer로 추가한 의존성 목록
- Spring Security
- Spring Data Jpa
- Spring Data jdbc
- Spring Web
- Lombok

## datasource를 위한 application.properties 설정
```properties
spring.jpa.database=mysql
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/{YourDBSchemaName}?serverTimezone=Asia/Seoul&characterEncoding=UTF-8
spring.datasource.username={YourDBUsername}
spring.datasource.password={YourDBPassword}
```

## Account 엔티티
>project/domain/Account
```java
@Getter
@Entity
@Table(name="ACCOUNT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "ACCOUNT_EMAIL",nullable = false,unique = true)
    private String email;

    @Column(name = "ACCOUNT_PASSWORD",nullable = false)
    private String password;

    @Column(name = "ACCOUNT_ROLE",nullable = false)
    private String userRole;

    @Builder
    public Account(String email, String password, String userRole){
        this.email=email;
        this.password=password;
        this.userRole=userRole;
    }
}
```
```lombok```을 활용하여 Getter를 생성하고 JPA를 위한 기본 생성자를 Protected로 넣어줍니다   
Entity 어노테이션은 엔티티를 표시해야함으로 필요하고, Table명을 ACCOUNT로 지정합니다   
Id생성전략은 ```AUTO, IDENTITY, SEQUENCE, TABLE``` 네가지가 기본이며 디폴트값은 ```AUTO```입니다   
[기본키 전략 참조](https://www.baeldung.com/hibernate-identifiers) ,
[참조 2](https://gmlwjd9405.github.io/2019/08/12/primary-key-mapping.html)   
setter는 객체에 불안정성을 가져올수 있기때문에 지양하고 명확한 메소드를 사용하는것이 좋습니다   
그리고 ```빌더 패턴```을 통해 변수 위치에 관계없는 명확한 객체 생성을 가능하게 했습니다   

##UserRole Enum
>project/domain/UserRole
```java
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");
    
    private String roleName;
    
    UserRole(String roleName){
        this.roleName=roleName;
    }
    public String getKey(){
        return name();
    }
    public String getRoleName(){
        return roleName;
    }
}
```

UserRole을 Enum으로 정의해줍니다   

##AccountRepository
>project/repository/AccountRepository
```java
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
}
```
```JPA```를 활용하면 DAO를 인터페이스 상속만으로 간편하게 생성할 수 있습니다.   
```findall, save```등의 메소드가 기본적으로 생성되고 ```findbyXX```와 같이 정의한 메소드도 확장 가능합니다.
 
##AccountRepositoryTest
>test/../project/repository/AccountRepositoryTest
```java
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;

    //메소드명_케이스_기대값
    @Test
    void findByEmail_맞는값_조회성공(){
        //given
        Account account = Account.builder()
                .email("test@naver.com")
                .password("test1234")
                .userRole("ROLE_USER")
                .build();
        accountRepository.save(account);
        //when
        Account acc = accountRepository.findByEmail("test@naver.com")
                .orElseThrow(() -> new RuntimeException("잘못된이메일입니다."));
        //then

        assertThat(acc.getEmail()).isEqualTo("test@naver.com");
    }
    @Test
    void  findByEmail_잘못된값_조회실패(){
        //given
        Account account = Account.builder()
                .email("test@naver.com")
                .password("test1234")
                .userRole("ROLE_USER")
                .build();
        accountRepository.save(account);
        //when
        //then
        assertThatThrownBy(() -> accountRepository.findByEmail("test1@naver.com")
                .orElseThrow(() -> new RuntimeException("잘못된이메일입니다.")));
    }
}
```
기본으로 생성되는 ```findall, save```등은 제외하고 새로 추가한 ```findByEmail```을 검증해줍니다.   
```DataJpaTest``` 어노테이션을 통해 JPA테스트에 필요한 요소만 테스트가 가능합니다.   
```AssertJ```의 ```assertThat```이 ```Junit```의 ```expected, actual```순서보다 덜 헷갈리고   
가독성이나 메소드가 풍부해서 적극적으로 사용했습니다.   
Replace를 NONE으로 설정해야 embedded DB를 사용하지 않고 설정된 DB를 사용합니다.   
기본적으로 ```DataJpaTest```에서 ```Transactional```을 포함하므로 롤백이 지원됩니다.   


##참조 git repo
[https://github.com/alalstjr/Java-spring-boot-security-jwt](https://github.com/alalstjr/Java-spring-boot-security-jwt)