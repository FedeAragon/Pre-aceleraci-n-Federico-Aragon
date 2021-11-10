package com.alkemy.challenge.challenge.mapper;

import com.alkemy.challenge.challenge.dto.GenreDTO;
import com.alkemy.challenge.challenge.entity.Genre;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper {
    public Genre genreDTO2Entity(GenreDTO genreDTO) {
        Genre genre = new Genre();
        genre.setImage(genreDTO.getImage());
        genre.setName(genreDTO.getName());
        return genre;
    }
    public GenreDTO genreEntity2DTO(Genre genre){
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(genre.getId());
        genreDTO.setImage(genre.getImage());
        genreDTO.setName(genre.getName());
        return genreDTO;
    }
    public List<GenreDTO> genreEntity2GenreDTOList(List<Genre> entities){
        List<GenreDTO> dtos = new ArrayList<>();
        for (Genre genre :
                entities) {
            dtos.add(this.genreEntity2DTO(genre));
        }
        return dtos;
    }
}
