package com.ongpatinhasquebrilham.petcontrol.api.controller;

import com.ongpatinhasquebrilham.petcontrol.api.model.LoginRequest;
import com.ongpatinhasquebrilham.petcontrol.api.model.LoginResponse;
import com.ongpatinhasquebrilham.petcontrol.api.model.RefreshTokenRequest;
import com.ongpatinhasquebrilham.petcontrol.domain.model.User;
import com.ongpatinhasquebrilham.petcontrol.domain.repository.UserRepository;
import com.ongpatinhasquebrilham.petcontrol.infrastructure.security.TokenService;
import com.ongpatinhasquebrilham.petcontrol.infrastructure.security.exception.InvalidTokenException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private UserRepository repository;
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var accessToken = tokenService.generateAccessToken((User) auth.getPrincipal());
        var refreshToken = tokenService.generateRefreshToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponse(accessToken, refreshToken));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody @Valid RefreshTokenRequest request) throws InvalidTokenException {
        String token = request.getRefreshToken();

        tokenService.validateRefreshToken(request.getRefreshToken());
        String username = tokenService.getSubject(token);

        var accessToken = tokenService.generateAccessToken(repository.findByUsername(username));
        var refreshToken = tokenService.generateRefreshToken(repository.findByUsername(username));

        return ResponseEntity.ok(new LoginResponse(accessToken, refreshToken));

    }

}