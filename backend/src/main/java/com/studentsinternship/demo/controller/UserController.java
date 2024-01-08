package com.studentsinternship.demo.controller;

import com.studentsinternship.demo.dto.RegisterDto;
import com.studentsinternship.demo.dto.user.UserDto;
import com.studentsinternship.demo.service.UserService;
import com.studentsinternship.demo.utils.annotations.AllowBoth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin()
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@RequestBody RegisterDto dto) {
        if (!userService.userExists(dto)) {
            userService.createUser(dto);
            return new ResponseEntity<>("Account created successfully!!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("There already exists an user with the given email!", HttpStatus.OK );
        }
    }

    @GetMapping("/find-user-by-id/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        log.info("aici");
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @GetMapping("/user-info")
    public ResponseEntity<UserDto> getUser(@RequestParam("email") String email) {
        return new ResponseEntity<>(this.userService.getUser(email), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<UserDto> getUser(@RequestParam("id") Long id) {
        return new ResponseEntity<>(this.userService.getUserById(id), HttpStatus.OK);
    }
}
