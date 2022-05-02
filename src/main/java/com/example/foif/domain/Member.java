package com.example.foif.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Member {
    private String policeStation;
    private String userName;
    private String policeId;
    private String phoneNumber;
    private String email;
    private String password;
}
