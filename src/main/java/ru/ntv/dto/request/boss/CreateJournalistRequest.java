package ru.ntv.dto.request.boss;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateJournalistRequest {
    private String login;
    private String password;
}