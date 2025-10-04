package com.steph18.demo.service;

import com.steph18.demo.dto.UserDto;
import com.steph18.demo.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends GenericService<User, Long> {

    Optional<User> queryByUsername(String username);
   boolean existByUsername(String username);
    User save(UserDto userDto);
    String login(String username, String password);
    String refreshtoken(String username, String password);
    User assignRole(String username, List<String> roleNames);
}
