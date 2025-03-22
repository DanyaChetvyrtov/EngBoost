package app.first.myEng.engBoost.utils.mapper;

import app.first.myEng.engBoost.dto.user.UserWriteDto;
import app.first.myEng.engBoost.dto.user.UserListItemDto;
import app.first.myEng.engBoost.models.user.User;
import org.mapstruct.Mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper extends MainMapper<User, UserWriteDto> {

    UserWriteDto toDto(User user);

    UserListItemDto toShortDto(User user);

    User toEntity(UserWriteDto userWriteDto);

    default List<UserListItemDto> toShortDtoList(List<User> users) {
        if(users == null) return Collections.emptyList();

        return users.stream()
                .map(this::toShortDto)
                .collect(Collectors.toList());
    }
}
