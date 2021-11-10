package com.alkemy.challenge.challenge.controller;

import com.alkemy.challenge.challenge.dto.CharacterBasicDTO;
import com.alkemy.challenge.challenge.dto.CharacterDTO;
import com.alkemy.challenge.challenge.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("characters")
@CrossOrigin("*")
public class CharacterController {

    private CharacterService service;

    @Autowired
    public CharacterController(CharacterService service){
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CharacterBasicDTO>> getAll(){
        List<CharacterBasicDTO> basicDTOS = this.service.getAll();
        return ResponseEntity.ok(basicDTOS);
    }

    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO characterDTO) {
        CharacterDTO result = this.service.save(characterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> update(@PathVariable Long id, @RequestBody CharacterDTO characterDTO){
        CharacterDTO result = this.service.update(id, characterDTO);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        this.service.delete(id);
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getDetailsById(@PathVariable Long id){
        CharacterDTO result = this.service.getDetailsById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<CharacterDTO>> getDetailsByFilteers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Set<Long> movies,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ){
        List<CharacterDTO> dtos = this.service.getByFilters(name, age, movies, order);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/{id}/movie/{idMovie}")
    public ResponseEntity<String> addMovie(@PathVariable Long id, @PathVariable Long idMovie) {
        this.service.addMovie(id, idMovie);
        return ResponseEntity.ok("Movie add successfully");
    }

    @DeleteMapping("/{id}/movie/{idMovie}")
    public ResponseEntity<String> removeMovie(@PathVariable Long id, @PathVariable Long idMovie) {
        this.service.removeMovie(id, idMovie);
        return ResponseEntity.ok("Movie delete successfully");
    }
}
