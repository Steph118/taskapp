package com.steph18.demo.configs;

import com.steph18.demo.entities.Permission;
import com.steph18.demo.entities.Role;
import com.steph18.demo.entities.User;
import com.steph18.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Configuration
public class InitLoad {

    private UserService userService;

    @Bean
    public CommandLineRunner initData() {

        return args -> {
           /* Permission padmin = new Permission("PERM_ALL", "Tous");
            Permission ppub = new Permission("PERM_POST_ALL", "Gerer les postes");
            Permission pvisi = new Permission("PERM_VISITEUR_ALL", "Gerer les roles");
            Role radmin = Role.builder()
                    .label("ADMIN")
                    .build();
            Role rpub = Role.builder()
                    .label("PUBLICATEUR")
                    .build();
            Role rvisi = Role.builder()
                    .label("VISITEUR")
                    .build();
            radmin.setPermissions(List.of(padmin));
            rpub.setPermissions(List.of(ppub));
            rvisi.setPermissions(List.of(pvisi));
            User uAdmin = User.builder()
                    .username("kadmin").password("kadmin")
                    .actif(true) .changePassword(false)
                    .locked(false).build();
            uAdmin.setRoles(List.of(radmin));
            User uPub = User.builder()
                    .username("kpubliser").password("kpubliser")
                    .actif(true) .changePassword(false)
                    .locked(false).build();
            uPub.setRoles(List.of(rpub));

            User uVis = User.builder()
                    .username("kvisitor").password("kvisitor")
                    .actif(true) .changePassword(false)
                    .locked(false).build();
            uVis.setRoles(List.of(rvisi));
            userService.saveAll(List.of(uAdmin, uPub, uVis));*/

        };
    }
}
