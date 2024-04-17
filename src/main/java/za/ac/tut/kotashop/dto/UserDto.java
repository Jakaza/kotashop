package za.ac.tut.kotashop.dto;

import jakarta.validation.constraints.NotEmpty;

public class UserDto {
    private Long id;
    @NotEmpty
    private String fullname;
    @NotEmpty
    private String surname;

    @NotEmpty
    private String country;

    @NotEmpty
    private String township;

    @NotEmpty
    private String housenumber;

    @NotEmpty(message = "Email should not be empty")
    private String email;

    @NotEmpty(message = "Password should not be empty")
    private String password;

}