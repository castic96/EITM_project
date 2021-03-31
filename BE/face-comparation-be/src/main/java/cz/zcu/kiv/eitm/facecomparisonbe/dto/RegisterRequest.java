package cz.zcu.kiv.eitm.facecomparisonbe.dto;

public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String image; // in base64
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
