package com.pisoft.pisoft.controller;

import com.pisoft.pisoft.advise.ApiResponse;
import com.pisoft.pisoft.dto.LoginDto;
import com.pisoft.pisoft.dto.SignUpDto;
import com.pisoft.pisoft.dto.UsersDto;
import com.pisoft.pisoft.service.AuthService;
import com.pisoft.pisoft.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<UsersDto> signUp(@RequestBody SignUpDto signUpDto){
        UsersDto usersDto = userService.signUp(signUpDto);
        return new ResponseEntity<>(usersDto , HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginDto loginDto, HttpServletResponse response){

        String token = authService.login(loginDto);

        Cookie cookie = new Cookie("token" , token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // https
        cookie.setMaxAge(60 * 60 * 24 * 7); // 7 days cookies alive
        response.addCookie(cookie);
        return ResponseEntity.ok(new ApiResponse<>(token));
    }

}
