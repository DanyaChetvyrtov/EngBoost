package app.first.myEng.engBoost.utils.expression;

import app.first.myEng.engBoost.models.user.Role;
import app.first.myEng.engBoost.utils.jwt.JwtEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("customSecurityExpression")
public class CustomSecurityExpression {

    public boolean canAccessUser(Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtEntity user = (JwtEntity) authentication.getPrincipal();
        Integer userId = user.getId();

        return userId.equals(id) || hasAnyRole(authentication, Role.ROLE_ADMIN);
    }

    private boolean hasAnyRole(Authentication authentication, Role... roles) {
        for (Role role : roles) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
            System.out.println(role.name());
            if (authentication.getAuthorities().contains(authority))
                return true;
        }
        return false;
    }

    public boolean canAccessCard(Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtEntity user = (JwtEntity) authentication.getPrincipal();
        Integer userId = user.getId();

        return userId.equals(id);
    }
}
