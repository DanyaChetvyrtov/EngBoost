package app.first.myEng.engBoost.utils.mapper;

import app.first.myEng.engBoost.dto.UserDto;
import app.first.myEng.engBoost.models.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
