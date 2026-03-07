package com.project.demo.Controller;

import com.project.demo.Dto.LoginRequestDto;
import com.project.demo.Dto.LoginResponseDto;
import com.project.demo.Dto.SignupRequestDto;
import com.project.demo.Dto.SignupResponseDto;
import com.project.demo.Service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @Operation(summary="Register a new user" , description="Creates a user account in the system")
    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto>  signup(@RequestBody SignupRequestDto signupRequestDto){
        return ResponseEntity.ok(authService.signup(signupRequestDto));
    }
    @Operation(summary = "User login" , description = "Authenticates user and returns JWT Token")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }
















}
