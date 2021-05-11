package kr.co.velnova.jwtparse.aop;

import kr.co.velnova.jwtparse.dto.UserDTO;
import kr.co.velnova.jwtparse.jwt.JwtVerify;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
@Aspect
public class JwtAspect {

    private final JwtVerify jwtVerify;

    public JwtAspect(JwtVerify jwtVerify) {
        this.jwtVerify = jwtVerify;
    }

    // controller 패키지의 모든 메서드에 적용
    @Around("execution(* kr.co.velnova.jwtparse.controller..*(..))")
    public Object updateUserId(ProceedingJoinPoint joinPoint) throws Throwable {

        // 메서드의 인자값 호출
        Object[] args = joinPoint.getArgs();

        for (Object object : args) {
            // 함수의 인자 중 instance가 UserDTO이면 userId 변경
            if (object instanceof UserDTO) {
                UserDTO userDTO = (UserDTO) object;

                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

                // header에서 jwt token 추출
                String jwt = request.getHeader("Authorization");

                // jwt token 검증
                Map<String, Object> jwtBodyMap = jwtVerify.verifyJWT(jwt);

                userDTO.setUserId((int) jwtBodyMap.get("userId"));
            }
        }

        // 변경된 인자 값으로 메서드 실행
        return joinPoint.proceed(args);
    }
}
