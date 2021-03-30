package com.example.project.service;

import com.example.project.domain.Account;
import com.example.project.dto.AccountSaveRequestDTO;
import com.example.project.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    public Account saveUser(AccountSaveRequestDTO dto){
        String rawPassword = dto.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        dto.setPassword(encodedPassword);
        return accountRepository.save(dto.toEntity());
    }
}
