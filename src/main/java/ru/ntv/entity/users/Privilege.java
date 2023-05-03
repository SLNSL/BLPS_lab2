package ru.ntv.entity.users;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

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

    private String privilegeName;

    @Override
    public String getAuthority() {
        return privilegeName;
    }
}