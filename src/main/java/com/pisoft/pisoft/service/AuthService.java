package com.pisoft.pisoft.service;

import com.pisoft.pisoft.dto.LoginDto;
import com.pisoft.pisoft.dto.LoginResponseDto;
import com.pisoft.pisoft.entity.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService{

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;


    public String login(LoginDto loginDto) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(), loginDto.getPassword()
                )
        );

        Users users = (Users) authenticate.getPrincipal();
        return jwtService.generateToken(users);


    }


    public LoginResponseDto login2(LoginDto loginDto) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(), loginDto.getPassword()
                )
        );

        Users users = (Users) authenticate.getPrincipal();
        String accessToken = jwtService.generateAccessToken(users);
        String refreshToken = jwtService.generateRefreshToken(users);

        return new LoginResponseDto(users.getId() , accessToken , refreshToken);
    }

    public LoginResponseDto generateAccessToken(String refreshToken) {

        Long userIdFromToken = jwtService.generateUserIdFromToken(refreshToken);

        Users users = userService.findById(userIdFromToken);

        String accessToken = jwtService.generateAccessToken(users);

        return new LoginResponseDto(users.getId() , accessToken , refreshToken);

    }
}
