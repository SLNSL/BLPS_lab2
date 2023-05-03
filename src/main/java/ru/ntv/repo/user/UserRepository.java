package ru.ntv.repo.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.ntv.entity.users.Role;
import ru.ntv.entity.users.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    boolean existsByLogin(String login);

    Optional<User> findByLogin(String login);

    List<User> findAllByRole(Role roleJournalist);
}