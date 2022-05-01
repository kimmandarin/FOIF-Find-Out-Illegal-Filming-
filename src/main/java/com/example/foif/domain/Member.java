package com.example.foif.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Member {
    private String policeId;
    private String policeStation;
    private String userName;
    private String phoneNumber;
    private String email;
    private String password;
}
