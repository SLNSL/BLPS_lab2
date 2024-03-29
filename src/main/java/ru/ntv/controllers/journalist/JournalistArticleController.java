package ru.ntv.controllers.journalist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ntv.dto.request.journalist.NewArticleRequest;
import ru.ntv.entity.Article;
import ru.ntv.exception.ArticleNotFoundException;
import ru.ntv.service.ArticleService;

import javax.validation.Valid;

@RestController
@RequestMapping("articles")
@Validated
public class JournalistArticleController {

    private final ArticleService articleService;

    public JournalistArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @PostMapping
    ResponseEntity<Article> createArticle(@Valid @RequestBody NewArticleRequest newArticleRequest) {
        final var article = articleService.createArticle(newArticleRequest);

        return ResponseEntity.ok(article);
    }

    @DeleteMapping(params = "id")
    ResponseEntity<?> deleteArticle(@RequestParam int id) {
        articleService.delete(id);

        return ResponseEntity.ok("OK");
    }


    @PutMapping(params = "id")
    ResponseEntity<Article> updateArticle(
            @RequestParam int id,
            @Valid @RequestBody NewArticleRequest req
    ) throws ArticleNotFoundException {
        final var article = articleService.update(id, req);

        return ResponseEntity.ok(article);
    }
}