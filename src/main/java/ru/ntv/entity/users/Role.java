package ru.ntv.entity.users;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Set;

//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Table(name = "role")

@Document("role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    private String id;

    private String name;

    @DBRef
    Set<Privilege> privileges;
}