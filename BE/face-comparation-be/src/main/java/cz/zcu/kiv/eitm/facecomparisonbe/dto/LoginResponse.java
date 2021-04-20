package cz.zcu.kiv.eitm.facecomparisonbe.dto;

public class LoginResponse {

    /** Status indicator - true=OK. */
    private boolean status;
    /** If user's e-mail address is necessary. */
    private boolean needEmail;

    /** User's first name. */
    private String firstName;
    /** User's last name. */
    private String lastName;

    public LoginResponse(boolean status, boolean needEmail, String firstName, String lastName) {
        this.status = status;
        this.needEmail = needEmail;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean isStatus() {
        return status;
    }

    public boolean isNeedEmail() {
        return needEmail;
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
                ", needEmail='" + needEmail + '\'' +
                ", firstname='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                '}';
    }
}
