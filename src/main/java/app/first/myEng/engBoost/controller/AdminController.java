package app.first.myEng.engBoost.controller;

import app.first.myEng.engBoost.dto.common.PageResponse;
import app.first.myEng.engBoost.dto.user.UserListItemDto;
import app.first.myEng.engBoost.models.user.User;
import app.first.myEng.engBoost.service.UserService;
import app.first.myEng.engBoost.utils.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {
    private final UserService userService;
    private final UserMapper userMapper;

    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

    public AdminController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/users")
    public ResponseEntity<PageResponse<UserListItemDto>> getUsers(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "5") Integer size
    ) {
        Page<User> usersPage = userService.getUsers(page, size);
        List<UserListItemDto> userDtos = userMapper.toShortDtoList(usersPage.getContent());
        PageResponse<UserListItemDto> response = new PageResponse<>(
                userDtos,
                usersPage.getTotalPages(),
                usersPage.getTotalElements(),
                page, size
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/users/search")
    public ResponseEntity<List<UserListItemDto>> getUsers(
            @RequestParam(value = "username") String username
    ) {
        logger.info("GET request for searching buy '{}' username", username);
        List<User> users = userService.searchUsersByUsername(username);
        List<UserListItemDto> userDtos = userMapper.toShortDtoList(users);

        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }
}
