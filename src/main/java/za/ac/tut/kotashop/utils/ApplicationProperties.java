package za.ac.tut.kotashop.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {
    @Value("${user_admin}")
    private String adminUsername;

    @Value("${pass}")
    private String adminPassword;

    public String getAdminUsername() {
        return adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }
}
