package com.crio.xmeme.controller;

import com.crio.xmeme.exceptions.CustomException;
import com.crio.xmeme.exchange.AuthResponse;
import com.crio.xmeme.exchange.LoginRequest;
import com.crio.xmeme.exchange.RegisterRequest;
import com.crio.xmeme.services.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest){
        log.info("***register called***");
        if(registerRequest.getFirstName()!=null && registerRequest.getLastName()!=null && registerRequest.getEmail()!=null
            && registerRequest.getPass()!=null)
            if(authService.isUserExist(registerRequest.getEmail()))
                return new ResponseEntity<>(new CustomException("User already Exist.").getMessage(), HttpStatus.CONFLICT);
            else
                return ResponseEntity.ok(authService.register(registerRequest));
        else
            return new ResponseEntity<>(new CustomException("BAD REQUEST!!").getMessage(), HttpStatus.BAD_REQUEST );
    }

    @PostMapping(LOGIN_ENDPOINT)
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        log.info("***login called***");
        var token = authService.login(loginRequest).getToken();
        if(!token.equals("Bad credentials"))
            return ResponseEntity.ok(AuthResponse.builder().token(token).build());
        else
            return new ResponseEntity<>(new CustomException("Invalid Email or Password").getMessage(), HttpStatus.BAD_REQUEST );
    }
}
