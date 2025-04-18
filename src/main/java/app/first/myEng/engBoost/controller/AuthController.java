package app.first.myEng.engBoost.controller;

import app.first.myEng.engBoost.dto.auth.JwtRequest;
import app.first.myEng.engBoost.dto.auth.JwtResponse;
import app.first.myEng.engBoost.dto.user.UserWriteDto;
import app.first.myEng.engBoost.models.user.User;
import app.first.myEng.engBoost.service.AuthService;
import app.first.myEng.engBoost.service.UserService;
import app.first.myEng.engBoost.utils.mapper.UserMapper;
import app.first.myEng.engBoost.validation.OnCreate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final UserMapper userMapper;

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthService authService, UserService userService, UserMapper userMapper) {
        this.authService = authService;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody JwtRequest loginRequest) {
        logger.info("POST 'login' request for user '{}'", loginRequest.getUsername());
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<UserWriteDto> register(@RequestBody @Validated(OnCreate.class) UserWriteDto userWriteDto) {
        logger.info("POST 'register' request for user with '{}' username has been received.", userWriteDto.getUsername());
        User user = userMapper.toEntity(userWriteDto);
        userWriteDto = userMapper.toDto(userService.create(user));
        return new ResponseEntity<>(userWriteDto, HttpStatus.CREATED);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken) {
        logger.info("POST 'refresh' request.");
        return authService.refresh(refreshToken);
    }
}
