package com.ongpatinhasquebrilham.petcontrol.api.controller;

import com.ongpatinhasquebrilham.petcontrol.api.model.AuthenticationDTO;
import com.ongpatinhasquebrilham.petcontrol.api.model.LoginResponseDTO;
import com.ongpatinhasquebrilham.petcontrol.api.model.RefreshTokenInput;
import com.ongpatinhasquebrilham.petcontrol.api.model.RegisterDTO;
import com.ongpatinhasquebrilham.petcontrol.domain.model.User;
import com.ongpatinhasquebrilham.petcontrol.domain.repository.UserRepository;
import com.ongpatinhasquebrilham.petcontrol.infrastructure.security.TokenService;
import com.ongpatinhasquebrilham.petcontrol.infrastructure.security.exception.InvalidTokenException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private UserRepository repository;
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var accessToken = tokenService.generateAccessToken((User) auth.getPrincipal());
        var refreshToken = tokenService.generateRefreshToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(accessToken, refreshToken));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data) {
        if (!Objects.isNull(this.repository.findByUsername(data.username()))) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.username(), encryptedPassword, data.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody @Valid RefreshTokenInput request) throws InvalidTokenException {
        String token = request.getRefreshToken();

        tokenService.validateRefreshToken(request.getRefreshToken());
        String username = tokenService.getSubject(token);

        var accessToken = tokenService.generateAccessToken(repository.findByUsername(username));
        var refreshToken = tokenService.generateRefreshToken(repository.findByUsername(username));

        return ResponseEntity.ok(new LoginResponseDTO(accessToken, refreshToken));

    }

}