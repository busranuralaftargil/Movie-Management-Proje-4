package com.sau.mm.model;

import com.sau.mm.dto.MovieDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="movie_seq")
    @SequenceGenerator(name="movie_seq", sequenceName="movie_seq", initialValue = 1 , allocationSize=1)
    private long id;

    @Column(length = 32)
    private String title;

    @Column(length = 16)
    private String director;

    private Integer year;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Classification> classifications;

    public Movie(MovieDTO movieDTO) {
        this.id = movieDTO.getId();
        this.title = movieDTO.getTitle();
        this.director = movieDTO.getDirector();
        this.year = movieDTO.getYear();
    }

    public MovieDTO viewAsMovieDTO() {
        return new MovieDTO(id, title, director, year);
    }
}