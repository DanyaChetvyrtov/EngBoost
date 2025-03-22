package app.first.myEng.engBoost.controller;

import app.first.myEng.engBoost.dto.user.UserWriteDto;
import app.first.myEng.engBoost.models.user.User;
import app.first.myEng.engBoost.service.AuthService;
import app.first.myEng.engBoost.service.UserService;
import app.first.myEng.engBoost.utils.mapper.UserMapper;
import app.first.myEng.engBoost.validation.OnUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;
    private final AuthService authService;
    private final UserMapper userMapper;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService, AuthService authService, UserMapper userMapper) {
        this.userService = userService;
        this.authService = authService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserWriteDto> getUser(@PathVariable("id") int id) {
        logger.info("GET request for user with id '{}' has been received.", id);
        UserWriteDto userWriteDto = userMapper.toDto(userService.getUserById(id));
        return new ResponseEntity<>(userWriteDto, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("@customSecurityExpression.canAccessUser(#userWriteDto.id)")
    public ResponseEntity<UserWriteDto> updateUser(@RequestBody @Validated(OnUpdate.class) UserWriteDto userWriteDto) {
        logger.info("PUT request for user with '{}' username has been received.", userWriteDto.getUsername());
        logger.info("Request was received from {}", authService.getCurrentUserInfo().getUsername());
        User user = userMapper.toEntity(userWriteDto);
        userWriteDto = userMapper.toDto(userService.update(user));
        return new ResponseEntity<>(userWriteDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
        logger.info("DELETE request for user with id '{}' has been received.", id);
        logger.info("Request was received from {}", authService.getCurrentUserInfo().getUsername());
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
