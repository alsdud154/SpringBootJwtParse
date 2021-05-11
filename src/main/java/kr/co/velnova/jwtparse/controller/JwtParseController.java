package kr.co.velnova.jwtparse.controller;

import kr.co.velnova.jwtparse.dto.UserDTO;
import kr.co.velnova.jwtparse.jwt.JwtVerify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class JwtParseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JwtVerify jwtVerify;

    public JwtParseController(JwtVerify jwtVerify) {
        this.jwtVerify = jwtVerify;
    }

    @PostMapping("/create-jwt")
    public String createJwt(@RequestBody Map<String, Object> payloads){
        return "Bearer " + jwtVerify.createToken(payloads);
    }

    @GetMapping("verify-jwt")
    public UserDTO verifyJwt(UserDTO userDTO) {
        logger.debug("userDTO = " + userDTO);

        return userDTO;
    }


}
