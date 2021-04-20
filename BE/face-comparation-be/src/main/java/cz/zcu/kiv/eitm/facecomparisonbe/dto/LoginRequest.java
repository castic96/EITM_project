package cz.zcu.kiv.eitm.facecomparisonbe.dto;

public class LoginRequest {

    /** Image as base64 string. */
    private String image;
    /** IP address. */
    private String ipAddress;
    /** User's e-mail address. */
    private String email;

    public LoginRequest(String image, String ipAddress, String email) {
        this.image = image;
        this.ipAddress = ipAddress;
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                ", ipAddress='" + ipAddress + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
