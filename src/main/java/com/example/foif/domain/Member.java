package com.example.foif.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Component
public class Member {
    @NotEmpty(message = "경찰서 지역을 입력해주세요")
    private String policeStation;

    @NotEmpty(message = "이름을 입력해주세요.")
    private String userName;

    @NotEmpty(message = "경찰코드를 입력해주세요.")
    private String policeId;

    @NotEmpty(message = "전화번호를 입력해주세요.")
    private String phoneNumber;

    @NotEmpty(message = "이메일을 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;

    private Boolean enabled;
}
