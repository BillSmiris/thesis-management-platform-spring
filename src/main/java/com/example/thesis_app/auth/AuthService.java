package com.example.thesis_app.auth;

import com.example.thesis_app.auth.dto.request.LoginRequestModel;
import com.example.thesis_app.auth.dto.response.LoginResponseModel;
import com.example.thesis_app.professor.ProfessorRepository;
import com.example.thesis_app.user.User;
import com.example.thesis_app.user.UserRepository;
import jakarta.security.auth.message.AuthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final String authErrorMessage = "Wrong username or password. Please check your credentials and try again.";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String login(LoginRequestModel loginRequestModel) throws AuthException {
        Optional<User> userOptional = userRepository.findByUsername(loginRequestModel.getUsername());

        if(userOptional.isEmpty()) {
            throw new RuntimeException(authErrorMessage);
        }

        User user = userOptional.get();

        if(!passwordEncoder.matches(loginRequestModel.getPassword(), user.getPassword())) {
            throw new AuthException(authErrorMessage);
        }

        return jwtUtil.generateToken(user.getUsername(), user.getRole());
//        return new LoginResponseModel(
//                user.getRole()
//        );
    }
}
