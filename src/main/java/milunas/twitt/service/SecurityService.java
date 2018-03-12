package milunas.twitt.service;


import milunas.twitt.security.PasswordEncoder;
import org.apache.commons.codec.binary.Base64;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class SecurityService {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder();
    }

    public String generateToken(String username){
        String creatingData = username + ":" + new Date();
        return new String(Base64.encodeBase64(creatingData.getBytes()));
    }

}
