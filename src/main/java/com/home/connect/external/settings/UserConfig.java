package com.home.connect.external.settings;

import com.home.connect.core.domain.repositories.UserRepository;
import com.home.connect.external.credentials.UserDetaisLoader;
import com.home.connect.external.gateways.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    public UserService userService(UserRepository repository) {return new UserService(repository);}

    @Bean
    public UserDetaisLoader userDetaisLoader(UserRepository repository) {return new UserDetaisLoader(repository);}

}