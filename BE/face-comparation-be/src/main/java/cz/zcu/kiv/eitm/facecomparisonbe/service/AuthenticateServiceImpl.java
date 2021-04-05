package cz.zcu.kiv.eitm.facecomparisonbe.service;

import cz.zcu.kiv.eitm.facecomparisonbe.dto.LoginResponse;
import cz.zcu.kiv.eitm.facecomparisonbe.model.User;
import cz.zcu.kiv.eitm.facecomparisonbe.utils.AWSRekognitionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.List;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    @Autowired
    private UserService userService;

    @Override
    public LoginResponse authenticate(String ipAddress, String image) {
        List<User> usersByIpAddress = userService.getUsersByIpAddress(ipAddress);
        User foundUser = checkUsers(usersByIpAddress, image);

        if (foundUser != null) {
            return new LoginResponse(true, foundUser.getFirstName(), foundUser.getLastName());
        } else {
            List<User> otherUsers = userService.getAllUsers();
            otherUsers.removeAll(usersByIpAddress);

            foundUser = checkUsers(otherUsers, image);
            if (foundUser != null) {
                return new LoginResponse(true, foundUser.getFirstName(), foundUser.getLastName());
            } else {
                return new LoginResponse(false, null, null);
            }
        }
    }

    /**
     * Compares all given users' images with given image. If some user's image
     * (face) is the same as the face in given image, returns this user.
     *
     * @param users users in DB
     * @param image login image
     * @return found user with similar image (face) or NULL
     */
    private User checkUsers(List<User> users, String image) {
        AWSRekognitionUtil awsRekognitionUtil = new AWSRekognitionUtil();

        for (User user : users) {
            String imgBase64 = user.getImage();

            ByteBuffer sourceImageBytes = ByteBuffer.wrap(Base64.getDecoder().decode(imgBase64));
            ByteBuffer targetImageBytes = ByteBuffer.wrap(Base64.getDecoder().decode(image));

            boolean found = awsRekognitionUtil.compareImages(sourceImageBytes, targetImageBytes);

            if (found) {
                return user;
            }
        }

        return null;
    }
}