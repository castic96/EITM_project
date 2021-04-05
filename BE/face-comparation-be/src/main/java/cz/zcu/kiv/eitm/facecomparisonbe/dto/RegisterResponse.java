package cz.zcu.kiv.eitm.facecomparisonbe.dto;

public class RegisterResponse {

    /** Status indicator - true=OK. */
    private boolean status;
    /** Error message. */
    private String errorMessage;

    public RegisterResponse(boolean status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public boolean isStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
