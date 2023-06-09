package ru.ntv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.ntv.entity.users.Privilege;
import ru.ntv.entity.users.User;
import ru.ntv.repo.user.PrivilegeRepository;
import ru.ntv.repo.user.RoleRepository;
import ru.ntv.repo.user.UserRepository;


@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class NTVApplication{
    public static void main(String[] args) {
        SpringApplication.run(NTVApplication.class, args);
    }


}