package cz.zcu.kiv.eitm.facecomparisonbe.dto;

public class LoginResponse {

    private boolean status;
    private String firstName;
    private String lastName;

    public LoginResponse(boolean status, String firstName, String lastName) {
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean isStatus() {
        return status;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "status=" + status +
                ", firstname='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                '}';
    }
}
