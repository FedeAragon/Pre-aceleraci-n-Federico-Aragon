package com.alkemy.challenge.challenge.mapper;

import com.alkemy.challenge.challenge.dto.CharacterDTO;
import com.alkemy.challenge.challenge.dto.MovieBasicDTO;
import com.alkemy.challenge.challenge.dto.MovieDTO;
import com.alkemy.challenge.challenge.entity.Character;
import com.alkemy.challenge.challenge.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class MovieMapper {

    @Autowired
    private CharacterMapper characterMapper;

    public List<MovieBasicDTO> movieEntity2MovieBasicDTO(Collection<Movie> entities){
        List<MovieBasicDTO> dtos = new ArrayList<>();
        MovieBasicDTO basicDTO;
        for (Movie movie :
                entities) {
            basicDTO = new MovieBasicDTO();
            basicDTO.setId(movie.getId());
            basicDTO.setImage(movie.getImage());
            basicDTO.setTitle(movie.getTitle());
            basicDTO.setCreationDate(movie.getCreationDate().toString());
            dtos.add(basicDTO);
        }
        return dtos;
    }

    private LocalDate stringToLocalDate(String creationDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(creationDate, formatter);
    }

    public List<MovieDTO> movieEntityList2DTOList(List<Movie> entities, boolean loadCharacters){
        List<MovieDTO> dtos = new ArrayList<>();
        for (Movie movie :
                entities) {
            dtos.add(this.movieEntity2DTO(movie, loadCharacters));
        }
        return dtos;
    }

    public MovieDTO movieEntity2DTO(Movie entity, boolean loadCharacters){
        MovieDTO dto = new MovieDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreationDate(entity.getCreationDate().toString());
        dto.setScore(entity.getScore());
        dto.setGenreId(entity.getGenreId());
        if (loadCharacters){
            List<CharacterDTO> characterDTOS = this.characterMapper.characterEntitySet2DTOList(entity.getCharacters(), false);
            dto.setCharacters(characterDTOS);
        }
        return dto;
    }

    public Movie movieDTO2Entity(MovieDTO dto){
        Movie movie = new Movie();
        movie.setImage(dto.getImage());
        movie.setTitle(dto.getTitle());
        movie.setCreationDate(this.stringToLocalDate(dto.getCreationDate()));
        movie.setScore(dto.getScore());
        movie.setGenreId(dto.getGenreId());
        return movie;
    }

    public List<MovieDTO> movieEntitySet2DTOList(Collection<Movie> entites, boolean loadCharacters){
        List<MovieDTO> dtos = new ArrayList<>();
        for (Movie movie :
                entites) {
            dtos.add(this.movieEntity2DTO(movie, loadCharacters));
        }
        return dtos;
    }
    
    public Set<Movie> movieDTOList2Entity(List<MovieDTO> dtos){
        Set<Movie> entities = new HashSet<>();
        for (MovieDTO dto :
                dtos) {
            entities.add(this.movieDTO2Entity(dto));
        }
        return entities;
    }

    public void movieEntityRefreshValues(Movie entity, MovieDTO dto){
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setCreationDate(this.stringToLocalDate(dto.getCreationDate()));
        entity.setScore(dto.getScore());
        entity.setGenreId(dto.getGenreId());
    }
}
