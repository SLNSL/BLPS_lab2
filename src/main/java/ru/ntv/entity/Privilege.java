package ru.ntv.entity;

import lombok.*;
import javax.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "privilege")
public class Privilege implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "privilege_name")
    private String privilegeName;

    @Override
    public String getAuthority() {
        return privilegeName;
    }
}