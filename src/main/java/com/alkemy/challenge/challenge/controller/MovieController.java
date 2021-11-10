package com.alkemy.challenge.challenge.controller;

import com.alkemy.challenge.challenge.dto.MovieBasicDTO;
import com.alkemy.challenge.challenge.dto.MovieDTO;
import com.alkemy.challenge.challenge.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
@CrossOrigin("*")
public class MovieController {

    private MovieService service;

    @Autowired
    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieBasicDTO>> getAll() {
        List<MovieBasicDTO> basicDTOS = this.service.getAll();
        return ResponseEntity.ok(basicDTOS);
    }

    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movieDTO) {
        MovieDTO result = this.service.save(movieDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@PathVariable Long id, @RequestBody MovieDTO movieDTO) {
        MovieDTO result = this.service.update(id, movieDTO);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getDetailsById(@PathVariable Long id) {
        MovieDTO result = this.service.getDetailsById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getDetailsByFilteers(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long genreId,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        List<MovieDTO> dtos = this.service.getByFilters(title, genreId, order);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/{id}/character/{idCharacter}")
    public ResponseEntity<String> addCharacter(@PathVariable Long id, @PathVariable Long idCharacter) {
        this.service.addCharacter(id, idCharacter);
        return ResponseEntity.ok("Character add successfully");
    }

    @DeleteMapping("/{id}/character/{idCharacter}")
    public ResponseEntity<String> removeCharacter(@PathVariable Long id, @PathVariable Long idCharacter) {
        this.service.removeCharacter(id, idCharacter);
        return ResponseEntity.ok("Character delete successfully");
    }
}

