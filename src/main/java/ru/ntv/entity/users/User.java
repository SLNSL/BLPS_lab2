package ru.ntv.entity.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
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

    @DocumentReference
    private Role role;
}