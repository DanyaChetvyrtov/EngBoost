package app.first.myEng.engBoost.controller;

import app.first.myEng.engBoost.dto.user.UserDto;
import app.first.myEng.engBoost.models.user.User;
import app.first.myEng.engBoost.service.UserService;
import app.first.myEng.engBoost.utils.mapper.UserMapper;
import app.first.myEng.engBoost.validation.OnCreate;
import app.first.myEng.engBoost.validation.OnUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") int id) {
        logger.info("GET request for user with id '{}' has been received.", id);
        UserDto userDto = userMapper.toDto(userService.getUserById(id));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Validated(OnCreate.class) UserDto userDto) {
        logger.info("POST request for user with '{}' username has been received.", userDto.getUsername());
        User user = userMapper.toEntity(userDto);
        userDto = userMapper.toDto(userService.create(user));
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody @Validated(OnUpdate.class) UserDto userDto) {
        logger.info("PUT request for user with '{}' username has been received.", userDto.getUsername());
        User user = userMapper.toEntity(userDto);
        userDto = userMapper.toDto(userService.update(user));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
        logger.info("DELETE request for user with id '{}' has been received.", id);
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
