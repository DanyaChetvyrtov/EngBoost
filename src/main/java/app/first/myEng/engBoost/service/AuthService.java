package app.first.myEng.engBoost.service;

import app.first.myEng.engBoost.dto.auth.JwtRequest;
import app.first.myEng.engBoost.dto.auth.JwtResponse;
import app.first.myEng.engBoost.models.user.User;
import app.first.myEng.engBoost.utils.jwt.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(AuthenticationManager authenticationManager, UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public JwtResponse login(JwtRequest loginRequest) {
        JwtResponse jwtResponse = new JwtResponse();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        User user = userService.getUserByUsername(loginRequest.getUsername());

        jwtResponse.setId(user.getId());
        jwtResponse.setUsername(user.getUsername());
        jwtResponse.setAccessToken(
                jwtTokenProvider.createAccessToken(
                        user.getId(), user.getUsername()
                )
        );
        jwtResponse.setRefreshToken(
                jwtTokenProvider.createRefreshToken(
                        user.getId(), user.getUsername()
                )
        );

        return jwtResponse;
    }

    public JwtResponse refresh(String refreshToken) {
        return jwtTokenProvider.refreshUserTokens(refreshToken);
    }
}
