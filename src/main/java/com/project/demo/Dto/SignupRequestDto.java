package com.project.demo.Dto;

import lombok.Data;

@Data
public class SignupRequestDto {
    private String email;
    private String password;
    private String name;
}
