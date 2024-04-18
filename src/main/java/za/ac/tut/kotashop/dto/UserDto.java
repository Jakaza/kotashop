package za.ac.tut.kotashop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDto {
    private Long id;
    @NotEmpty(message = "Fullname should not be empty")
    @Size(min = 3, message = "Fullname should have mininum 3 or more characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Fullname should contain letters only")
    private String fullname;

    @NotEmpty(message = "Surname should not be empty")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Surname should contain letters only")
    private String surname;

    @NotEmpty(message = "Country should not be empty")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Country should contain letters only")
    private String country;

    @NotEmpty(message = "Township should not be empty")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Township should contain letters only")
    private String township;

    @NotEmpty(message = "House Number should not be empty")
    @Pattern(regexp = "^[0-9]+$", message = "House Number should contain numeric digits only")
    private String housenumber;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Password should not be empty")
    private String password;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTownship() {
        return township;
    }

    public void setTownship(String township) {
        this.township = township;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", surname='" + surname + '\'' +
                ", country='" + country + '\'' +
                ", township='" + township + '\'' +
                ", housenumber='" + housenumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}