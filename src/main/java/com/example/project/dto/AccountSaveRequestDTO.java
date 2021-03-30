package com.example.project.dto;

import com.example.project.domain.Account;
import com.example.project.domain.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountSaveRequestDTO {
    private String userName;
    private String userEmail;
    private String password;
    private UserRole userRole;

    @Builder
    public AccountSaveRequestDTO(String userEmail, String password, String userName, UserRole userRole){
        this.userEmail=userEmail;
        this.password=password;
        this.userName=userName;
        this.userRole=userRole;
    }

    public Account toEntity(){
        return Account.builder()
                .name(userName)
                .email(userEmail)
                .password(password)
                .userRole(userRole)
                .build();
    }
}
