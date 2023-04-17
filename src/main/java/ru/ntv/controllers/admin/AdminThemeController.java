package ru.ntv.controllers.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ntv.dto.request.admin.CreateThemeRequest;
import ru.ntv.entity.articles.Theme;
import ru.ntv.service.ThemesService;
import javax.validation.*;

@RestController
@RequestMapping("admin/themes")
@Validated
public class AdminThemeController {
    
    private final ThemesService themesService;

    public AdminThemeController(ThemesService themesService) {
        this.themesService = themesService;
    }

    @PostMapping()
    ResponseEntity<Theme> create(@Valid @RequestBody CreateThemeRequest req){
        final var theme = themesService.create(req);

        return ResponseEntity.ok(theme);
    }

    @DeleteMapping(params = "id")
    ResponseEntity<?> delete(@RequestParam int id){
        themesService.delete(id);

        return ResponseEntity.ok("OK");
    }
}