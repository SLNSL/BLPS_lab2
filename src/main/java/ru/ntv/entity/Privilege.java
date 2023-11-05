package ru.ntv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "privilege")
public class Privilege implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "privilege_name")
    private String privilegeName;

    @Override
    public String getAuthority() {
        return privilegeName;
    }

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "role_privilege",
            joinColumns = {@JoinColumn(name = "privilege_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> roles;
}