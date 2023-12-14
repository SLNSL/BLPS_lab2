package ru.ntv.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ntv.entity.Theme;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Integer> {
}