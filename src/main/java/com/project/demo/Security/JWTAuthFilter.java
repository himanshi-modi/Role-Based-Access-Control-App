package com.project.demo.Security;


import com.project.demo.Controller.AuthController;
import com.project.demo.Entity.model.User;
import com.project.demo.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {

    private final AuthUtil authUtil;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestHeaderToken=request.getHeader("Authorization");
        if(requestHeaderToken==null || !requestHeaderToken.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        String token = requestHeaderToken.substring(7).trim();

        String email=authUtil.getUsernameFromToken(token);
        if(email!=null || SecurityContextHolder.getContext().getAuthentication()==null){
            User user=userRepository.findByEmail(email).orElseThrow();
            CustomUserDetails userDetails=new CustomUserDetails(user);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(user,null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request,response);

    }
}
