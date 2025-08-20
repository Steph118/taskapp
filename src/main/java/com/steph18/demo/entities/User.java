/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.steph18.demo.entities;

import com.steph18.demo.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Mediasoft
 */

@Data
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "actif")
    private Boolean actif;

    @Column(name = "change_password")
    private Boolean changePassword;

    private Boolean locked = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

    public static User register(UserDto dto) {
        return builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .actif(true)
                .locked(false)
                .changePassword(true).build();
    }

    public User register(String username, String password) {
        builder()
                .username(username)
                .password(password)
                .actif(true)
                .changePassword(true);
        return this;
    }

    @PrePersist
    public void prePersit() {
        if (Objects.isNull(this.getActif())) {
            this.setActif(false);
        }
        this.setChangePassword(true);
    }


    @Override
    public String toString() {
        return "User{" + "id=" + id + ", userName=" + username + ", password=" + password + ", actif=" + actif + ", changePassword=" + changePassword + '}';
    }

}
