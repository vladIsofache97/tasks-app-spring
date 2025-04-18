package com.iso.tasks.controller;

import com.iso.tasks.model.dto.UserCreateDTO;
import com.iso.tasks.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final JwtEncoder encoder;
    private final UserService userService;

    public UserController(UserService userService, JwtEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserCreateDTO userCreateDTO) {
        return userService.createUser(userCreateDTO);
    }

    @PostMapping("/login")
    public String loginUser(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = 36000L;

        Long uid = userService.getUserId(authentication.getName());

        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(uid.toString())
                .claim("scope", scope)
                .build();

        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
