package com.studentsinternship.demo.controller;

import com.studentsinternship.demo.config.JwtTokenService;
import com.studentsinternship.demo.dto.TokenDto;
import com.studentsinternship.demo.dto.user.LoginUserDto;
import com.studentsinternship.demo.entity.User;
import com.studentsinternship.demo.service.UserService;
import liquibase.pro.packaged.S;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin()
@Slf4j
public class LoginController {

    private final UserService userService;
    private  final JwtTokenService jwtTokenService;

    public LoginController(UserService userService, JwtTokenService jwtTokenService) {
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/auth")
    public ResponseEntity<TokenDto> authentication(@RequestBody LoginUserDto dto){

        User userInfo = userService.getUserInformation(dto);
        String jwt = "";
        if(userInfo != null) {
            jwt = jwtTokenService.createJwtToken(userInfo.getEmail(), userInfo.getRole(), userInfo.getId());
        }
        TokenDto token = new TokenDto();
        token.setToken(jwt);
        return ResponseEntity.ok(token);
    }

}
