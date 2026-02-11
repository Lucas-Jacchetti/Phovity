package com.lucasjacc.dev.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucasjacc.dev.dto.user.UserCreateDto;
import com.lucasjacc.dev.jwt.TokenService;
import com.lucasjacc.dev.mapper.UserMapper;
import com.lucasjacc.dev.model.User;
import com.lucasjacc.dev.repository.UserRepository;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;
    
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthDto data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        ResponseCookie cookie = ResponseCookie.from("token", token)
            .httpOnly(true)
            .secure(false) //mudar pra true em producao
            .path("/")
            .maxAge(60 * 60 * 2)
            .sameSite("Lax")
            .build();

            return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }

    
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated UserCreateDto data){
        if (repository.findByEmail(data.getEmail()) != null) {
            return ResponseEntity.badRequest().build();
        }
        
        data.setPassword(new BCryptPasswordEncoder().encode(data.getPassword()));
        User user = UserMapper.toEntity(data);
        repository.save(user);

        return ResponseEntity.ok().build();
    }
}
