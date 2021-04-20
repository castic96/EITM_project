package cz.zcu.kiv.eitm.facecomparisonbe.dto;

public class RegisterRequest {

    /** User's first name. */
    private String firstName;
    /** User's last name. */
    private String lastName;
    /** User's e-mail address. */
    private String email;
    /** Image as base64 string. */
    private String image;
    /** IP address. */
    private String ipAddress;

    public RegisterRequest(String firstName, String lastName, String email, String image, String ipAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.image = image;
        this.ipAddress = ipAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getImage() {
        return image;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "firstname='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
