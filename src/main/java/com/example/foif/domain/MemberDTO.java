package com.example.foif.domain;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
// @javax.persistence.Entity(name="MEMBER")
@Entity
@Table(name="member")
public class MemberDTO {

    public MemberDTO() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "police_id")
    private String policeId;

    @Column(name = "police_station")
    private String policeStation;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

}
