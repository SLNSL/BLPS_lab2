package ru.ntv.dto.response.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ntv.entity.Theme;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThemesResponse {
    private List<Theme> themes;
}