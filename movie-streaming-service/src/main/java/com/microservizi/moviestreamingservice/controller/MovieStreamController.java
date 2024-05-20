package com.microservizi.moviestreamingservice.controller;

import com.microservizi.moviestreamingservice.service.MovieCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class MovieStreamController {

    @Autowired
    private MovieCatalogService movieCatalogService;

    public static final Logger log = Logger.getLogger(MovieStreamController.class.getName());

    public static final String VIDEO_DIRECTORY = "C:\\Users\\mirko\\Desktop\\java\\prova_microservizi\\video\\";

  /*  @GetMapping("/stream/{videoPath}")
    public ResponseEntity<InputStreamResource> streamVideo(@PathVariable String videoPath) throws FileNotFoundException {
        File file = new File(VIDEO_DIRECTORY + videoPath);
        if (file.exists()) {
            InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("video/mp4"))
                    .body(inputStreamResource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/
    @GetMapping("/stream/{videoPath}")
    public ResponseEntity<InputStreamResource> streamVideo(@PathVariable String videoPath) throws FileNotFoundException {
        File file = new File(VIDEO_DIRECTORY + videoPath);
        System.out.println("Percorso del file: " + file.getAbsolutePath()); // Controlla il percorso del file
        if (file.exists()) {
            InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("video/mp4"))
                    .body(inputStreamResource);
        } else {
            System.out.println("Il file non esiste"); // Aggiungi un log nel caso in cui il file non venga trovato
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/stream/with-id/{videoInfoId}")
    public ResponseEntity<InputStreamResource> streamVideoById(@PathVariable Long videoInfoId) {
        String moviePath = movieCatalogService.getMoviePath(videoInfoId);
        log.info("Resolved movie path = {}"+ moviePath);

        if (moviePath != null) {
            try {
                return streamVideo(moviePath);
            } catch (FileNotFoundException e) {
                log.info("File not found at path: {}"+ moviePath);
                return ResponseEntity.notFound().build();
            }
        } else {
            log.info("Movie path not found for ID: {}"+ videoInfoId);
            return ResponseEntity.notFound().build();
        }
    }



   /* @GetMapping("/stream/with-id/{videoInfoId}")
    public ResponseEntity<InputStreamResource> streamVideoById(@PathVariable Long videoInfoId) throws FileNotFoundException {
        String moviePath = movieCatalogService.getMoviePath(videoInfoId);
        log.log(Level.INFO, "Resolved movie path = {0}", moviePath);
        return streamVideo(moviePath);
    }*/


  /*  public static final String VIDEO_DIRECTORY = "C:/Users/mirko/Desktop/java/prova_microservizi/ideo/";

    @GetMapping("/stream/{name}")
    public ResponseEntity<FileSystemResource> streamVideo1(@PathVariable String name) {
        File videoFile = new File(VIDEO_DIRECTORY + name + ".mp4");
        if (videoFile.exists()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("video/mp4"))
                    .body(new FileSystemResource(videoFile));
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/




}
