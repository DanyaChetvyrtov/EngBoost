package app.first.myEng.engBoost.utils.mapper;

import app.first.myEng.engBoost.dto.user.UserDto;
import app.first.myEng.engBoost.dto.user.UserShortDto;
import app.first.myEng.engBoost.models.user.User;
import org.mapstruct.Mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper extends MainMapper<User, UserDto> {

    UserDto toDto(User user);

    UserShortDto toShortDto(User user);

    User toEntity(UserDto userDto);

    default List<UserShortDto> toShortDtoList(List<User> users) {
        if(users == null) return Collections.emptyList();

        return users.stream()
                .map(this::toShortDto)
                .collect(Collectors.toList());
    }
}
