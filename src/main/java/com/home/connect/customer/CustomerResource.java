package com.home.connect.customer;

import com.home.connect.system.dtos.SignUpDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/customers/")
public class CustomerResource {
    private final CustomerService service;

    public CustomerResource(CustomerService service) {
        this.service = service;
    }

    @GetMapping("admin/")
    public ResponseEntity<Page<Customer>> findAllPaginad(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findAllPaged(page, size));
    }

    @GetMapping("user/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findById(id));
    }

    @PutMapping("user/{id}")
    public ResponseEntity<String> updateById(@Valid @PathVariable Integer id,
                                             @RequestBody SignUpDTO dto) {
        service.updateById(id, dto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Mensagem: Atualizada com sucesso!");
    }

    @DeleteMapping("admin/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        service.deleteById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Mensagem: Deletado com sucesso!");
    }
}