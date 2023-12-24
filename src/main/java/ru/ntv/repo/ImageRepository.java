package ru.ntv.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ntv.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
}