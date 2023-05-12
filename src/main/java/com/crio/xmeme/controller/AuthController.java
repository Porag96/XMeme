package com.crio.xmeme.controller;

import com.crio.xmeme.exchange.AuthResponse;
import com.crio.xmeme.exchange.LoginRequest;
import com.crio.xmeme.exchange.RegisterRequest;
import com.crio.xmeme.services.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AuthController.AUTH_API_ENDPOINT)
@RequiredArgsConstructor
@Log4j2
public class AuthController {
    public static final String AUTH_API_ENDPOINT = "/api/v1/auth";
    public static final String REGISTER_ENDPOINT = "/register";
    public static final String LOGIN_ENDPOINT = "/login";

    @Autowired
    private final AuthService authService;

    @PostMapping(REGISTER_ENDPOINT)
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest){
        log.info("***register called***");
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping(LOGIN_ENDPOINT)
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        log.info("***login called***");
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
