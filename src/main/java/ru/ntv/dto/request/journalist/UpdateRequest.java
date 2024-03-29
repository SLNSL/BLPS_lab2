package ru.ntv.dto.request.journalist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequest {
    private List<Integer> themeIds;

    private Integer id;

    @Size(min = 2, max = 50)
    private String header;

    @Size(min = 2, max = 100)
    private String subheader;

    @Size(min = 2, max = 255)
    private String text;

    @Min(1)
    @Max(3)
    private Integer priority;

    @Size(min = 2, max = 255)
    private String photoURL;
}