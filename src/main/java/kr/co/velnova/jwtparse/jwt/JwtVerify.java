package kr.co.velnova.jwtparse.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;

@Component
public class JwtVerify {

    @Value("${jwt.alg}")
    private String alg;

    @Value("${jwt.typ}")
    private String typ;

    @Value("${jwt.secret-key}")
    private String secretKey;

    //토큰 생성
    public String createToken(Map<String, Object> payloads) {

        //Header 부분 설정
        Map<String, Object> headers = new HashMap<>();
        headers.put("alg", alg);
        headers.put("typ", typ);

        long expiredTime = 1000L * 60L; // 토큰 유효 시간 (1분)

        Date ext = new Date(); // 토큰 만료 시간
        ext.setTime(ext.getTime() + expiredTime);

        // 토큰 Builder
        return Jwts.builder()
                .setHeader(headers) // Headers 설정
                .setClaims(payloads) // Claims 설정
                .setSubject("user") // 토큰 용도
                .setExpiration(ext) // 토큰 만료 시간 설정
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes()) // HS256과 Key로 Sign
                .compact();
    }

    //토큰 검증
    public Map<String, Object> verifyJWT(String jwt) {

        // 기본적인 token 유효성 체크
        if (jwt == null || !jwt.startsWith("Bearer ")) {
            throw new UnsupportedJwtException("jwt를 확인해주세요.");
        }

        // [Bearer ] 삭제
        jwt = jwt.substring(7);

        return Jwts.parser()
                    .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8)) // Set Key
                    .parseClaimsJws(jwt) // 파싱 및 검증, 실패 시 에러
                    .getBody();
    }
}
