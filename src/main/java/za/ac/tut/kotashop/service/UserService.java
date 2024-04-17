package za.ac.tut.kotashop.service;
import za.ac.tut.kotashop.dto.UserDto;
import za.ac.tut.kotashop.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}