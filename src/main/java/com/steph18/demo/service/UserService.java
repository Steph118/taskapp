package com.steph18.demo.service;

import com.steph18.demo.entities.User;

import java.util.Optional;

public interface UserService extends GenericService<User, Long> {

    Optional<User> queryByUsername(String username);
}
