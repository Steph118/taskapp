package com.steph18.demo.configs;

import com.steph18.demo.entities.User;
import com.steph18.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.queryByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Set<GrantedAuthority> authorities = getAuthorities(user);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }

    private Set<GrantedAuthority> getAuthorities(User user) {
        return user.getRoles().stream()
                .flatMap(role -> {
                    // Ajouter le rôle avec le préfixe ROLE_
                    Set<GrantedAuthority> roleAuth = new java.util.HashSet<>(Set.of(new SimpleGrantedAuthority("ROLE_" + role.getLabel())));
                    // Ajouter les permissions du rôle
                    Set<GrantedAuthority> permAuth = role.getPermissions().stream()
                            .map(p -> new SimpleGrantedAuthority(p.getCode()))
                            .collect(Collectors.toSet());
                    roleAuth.addAll(permAuth);
                    return roleAuth.stream();
                })
                .collect(Collectors.toSet());
    }
}
