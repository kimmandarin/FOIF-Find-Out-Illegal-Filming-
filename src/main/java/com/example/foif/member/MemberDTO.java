package com.example.foif.member;

public class MemberDTO {
    private String policeStation;
    private String userName;
    private String policeId;
    private String phoneNumber;
    private String email;
    private String password;

    public MemberDTO(String policeStation, String userName, String policeId, String phoneNumber, String email, String password) {
        this.policeStation = policeStation;
        this.userName = userName;
        this.policeId = policeId;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public String getPoliceStation() {
        return policeStation;
    }

    public void setPoliceStation(String policeStation) {
        this.policeStation = policeStation;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "policeStation='" + policeStation + '\'' +
                ", userName='" + userName + '\'' +
                ", policeId='" + policeId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
