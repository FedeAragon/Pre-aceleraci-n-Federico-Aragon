package com.alkemy.challenge.challenge.controller;

import com.alkemy.challenge.challenge.dto.GenreDTO;
import com.alkemy.challenge.challenge.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genre")
@CrossOrigin("*")
public class GenreController {

    @Autowired
    private GenreService service;

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAll(){
        List<GenreDTO> dtos = this.service.getAllGenres();
        return ResponseEntity.ok().body(dtos);
    }

    @PostMapping
    public ResponseEntity<GenreDTO> save(@RequestBody GenreDTO genreDTO){
        GenreDTO genreSaved = this.service.save(genreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(genreSaved);
    }
}
