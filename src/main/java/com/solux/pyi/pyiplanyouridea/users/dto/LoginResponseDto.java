package com.solux.pyi.pyiplanyouridea.users.dto;

import com.solux.pyi.pyiplanyouridea.users.domain.Users;
import lombok.Getter;

@Getter
public class LoginResponseDto {

    private Long userUuid;
    private String userId;
    private String password;

    public LoginResponseDto(Users entity) {
        this.userUuid = entity.getUserUuid();
        this.userId = entity.getUserId();
        this.password = entity.getPassword();
    }

}
