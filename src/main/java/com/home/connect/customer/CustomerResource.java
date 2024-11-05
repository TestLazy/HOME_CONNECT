package com.home.connect.customer;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers/")
public class CustomerResource {
    private final CustomerService service;

    public CustomerResource(CustomerService service) {
        this.service = service;
    }

    @GetMapping("admin/")
    public ResponseEntity<Page<Customer>> findAllPaginad(
            @RequestParam(value = "username", required = false, defaultValue = "") String username,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        if (username.isEmpty() || username.isBlank()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(service.findAllPaged(PageRequest.of(page, size)));
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(service.searchUsers(username, PageRequest.of(page, size)));
        }
    }

    @GetMapping("user/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findById(id));
    }

    @PutMapping("admin/{id}")
    public ResponseEntity<String> updateById(@Valid @PathVariable Integer id,
                                             @RequestBody CustomerSignUp dto) {
        service.updateById(id, dto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Atualizada com sucesso!");
    }

    @DeleteMapping("admin/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        service.deleteById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Deletado com sucesso!");
    }
}