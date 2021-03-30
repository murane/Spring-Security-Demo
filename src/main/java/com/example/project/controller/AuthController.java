package com.example.project.controller;

import com.example.project.domain.Account;
import com.example.project.dto.AccountSaveRequestDTO;
import com.example.project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class AuthController {
    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<?> insertUser(@RequestBody AccountSaveRequestDTO dto,
                                        BindingResult result){
        Account newAccount = userService.saveUser(dto);
        return new ResponseEntity<Account>(newAccount, HttpStatus.CREATED);
    }
}
