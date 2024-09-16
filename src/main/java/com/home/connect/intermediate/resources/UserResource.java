package com.home.connect.intermediate.resources;

import com.home.connect.core.base.ModelEntity;
import com.home.connect.core.domain.entities.User;
import com.home.connect.external.gateways.UserService;
import com.home.connect.intermediate.dtos.SignDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/users/")
public class UserResource {
    private final UserService service;

    public UserResource(UserService service) {this.service = service;}

    @GetMapping
    public ResponseEntity<Page<User>> findAllPaginad(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findAllPaged(page, size));
    }

    @GetMapping("{id}")
    public ResponseEntity<ModelEntity> findById(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateById(@Valid @PathVariable Integer id,
                                             @RequestBody SignDTO dto) {
        service.updateById(id, dto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Mensagem: Atualizada com sucesso!");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        service.deleteById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Mensagem: Deletado com sucesso!");
    }
}