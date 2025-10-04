package com.steph18.demo.contoller;

import com.steph18.demo.dto.RequestObject;
import com.steph18.demo.dto.ResponseObject;
import com.steph18.demo.entities.User;
import com.steph18.demo.enums.ResponseObjetCode;
import com.steph18.demo.repository.RoleRepository;
import com.steph18.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@RestController
@RequestMapping("/api/ref")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final RoleRepository roleRepo;

    @PostMapping("/assign-role")
    @PreAuthorize("hasAnyAuthority({'PERM_ALL'})")
    public ResponseEntity<?> assignRole(@RequestBody RequestObject request) {
        try {
            System.out.println("uuuu : ");
            User u = userService.assignRole(request.getUserDto().getUsername(),
                    request.getUserDto().getRoleCodes());
            return u.getRoles().isEmpty() ?
                    ResponseEntity.ok()
                            .body(
                                    ResponseObject.builder()
                                            .code(ResponseObjetCode.NOK.getCode())
                                            .description("Aucun role trouve")
                                            .status(ResponseObjetCode.NOK.getCode())
                                            .message(HttpStatus.OK.getReasonPhrase())
                                            .build())
                    :
                    ResponseEntity.ok()
                            .body(
                                    ResponseObject.builder()
                                            .code(ResponseObjetCode.OK.getCode())
                                            .description("Ajout effectue avec success")
                                            .status(HttpStatus.OK.value())
                                            .message(HttpStatus.OK.getReasonPhrase())
                                            .build()

                            );

        } catch (Exception e) {
            return ResponseEntity.ok()
                    .body(
                            ResponseObject.builder()
                                    .code(ResponseObjetCode.NOK.getCode())
                                    .description(e.getMessage())
                                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                    .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                                    .build());
        }

    }

    @PostMapping("/lock-user")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> unlockUser(@RequestBody RequestObject request) {
        try {
            User user = userService.findById(request.getUserDto().getId());
            user.setLocked(request.getUserDto().getLocked());
            userService.save(user);
            return ResponseEntity.ok("User unlocked");
        } catch (Exception e) {
            return ResponseEntity.ok("Error");
        }

    }
}

