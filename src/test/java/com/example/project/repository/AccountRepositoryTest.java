package com.example.project.repository;

import com.example.project.domain.Account;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
