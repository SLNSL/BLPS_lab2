package ru.ntv.entity.users;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "role_id_seq", sequenceName = "role_id_seq")
    @Column(name = "id")
    private int id;

    @Column(name = "role_name")
    private String roleName;
}
