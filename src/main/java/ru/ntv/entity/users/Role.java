package ru.ntv.entity.users;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;


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


    @DocumentReference
    private Set<Privilege> privileges;
}