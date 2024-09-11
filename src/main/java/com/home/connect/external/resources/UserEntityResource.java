package com.home.connect.external.resources;

import com.home.connect.core.entities.UserEntity;
import com.home.connect.external.dtos.UpdateDTO;
import com.home.connect.external.services.UserEntityService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserEntityResource {
    private final UserEntityService service;

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> findById(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@Valid @PathVariable Integer id,
                                             @RequestBody UpdateDTO dto) {
        service.updateById(id, dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Mensagem: Conta atualizada com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        service.deleteById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Mensagem: Conta deletada com sucesso!");
    }

    @GetMapping
    public ResponseEntity<Iterable<UserEntity>> findAllPaginated(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findAllPaginated(page, size));
    }
}