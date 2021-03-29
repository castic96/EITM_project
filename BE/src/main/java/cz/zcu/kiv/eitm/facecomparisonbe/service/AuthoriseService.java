package cz.zcu.kiv.eitm.facecomparisonbe.service;

import cz.zcu.kiv.eitm.facecomparisonbe.model.Result;
import cz.zcu.kiv.eitm.facecomparisonbe.model.User;

public interface AuthoriseService {

    Result authorise(User user, User userDb);
}
