package com.home.connect.property;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/properties/")
public class PropertyResource {
    private PropertyService service;

    public PropertyResource(PropertyService service) {
        this.service = service;
    }

    @PostMapping("admin/")
    public ResponseEntity<String> save(@Valid @RequestBody PropertyRequest dto) {
        service.save(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Propriedade criada com sucesso!");
    }

    @GetMapping("user/{idCustomer}/{idProperty}")
    public ResponseEntity<String> saveInCustomer(@PathVariable Integer idCustomer, @PathVariable Integer idProperty) {
        service.saveInCustomer(idCustomer, idProperty);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Propriedade adicionada com sucesso!");
    }

    @GetMapping("all/{id}")
    public ResponseEntity<Property> findById(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findById(id));
    }

    @GetMapping("admin/")
    public ResponseEntity<Page<Property>> findAllPaginad(
            @RequestParam(value = "value", required = false, defaultValue = "0.0") BigDecimal value,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        if (value.compareTo(BigDecimal.ZERO) == 0) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(service.findAllPaged(PageRequest.of(page, size)));
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(service.searchProperty(value, PageRequest.of(page, size)));
        }
    }

    @PutMapping("admin/{idProperty}")
    public ResponseEntity<String> updateById(@PathVariable Integer idProperty,
                                             @Valid @RequestBody PropertyRequest dto) {
        service.updateById(idProperty, dto);

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