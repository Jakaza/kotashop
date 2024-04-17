package za.ac.tut.kotashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.tut.kotashop.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}