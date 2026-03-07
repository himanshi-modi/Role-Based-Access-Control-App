package com.project.demo.Service;

import com.project.demo.Dto.LoginRequestDto;
import com.project.demo.Dto.LoginResponseDto;
import com.project.demo.Dto.SignupRequestDto;
import com.project.demo.Dto.SignupResponseDto;
import com.project.demo.Entity.enums.Roles;
import com.project.demo.Entity.model.User;
import com.project.demo.Exception.UserAlreadyExistsException;
import com.project.demo.Security.AuthUtil;
import com.project.demo.Security.CustomUserDetails;
import com.project.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;

    public  SignupResponseDto signup(SignupRequestDto signupRequestDto) {
        if (userRepository.findByEmail(signupRequestDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists with this email!");
        }
        User user=userRepository.save(User.builder()
                .name(signupRequestDto.getName())
                .email(signupRequestDto.getEmail())
                .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                .roles(Roles.USER).build());
        return modelMapper.map(user, SignupResponseDto.class);
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        Authentication authenticationToken=new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(),loginRequestDto.getPassword());
        Authentication authentication=authenticationManager.authenticate(authenticationToken);
        CustomUserDetails customUserDetails= (CustomUserDetails) authentication.getPrincipal();
        User user=customUserDetails.getUser();
        String jwtToken=authUtil.generateJwtAccessToken(user);

       return new LoginResponseDto(user.getId(),jwtToken);


    }
}

