package za.ac.tut.kotashop.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.tut.kotashop.dto.UserDto;
import za.ac.tut.kotashop.entity.User;
import za.ac.tut.kotashop.repository.UserRepository;
import za.ac.tut.kotashop.utils.PasswordEncryptor;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setFullname(userDto.getFullname());
        user.setSurname(userDto.getSurname());
        user.setCountry(userDto.getCountry());
        user.setTownship(userDto.getTownship());
        user.setHousenumber(userDto.getHousenumber());
        user.setEmail(userDto.getEmail());
        user.setPassword(PasswordEncryptor.hashPassword(userDto.getPassword()));
        userRepository.save(user);
    }

    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            String storedHashedPassword = user.getPassword();
            if (PasswordEncryptor.verifyPassword(password, storedHashedPassword)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFullname(user.getFullname());
        userDto.setSurname(user.getSurname());
        userDto.setCountry(user.getCountry());
        userDto.setTownship(user.getTownship());
        userDto.setHousenumber(user.getHousenumber());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}