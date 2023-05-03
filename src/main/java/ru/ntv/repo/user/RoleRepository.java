package ru.ntv.repo.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.ntv.entity.users.Role;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Role findRoleByRole(String roleName);
}