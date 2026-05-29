package com.sau.mm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassificationDTO {
    private long id;
    private String movieTitle;
    private String categoryName;
    private LocalDate date;
}