package ru.ntv.controllers.journalist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ntv.dto.request.journalist.CreateThemeRequest;
import ru.ntv.entity.Theme;
import ru.ntv.service.ThemesService;

import javax.validation.Valid;

@RestController
@RequestMapping("themes")
@Validated
public class JournalistThemeController {

    private final ThemesService themesService;

    public JournalistThemeController(ThemesService themesService) {
        this.themesService = themesService;
    }

    @PostMapping()
    ResponseEntity<Theme> create(@Valid @RequestBody CreateThemeRequest req) {
        final var theme = themesService.create(req);

        return ResponseEntity.ok(theme);
    }

    @DeleteMapping(params = "id")
    ResponseEntity<?> delete(@RequestParam int id) {
        themesService.delete(id);

        return ResponseEntity.ok("OK");
    }
}