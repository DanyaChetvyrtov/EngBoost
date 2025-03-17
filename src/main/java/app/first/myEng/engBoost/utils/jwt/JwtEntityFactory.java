package app.first.myEng.engBoost.utils.jwt;

import app.first.myEng.engBoost.models.user.User;

public class JwtEntityFactory {


    public static JwtEntity create(User user){
        return new JwtEntity(
                user.getId(),
                user.getUsername(),
                user.getPassword()
        );
    }
}
