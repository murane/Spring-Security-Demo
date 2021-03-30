package com.example.project.service;

import com.example.project.domain.UserRole;
import com.example.project.dto.AccountSaveRequestDTO;
import com.example.project.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;

import static com.example.project.domain.UserRole.USER;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private AccountRepository accountRepository;
    @Test
    void 유저생성(){

    }
    private AccountSaveRequestDTO createDTO(){
        return AccountSaveRequestDTO.builder()
                .userEmail("test@naver.com")
                .userName("testuser")
                .password("testpw1234")
                .userRole(USER)
                .build();
    }
}
