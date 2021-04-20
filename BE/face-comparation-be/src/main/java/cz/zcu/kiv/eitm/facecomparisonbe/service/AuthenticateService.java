package cz.zcu.kiv.eitm.facecomparisonbe.service;

import cz.zcu.kiv.eitm.facecomparisonbe.dto.LoginResponse;

public interface AuthenticateService {

    /**
     * Authenticates user.
     *
     * @param ipAddress IP address
     * @param image image in base64
     * @param email email
     * @return login response
     */
    LoginResponse authenticate(String ipAddress, String image, String email);
}
