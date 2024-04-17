package za.ac.tut.kotashop.service;


import org.springframework.stereotype.Service;
import za.ac.tut.kotashop.dto.UserDto;
import za.ac.tut.kotashop.entity.User;
import za.ac.tut.kotashop.repository.RoleRepository;
import za.ac.tut.kotashop.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;


    @Override
    public void saveUser(UserDto userDto) {

    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public List<UserDto> findAllUsers() {
        return List.of();
    }
}