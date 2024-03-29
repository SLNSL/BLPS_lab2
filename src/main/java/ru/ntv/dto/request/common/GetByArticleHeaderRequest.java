package ru.ntv.dto.request.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByArticleHeaderRequest implements Serializable {
    @Size(min = 2, max = 255)
    private String header;
}