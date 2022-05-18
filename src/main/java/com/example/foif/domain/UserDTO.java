package com.example.foif.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String policeId;
    private String policeStation;
    private String userName;
    private String phoneNumber;
    private String email;
    private String role;
}
