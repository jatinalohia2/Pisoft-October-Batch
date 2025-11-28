package com.pisoft.pisoft.controller;

import com.pisoft.pisoft.advise.ApiResponse;
import com.pisoft.pisoft.dto.LoginDto;
import com.pisoft.pisoft.dto.LoginResponseDto;
import com.pisoft.pisoft.dto.SignUpDto;
import com.pisoft.pisoft.dto.UsersDto;
import com.pisoft.pisoft.exception.ResourceNotFound;
import com.pisoft.pisoft.service.AuthService;
import com.pisoft.pisoft.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Value("${deploy.env}")
    private String env;

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<UsersDto> signUp(@RequestBody SignUpDto signUpDto){
        UsersDto usersDto = userService.signUp(signUpDto);
        return new ResponseEntity<>(usersDto , HttpStatus.CREATED);
    }

    // this is for  generating a simple access token :
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginDto loginDto,
                                                     HttpServletResponse response){
        String token = authService.login(loginDto);

        Cookie cookie = new Cookie("token" , token);
        cookie.setHttpOnly(true);
        cookie.setSecure(env.equals("production")); // https
        cookie.setMaxAge(60 * 60 * 24 * 7); // 7 days cookies alive
        response.addCookie(cookie);

        return ResponseEntity.ok(new ApiResponse<>(token));
    }


    // this is for  generating  access token and refresh token:

    @PostMapping("/login2")
    public ResponseEntity<ApiResponse<LoginResponseDto>> login2(@RequestBody LoginDto loginDto,
                                                     HttpServletResponse response){

        LoginResponseDto loginResponseDto = authService.login2(loginDto);

        Cookie cookie = new Cookie("refreshToken" , loginResponseDto.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(env.equals("production")); // https
        response.addCookie(cookie);
        return ResponseEntity.ok(new ApiResponse<>(loginResponseDto));
    }

    @PostMapping("/refreshToken")
    public  ResponseEntity<ApiResponse<LoginResponseDto>> refreshToken(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();

        String refreshToken = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals("refreshToken"))
                .map(Cookie::getValue)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFound("Refresh Token is not Valid"));

        LoginResponseDto loginResponseDto = authService.generateAccessToken(refreshToken);
        return ResponseEntity.ok(new ApiResponse<>(loginResponseDto));
    }
}
