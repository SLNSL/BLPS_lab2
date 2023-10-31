package ru.ntv.controllers.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ntv.dto.response.common.ThemesResponse;
import ru.ntv.service.ThemesService;

@RestController
@RequestMapping("themes")
@Validated
public class ThemesController {
    @Autowired
    private ThemesService themesService;

    @GetMapping
    ResponseEntity<ThemesResponse> getAllThemes() {
        final var response = themesService.getAllThemes();

        return ResponseEntity.ok(response);
    }
}