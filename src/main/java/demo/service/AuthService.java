package demo.service;

import demo.repository.dto.LoginRequest;

public interface AuthService {

    String createToken(LoginRequest loginRequest);

    void removeToken();
}
