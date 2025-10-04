package com.steph18.demo.serviceimpl;

import com.steph18.demo.configs.JwtUtils;
import com.steph18.demo.dto.UserDto;
import com.steph18.demo.entities.Role;
import com.steph18.demo.entities.User;
import com.steph18.demo.repository.RoleRepository;
import com.steph18.demo.repository.UserRepository;
import com.steph18.demo.service.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


//@Transactional
@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long>
        implements UserService {

    private final JwtUtils jwtUtils;
    private final AuthenticationManager authManager;
    private final BCryptPasswordEncoder encoder;
    private final UserRepository repository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(JwtUtils jwtUtils, AuthenticationManager authManager, BCryptPasswordEncoder encoder, UserRepository repository, RoleRepository roleRepository) {
        this.jwtUtils = jwtUtils;
        this.authManager = authManager;
        this.encoder = encoder;
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

    @Override
    public JpaRepository<User, Long> getRepository() {
        return this.repository;
    }

    @Override
    public Long getId(User t) {
        return t.getId();
    }

    @Override
    public Optional<User> queryByUsername(String username) {
        return repository.queryByUsername(username);
    }

    @Override
    public boolean existByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public User save(UserDto userDto) {
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        User user = User.register(userDto);
        return repository.save(user);
    }

    @Override
    public User save(User user) {
        String encoded = encoder.encode(user.getPassword());
        user.setPassword(encoded);
        return this.repository.save(user);
    }

    @Override
    public void saveAll(List<User> users) {
        users.forEach(this::save);
    }

    @Override
    public String login(String username, String password) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(username,
                        password)
        );
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return jwtUtils.generateToken(userDetails);
    }
    @Override
    public String refreshtoken(String username, String password) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(username,
                        password)
        );
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return jwtUtils.generateRefreshToken(userDetails);
    }

    @Override
    public User assignRole(String username, List<String> roleNames) {
        User user = repository.queryByUsername(username)
                .orElseThrow(() -> new RuntimeException("user not found"));
        List<Role> roles = new ArrayList<>();
        for (String roleName : roleNames) {
            roleRepository.findByLabel(roleName).ifPresent(
                    roles::add
            );
        }
        user.setRoles(roles);
        return repository.save(user);
    }
}
