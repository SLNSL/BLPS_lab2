package ru.ntv.repo.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.ntv.entity.users.Privilege;

@Repository
public interface PrivilegeRepository extends MongoRepository<Privilege, Integer> {
}