package kr.co.velnova.jwtparse.interceptor;

import kr.co.velnova.jwtparse.jwt.JwtVerify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtVerifyInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JwtVerify jwtVerify;

    public JwtVerifyInterceptor(JwtVerify jwtVerify) {
        this.jwtVerify = jwtVerify;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String uri = request.getRequestURI();

        logger.debug("==================== START ====================");
        logger.debug("Request URI ===> " + uri);

        if ("/api/verify-jwt".equals(uri)) {
            // header에서 jwt token 추출
            String jwt = request.getHeader("Authorization");

            // jwt token 검증
            jwtVerify.verifyJWT(jwt);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) {

        logger.debug("==================== END ======================");

    }
}
