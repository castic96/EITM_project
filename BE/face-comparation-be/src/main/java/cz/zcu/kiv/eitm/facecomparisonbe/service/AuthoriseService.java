package cz.zcu.kiv.eitm.facecomparisonbe.service;

import cz.zcu.kiv.eitm.facecomparisonbe.dto.LoginResponse;

public interface AuthoriseService {

    LoginResponse authorise(String userList, String image);
}
