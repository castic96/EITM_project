package cz.zcu.kiv.eitm.facecomparisonbe.service;

import cz.zcu.kiv.eitm.facecomparisonbe.dto.LoginResponse;
import cz.zcu.kiv.eitm.facecomparisonbe.model.User;
import cz.zcu.kiv.eitm.facecomparisonbe.utils.AWSRekognitionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    @Autowired
    private UserService userService;

    @Override
    public LoginResponse authenticate(String ipAddress, String image, String email) {
        List<User> users;
        if (!email.equals("")) {
            users = userService.getUsersByEmail(email);
        } else {
            users = userService.getAllUsers();
        }

        List<User> foundUsers = checkUsers(users, image);

        int count = foundUsers.size();
        if (count == 1) { // found the one -> return this user
            User foundUser = foundUsers.get(0);
            return new LoginResponse(true, false, foundUser.getFirstName(), foundUser.getLastName());
        } else if (count > 1) { // found multiple users -> need e-mail address
            return new LoginResponse(false, true, null, null);
        } else { // other problem (no one found)
            return new LoginResponse(false, false, null, null);
        }
    }

    /**
     * Compares all given users' images with given image. If some user's image
     * (face) is the same as the face in given image, returns this user.
     *
     * @param users users in DB
     * @param image login image
     * @return found users with similar image (face)
     */
    private List<User> checkUsers(List<User> users, String image) {
        AWSRekognitionUtil awsRekognitionUtil = new AWSRekognitionUtil();

        List<User> foundUsers = new ArrayList<>();
        for (User user : users) {
            String imgBase64 = user.getImage();

            ByteBuffer sourceImageBytes = ByteBuffer.wrap(Base64.getDecoder().decode(imgBase64));
            ByteBuffer targetImageBytes = ByteBuffer.wrap(Base64.getDecoder().decode(image));

            Float confidence = awsRekognitionUtil.compareImages(sourceImageBytes, targetImageBytes);

            if (confidence > AWSRekognitionUtil.CONFIDENCE_MIN) {
               foundUsers.add(user);
            }
        }

        return foundUsers;
    }
}
