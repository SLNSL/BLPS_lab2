package ru.ntv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.ntv.dto.request.boss.CreateJournalistRequest;
import ru.ntv.dto.response.boss.JournalistResponse;
import ru.ntv.entity.Article;
import ru.ntv.entity.User;
import ru.ntv.etc.DatabaseRole;
import ru.ntv.exception.NotRightRoleException;
import ru.ntv.repo.ArticleRepository;
import ru.ntv.repo.RoleRepository;
import ru.ntv.repo.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final ArticleRepository articleRepository;
    
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       ArticleRepository articleRepository,
                       PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.articleRepository = articleRepository;
        this.encoder = encoder;
    }


    @Transactional(transactionManager = "transactionManager")
    public String dismissJournalist(Integer idJournalist) {

        final var journalist = userRepository.findById(idJournalist).orElseThrow();
        System.out.println(journalist.getLogin() + " " + journalist.getId() + " " + journalist.getRole().getRoleName());
        if (!Objects.equals(journalist.getRole().getRoleName(), DatabaseRole.ROLE_JOURNALIST.name()))
            throw new NotRightRoleException("Это не журналист");

        journalist.setRole(
                roleRepository.findRoleByName(
                        DatabaseRole.ROLE_CLIENT.name()
                )
        );

        List<Article> articles = articleRepository.findAllByJournalistName(journalist.getLogin());
        articles.forEach(e -> System.out.println(e.getJournalistName()));
        articles.forEach(a -> a.setJournalistName(null));
        userRepository.save(journalist);
        articleRepository.saveAll(articles);

        return "уволен";
    }


    public JournalistResponse getJournalistById(int id) {
        final var user = userRepository.findById(id).get(); //todo throw custom Exception if user is not found

        return convertUserToJournalist(user);
    }

    public List<JournalistResponse> getAllJournalists() {
        final var roleJournalist = roleRepository.findRoleByName(DatabaseRole.ROLE_JOURNALIST.name());
        final List<User> users = userRepository.findAllByRole(roleJournalist);

        return users
                .stream()
                .map(this::convertUserToJournalist)
                .collect(Collectors.toList());
    }

    public JournalistResponse createJournalist(CreateJournalistRequest newUser) {
        if (userRepository.existsByLogin(newUser.getLogin())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This login is already taken");
        }

        // Create journalist's account
        final var user = new User();
        user.setLogin(newUser.getLogin());
        user.setPassword(encoder.encode(newUser.getPassword()));
        user.setRole(
                roleRepository.findRoleByName(
                        DatabaseRole.ROLE_JOURNALIST.name()
                )
        );
        final var savedUser = userRepository.save(user);

        return convertUserToJournalist(savedUser);
    }

    private JournalistResponse convertUserToJournalist(User user) {
        return new JournalistResponse(
                user.getId(),
                user.getLogin()
        );
    }
}