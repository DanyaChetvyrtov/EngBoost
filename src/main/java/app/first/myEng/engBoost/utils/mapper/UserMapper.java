package app.first.myEng.engBoost.utils.mapper;

import app.first.myEng.engBoost.dto.UserDto;
import app.first.myEng.engBoost.models.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {WordCardMapper.class})
public interface UserMapper {

    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
