package ru.ntv.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.ntv.dto.request.admin.NewArticleRequest;
import ru.ntv.entity.articles.Theme;
import ru.ntv.exception.ArticleNotFoundException;
import ru.ntv.dto.response.common.ArticlesResponse;
import ru.ntv.entity.articles.Article;
import ru.ntv.repo.article.ArticleRepository;
import ru.ntv.repo.article.ThemeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    private final ThemeRepository themeRepository;

    public ArticleService(ArticleRepository articleRepository, ThemeRepository themeRepository) {
        this.articleRepository = articleRepository;
        this.themeRepository = themeRepository;
    }

    public Optional<List<Article>> findByHeader(String header){
        return articleRepository.findAllByHeaderContainingIgnoreCase(header);
    }

    public List<Article> getArticlesByThemes(List<Integer> theme_ids) {
        final var themes = themeRepository.findAllById(theme_ids);

        return articleRepository.findByThemesIn(themes);
    }

    public Optional<Article> findById(int id){
        return articleRepository.findById(id);
    }

    public Article createArticle(NewArticleRequest newArticleRequest) {
        return articleRepository.save(
                convertNewArticleRequestToArticle(newArticleRequest)
        );
    }

    public ArticlesResponse getAll(Integer offset, Integer limit){
        final var res = new ArticlesResponse();
        res.setArticles(articleRepository.findAll(PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, "creationDate"))).get().toList());

        return res;
    }

    public Article update(int id, NewArticleRequest req) throws ArticleNotFoundException{
        final var oldArticleOptional = articleRepository.findById(id);
        if (oldArticleOptional.isEmpty()) throw new ArticleNotFoundException("Article not found!");

        final var article = oldArticleOptional.get();

        if (req.getText() != null) article.setText(req.getText());
        if (req.getHeader() != null) article.setHeader(req.getHeader());
        if (req.getSubheader() != null) article.setSubheader(req.getSubheader());
        if (req.getPhotoURL() != null) article.setPhoto(req.getPhotoURL());
        if (req.getPriority() != null) article.setPriority(req.getPriority());

        if (req.getThemeIds() != null) {
            final var themes = themeRepository.findAllById(req.getThemeIds());
            article.setThemes((List<Theme>) themes);
        }

        return articleRepository.save(article);
    }


    public void delete(int id){
        articleRepository.deleteById(id);
    }

    private Article convertNewArticleRequestToArticle(NewArticleRequest newArticleRequest){
        final var article = new Article();
        final var themes = themeRepository.findAllById(newArticleRequest.getThemeIds());

        article.setThemes(themes);
        article.setHeader(newArticleRequest.getHeader());
        article.setSubheader(newArticleRequest.getSubheader());
        article.setText(newArticleRequest.getText());
        article.setPriority(newArticleRequest.getPriority());
        article.setPhoto(newArticleRequest.getPhotoURL());

        return article;
    }
}
