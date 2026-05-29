package com.sau.mm.service;

import com.sau.mm.dto.MovieDTO;
import com.sau.mm.model.Movie;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


public interface MovieService {
    List<MovieDTO> getAllMovies();
    MovieDTO getMovieById(Long id);
    MovieDTO createMovie(Movie movie);
    boolean uploadImage(Long id, MultipartFile file);
    byte[] getImage(Long id);
    byte[] generateMovieReport();
}