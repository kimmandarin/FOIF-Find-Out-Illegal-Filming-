package com.example.foif.domain;

public class Result {
    private String[] result;
    private String[] correl;
    private String[] intersect;
    private String[] bhattacharyya;
    private String check = "두 영상은 동일하지 않은 영상입니다.";

    public String[] getResult() {
        return result;
    }

    public void setResult(String[] result) {
        this.result = result;
    }

    public String[] getCorrel() {
        return correl;
    }

    public void setCorrel(String[] correl) {
        this.correl = correl;
    }

    public String[] getIntersect() {
        return intersect;
    }

    public void setIntersect(String[] intersect) {
        this.intersect = intersect;
    }

    public String[] getBhattacharyya() {
        return bhattacharyya;
    }

    public void setBhattacharyya(String[] bhattacharyya) {
        this.bhattacharyya = bhattacharyya;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }
}
