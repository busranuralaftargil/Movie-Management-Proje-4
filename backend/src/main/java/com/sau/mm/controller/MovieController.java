package com.sau.mm.controller;

import com.sau.mm.dto.MovieDTO;
import com.sau.mm.model.Movie;
import com.sau.mm.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/movie")
public class MovieController {

    private final static Logger logger = LoggerFactory.getLogger(MovieController.class);
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        logger.info("Get all movies called");
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable Long id) {
        // Hocanın yazdığı kontrolün birebir aynısı
        if (id == null || id == 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        logger.info("Get movie by id {}", id);
        return new ResponseEntity<>(movieService.getMovieById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MovieDTO> addMovie(@RequestBody Movie movie) {
        logger.info("Add movie called with title: {}", movie.getTitle());
        return new ResponseEntity<>(movieService.createMovie(movie), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/uploadImage")
    public ResponseEntity<String> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        logger.info("Upload image called for movie id: {}", id);
        boolean success = movieService.uploadImage(id, file);
        if (success) {
            return new ResponseEntity<>("Resim başarıyla yüklendi.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Resim yüklenemedi!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/{id}/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        logger.info("Get image called for movie id: {}", id);
        byte[] image = movieService.getImage(id);
        if (image != null) {
            return new ResponseEntity<>(image, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping(value = "/report", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getMovieReport() {
        logger.info("Generate PDF report called");
        byte[] pdfBytes = movieService.generateMovieReport();

        if (pdfBytes != null) {
            return new ResponseEntity<>(pdfBytes, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}