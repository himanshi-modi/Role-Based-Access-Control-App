package com.project.demo.Dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
    private String name;
}
