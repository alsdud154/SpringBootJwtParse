package kr.co.velnova.jwtparse.jwt;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class JwtVerifyTest {

    @Autowired
    private JwtVerify jwtVerify;

    @Test
    @DisplayName("jwt 토큰 생성")
    void createJwt(){
        Map<String, Object> payloads = new HashMap<>();
        
        payloads.put("userId", 3);
        payloads.put("name", "강호동");
        payloads.put("age", 50);

        String token = jwtVerify.createToken(payloads);

        Assertions.assertThat(token).isNotNull();

    }
}
