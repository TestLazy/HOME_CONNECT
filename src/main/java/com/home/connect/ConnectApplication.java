package com.home.connect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConnectApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConnectApplication.class, args);
    }
}

// O usuário poderá apenas buscar por Id (Ele mesmo)
// O admin poderá buscar paginado, atualizar e deletar (apenas usuário)