package com.steph18.demo.contoller;

import com.steph18.demo.configs.JwtUtils;
import com.steph18.demo.dto.AccesTokenRequest;
import com.steph18.demo.dto.AccesTokenResponse;
import com.steph18.demo.dto.RequestObject;
import com.steph18.demo.dto.ResponseObject;
import com.steph18.demo.entities.User;
import com.steph18.demo.enums.ResponseObjetCode;
import com.steph18.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;

@Log
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(@RequestBody RequestObject request) {
        ResponseObject response;
        try {
            log.log(Level.FINE, "RequestObject:{0}", request);
            Optional<User> user = userService.queryByUsername(request.getUserDto().getUsername());
            response = checkUser(user);
            if (Objects.nonNull(response)) {
                return buildResponseEntity(response);
            }
            String token = userService.login(request.getUserDto().getUsername(),
                    request.getUserDto().getPassword());
            response = buildResponse(ResponseObjetCode.OK.getCode(),
                    HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(),
                    "Login successfully");
            response.buildLogin(token,Boolean.FALSE);
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .body(response);
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error("Exception: ", e);
            response = buildResponse(ResponseObjetCode.NOK.getCode(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                    "Login failed");
            return buildResponseEntity(response);
        }
    }

    @PostMapping(value = "/login2",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<AccesTokenResponse> login2(@ModelAttribute AccesTokenRequest request) {
        try {
            log.log(Level.FINE, "RequestObject:{0}", request);
            Optional<User> user = userService.queryByUsername(request.getUsername());
            log.log(Level.FINE, "RequestObject:{0}", user);
            if (user.isEmpty()) {
                return ResponseEntity.ok(
                        AccesTokenResponse.builder()
                                .error(ResponseObjetCode.INVALID_GRANT.getDescription())
                                .errorDescription(ResponseObjetCode.BAD_CREDENTIALS.getDescription())
                                .build()
                );
            }
            String token = userService.login(request.getUsername(),
                    request.getPassword());
            String rtoken = userService.refreshtoken(request.getUsername(),
                    request.getPassword());
            return  ResponseEntity.ok(
                    AccesTokenResponse.builder()
                            .accessToken(token)
                            .refreshToken(rtoken)
                            .tokenType("bearer")
                            .expiresIn(jwtUtils.getJwtExpiration())
                            .scope(request.getScope())
                            .build()
            );
        }catch (Exception e) {
            return ResponseEntity.ok(
                    AccesTokenResponse.builder()
                            .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                            .errorDescription(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                            .build()
            );
        }
    }


    private ResponseObject checkUser( Optional<User> user) {
        if (user.isEmpty()) {
            return buildResponse(ResponseObjetCode.USER_NOT_FOUND.getCode(),
                    11,
                    "user not found",
                    "user not found");
        }
        if (!user.get().getActif() || user.get().getLocked()) {
            return  buildResponse(ResponseObjetCode.USER_UNAUTHORISED.getCode(),
                    12,
                    "user unauthorized",
                    "user locked or not actif");
        }
        if (user.get().getChangePassword()) {
            return buildResponse(ResponseObjetCode.OK.getCode(),
                    HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(),
                    "user must change password")
                    .buildLogin(null,Boolean.TRUE);
        }
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RequestObject request) {
        try {
            log.log(Level.FINE, "Request: {0}", request);
            if (userService.existByUsername(request.getUserDto().getUsername()))
                return ResponseEntity.ok(
                        ResponseObject.builder()
                                .code(ResponseObjetCode.USER_ALREADY_EXISTS.getCode())
                                .description(ResponseObjetCode.USER_ALREADY_EXISTS.getDescription())
                                .message(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .build()
                );
            userService.save(request.getUserDto());
            return ResponseEntity.ok()
                    .body(ResponseObject.builder()
                            .code(ResponseObjetCode.OK.getCode())
                            .message(HttpStatus.OK.getReasonPhrase())
                            .description("User registered successfully")
                            .status(HttpStatus.OK.value())
                            .build());
        } catch (Exception e) {
            log.log(Level.WARNING, "Exception: ", e);
            return ResponseEntity.ok()
                    .body(ResponseObject.builder()
                            .code(ResponseObjetCode.UNKNOWN.getCode())
                            .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                            .message("Erreur survenue")
                            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .build());
        }

    }

    private ResponseEntity<ResponseObject> buildResponseEntity(ResponseObject response) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .body(response);
    }

    private ResponseObject buildResponse(
            int code, int status, String message, String description) {
        return ResponseObject.builder()
                .description(description)
                .code(code)
                .status(status)
                .message(message)
                .build();
    }

}
