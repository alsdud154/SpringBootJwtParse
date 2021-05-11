package kr.co.velnova.jwtparse.time;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("자바 8 테스트")
public class TimeTest {

    @Test
    @DisplayName("Time Instant millisecond 체크")
    void java8Time() {
        Date date = new Date();
        Instant instant = date.toInstant();

        long dateMillisecond = date.getTime();
        long instantMillisecond = instant.toEpochMilli();

        assertThat(dateMillisecond).isEqualTo(instantMillisecond);

    }
}
