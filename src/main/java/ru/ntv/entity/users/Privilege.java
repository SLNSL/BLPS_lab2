package ru.ntv.entity.users;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;

//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Table(name = "privilege")

@Document("privilege")
@Data
public class Privilege implements GrantedAuthority {
    @Id
    private String id;

    @Field("privilege_name")
    private String privilegeName;

    @Override
    public String getAuthority() {
        return privilegeName;
    }
}