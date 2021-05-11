package kr.co.velnova.jwtparse.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestDTO extends UserDTO {
    private String name;
    private int age;
}
