package cz.zcu.kiv.eitm.facecomparisonbe.dto;

public class RegisterRequest {

    /** User's first name. */
    private String firstName;
    /** User's last name. */
    private String lastName;
    /** Image as base64 string. */
    private String image;
    /** IP address. */
    private String ipAddress;

    public RegisterRequest(String firstName, String lastName, String image, String ipAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        this.ipAddress = ipAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
