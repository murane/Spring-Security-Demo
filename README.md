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

## Account 엔티티 추가
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

##UserRole Enum 추가
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

##UserRepository 추가
>project/repository/AccountRepository
```java
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
}
```




##참조 git repo
[https://github.com/alalstjr/Java-spring-boot-security-jwt](https://github.com/alalstjr/Java-spring-boot-security-jwt)