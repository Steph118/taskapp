package com.steph18.demo.contoller;

import com.steph18.demo.entities.Role;
import com.steph18.demo.entities.User;
import com.steph18.demo.repository.RoleRepository;
import com.steph18.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    @PostMapping("/assign-role")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> assignRole(@RequestParam Long userId, @RequestParam String roleName) {
        User user = userRepo.findById(userId).orElseThrow();
        Role role = roleRepo.findByLabel(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.getRoles().add(role);
        userRepo.save(user);
        return ResponseEntity.ok("Role assigned");
    }

    @PostMapping("/lock-user")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> lockUser(@RequestParam Long userId) {
        User user = userRepo.findById(userId).orElseThrow();
        user.setLocked(true);
        userRepo.save(user);
        return ResponseEntity.ok("User locked");
    }

    @PostMapping("/unlock-user")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> unlockUser(@RequestParam Long userId) {
        User user = userRepo.findById(userId).orElseThrow();
        user.setLocked(false);
        userRepo.save(user);
        return ResponseEntity.ok("User unlocked");
    }
}

