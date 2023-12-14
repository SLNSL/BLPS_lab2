package ru.ntv.dto.request.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ntv.entity.Theme;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByArticleThemesRequest implements Serializable {
    private List<Theme> themes;
}