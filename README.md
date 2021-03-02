# Spring-Security-Demo

## Spring.initailizer로 추가한 의존성 목록
- Spring Security
- Spring Data Jpa
- Spring Data jdbc
- Spring Web
- Lombok

## datasource를 위한 application.properties 설정
```yaml
spring.jpa.database=mysql
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/{YourDBSchemaName}?serverTimezone=Asia/Seoul&characterEncoding=UTF-8
spring.datasource.username={YourDBUsername}
spring.datasource.password={YourDBPassword}
```
