package cz.zcu.kiv.eitm.facecomparisonbe.dto;

public class LoginRequest {

    /** Image as base64 string. */
    private String image;
    /** IP address. */
    private String ipAddress;

    public LoginRequest(String image, String ipAddress) {
        this.image = image;
        this.ipAddress = ipAddress;
    }

    public String getImage() {
        return image;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
