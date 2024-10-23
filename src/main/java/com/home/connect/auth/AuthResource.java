package com.home.connect.auth;

import com.home.connect.system.dtos.SignInDTO;
import com.home.connect.system.dtos.SignUpDTO;
import com.home.connect.system.dtos.TokenDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth/")
public class AuthResource {
    private final AuthService service;

    public AuthResource(AuthService service) {
        this.service = service;
    }

    @PostMapping("sign-up")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignUpDTO dto) {
        service.signUp(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Conta criada com sucesso!");
    }

    @PostMapping("sign-in")
    public ResponseEntity<TokenDTO> signIn(@Valid @RequestBody SignInDTO dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.signIn(dto));
    }
}