package ru.ntv.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToMany
    @JoinTable(
            name = "article_theme",
            joinColumns = {@JoinColumn(name = "article_id")},
            inverseJoinColumns = {@JoinColumn(name = "theme_id")}
    )
    private List<Theme> themes;


    @Column(name = "header", unique = true)
    private String header;

    @Column(name = "subheader")
    private String subheader;

    @Column(name = "text", unique = true)
    private String text;

    @Column(name="priority")
    private Integer priority;

    @Column(name = "photo")
    private String photo;

    @Column(name = "creation_date")
    @CreationTimestamp
    private LocalDateTime creationDate;
}
