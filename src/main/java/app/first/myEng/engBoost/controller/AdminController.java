package app.first.myEng.engBoost.controller;

import app.first.myEng.engBoost.dto.user.UserShortDto;
import app.first.myEng.engBoost.models.user.User;
import app.first.myEng.engBoost.service.UserService;
import app.first.myEng.engBoost.utils.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {
    private final UserService userService;
    private final UserMapper userMapper;

    public AdminController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserShortDto>> getUsers() {
        List<User> users = userService.getUsers();
        List<UserShortDto> userDtos = userMapper.toShortDtoList(users);
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }
}
