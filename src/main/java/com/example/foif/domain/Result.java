package com.example.foif.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private String[] result;
    private String[] correl;
    private String[] intersect;
    private String[] bhattacharyya;
    private String[] realResult;
    private String check = "두 영상은 동일하지 않은 영상입니다.";
}
