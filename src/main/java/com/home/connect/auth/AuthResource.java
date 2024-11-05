package com.home.connect.auth;

import com.home.connect.customer.CustomerResponse;
import com.home.connect.customer.CustomerSignIn;
import com.home.connect.customer.CustomerSignUp;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/")
public class AuthResource {
    private final AuthService service;

    public AuthResource(AuthService service) {
        this.service = service;
    }

    @PostMapping("sign-up")
    public ResponseEntity<String> signUp(@Valid @RequestBody CustomerSignUp dto) {
        service.signUp(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Criada com sucesso!");
    }

    @PostMapping("sign-in")
    public ResponseEntity<CustomerResponse> signIn(@Valid @RequestBody CustomerSignIn dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.signIn(dto));
    }
}