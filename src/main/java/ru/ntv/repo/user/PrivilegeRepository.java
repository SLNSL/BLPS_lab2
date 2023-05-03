package ru.ntv.repo.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.ntv.entity.users.Privilege;

import javax.annotation.PostConstruct;

@Repository
public interface PrivilegeRepository extends MongoRepository<Privilege, String> {
    }