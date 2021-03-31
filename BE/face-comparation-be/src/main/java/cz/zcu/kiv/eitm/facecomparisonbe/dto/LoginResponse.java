package cz.zcu.kiv.eitm.facecomparisonbe.dto;

public class LoginResponse {

    private boolean status;
    private String firstname;
    private String lastname;

    public LoginResponse(boolean status, String firstname, String lastname) {
        this.status = status;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public boolean isStatus() {
        return status;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
