package ru.ntv.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ntv.entity.Article;
import ru.ntv.entity.Theme;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findByThemesIn(Collection<Theme> themes);
    Optional<Article> findById(int id);
    List<Article> findAllByHeaderContainingIgnoreCase(String header);
    List<Article> findAllByJournalistName(String name);
}