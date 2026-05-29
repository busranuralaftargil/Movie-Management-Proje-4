package com.sau.mm.model;

import com.sau.mm.dto.ClassificationDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "classification")
public class Classification {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="classification_seq")
    @SequenceGenerator(name="classification_seq", sequenceName="classification_seq", initialValue = 1 , allocationSize=1)
    private long id;

    private LocalDate date = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public Classification(ClassificationDTO classificationDTO) {
        this.id = classificationDTO.getId();
        this.date = classificationDTO.getDate() != null ? classificationDTO.getDate() : LocalDate.now();
    }

    public ClassificationDTO viewAsClassificationDTO() {
        return new ClassificationDTO(
                id,
                movie != null ? movie.getTitle() : null,
                category != null ? category.getName() : null,
                date
        );
    }
}