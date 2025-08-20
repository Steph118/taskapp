package com.steph18.demo.contoller;

import com.steph18.demo.configs.JwtUtils;
import com.steph18.demo.dto.RequestObject;
import com.steph18.demo.dto.ResponseObject;
import com.steph18.demo.entities.User;
import com.steph18.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtils;
    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepo;

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(@RequestBody RequestObject request) {
        try {
            System.err.println("RequestObject: " + request);
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserDto().getUsername(),
                            request.getUserDto().getPassword())
            );
            System.err.println("userDetails: " + auth);
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            System.err.println("userDetails: " + userDetails);
            String jwt = jwtUtils.generateToken(userDetails);
            return buildJwtResponse(jwt);
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error("Exception: ", e);
            return ResponseEntity
                    .ok()
                    .body(ResponseObject.builder()
                            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .message("Login failed")
                            .status(HttpStatus.INTERNAL_SERVER_ERROR.toString()
                            ).build());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RequestObject request) {
        log.info("Request: " + request);
        if (userRepo.existsByUsername(request.getUserDto().getUsername()))
            return ResponseEntity.ok(
                    ResponseObject.builder()
                            .code(HttpStatus.BAD_REQUEST.value())
                            .message("Username already exists")
                            .status(HttpStatus.BAD_REQUEST.toString())
                            .build()
            );
        request.getUserDto().setPassword(encoder.encode(request.getUserDto().getPassword()));
        User user = User.register(request.getUserDto());
        // assign roles later
        userRepo.save(user);
        return ResponseEntity.ok()
                .body(ResponseObject.builder()
                        .code(HttpStatus.OK.value())
                        .message("User registered successfully")
                        .status(HttpStatus.OK.toString())
                        .build());
    }

    public ResponseEntity<ResponseObject> buildJwtResponse(String jwt) {
        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + jwt)
                .header("Access-Control-Expose-Headers", "Authorization")
                .body(ResponseObject.builder()
                        .token(jwt)
                        .message("Login successful")
                        .code(200)
                        .status(HttpStatus.OK.toString())
                        .build());
    }

}
