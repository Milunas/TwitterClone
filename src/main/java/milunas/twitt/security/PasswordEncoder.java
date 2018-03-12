package milunas.twitt.security;

import org.apache.commons.codec.binary.Base64;

import java.util.Date;

public class PasswordEncoder {

    public String encode(String password){
        return new String(Base64.encodeBase64(password.getBytes()));
    }
}
