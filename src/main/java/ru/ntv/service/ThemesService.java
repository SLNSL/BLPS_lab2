package ru.ntv.service;

import org.springframework.stereotype.Service;
import ru.ntv.dto.request.journalist.CreateThemeRequest;
import ru.ntv.dto.response.common.ThemesResponse;
import ru.ntv.entity.Article;
import ru.ntv.entity.Theme;
import ru.ntv.repo.ArticleRepository;
import ru.ntv.repo.ThemeRepository;

import java.util.List;

@Service
public class ThemesService {
    private final ThemeRepository themeRepository;

    private final ArticleRepository articleRepository;

    public ThemesService(ThemeRepository themeRepository, ArticleRepository articleRepository) {
        this.themeRepository = themeRepository;
        this.articleRepository = articleRepository;
    }

    public ThemesResponse getAllThemes() {
        final var response = new ThemesResponse();
        response.setThemes((List<Theme>) themeRepository.findAll());

        return response;
    }

    public Theme create(CreateThemeRequest req) {
        var theme = new Theme();
        theme.setThemeName(req.getName());
        return themeRepository.save(theme);
    }

    public void delete(int id) {
        final var theme = themeRepository.findById(id).orElse(null);

        if (theme == null) return;

        List<Article> articles = theme.getArticles();
        for (Article article : articles) {
            article.getThemes().remove(theme);
            articleRepository.save(article);
        }

        themeRepository.delete(theme);
    }
}