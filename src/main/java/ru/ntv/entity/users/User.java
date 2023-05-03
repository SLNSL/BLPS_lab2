package ru.ntv.entity.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.ntv.entity.users.Role;

//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
@Document("my_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;

    private String login;

    private String password;

    @DBRef
    private Role role;
}