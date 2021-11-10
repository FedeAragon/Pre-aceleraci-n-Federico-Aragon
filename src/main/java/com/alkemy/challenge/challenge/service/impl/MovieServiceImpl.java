package com.alkemy.challenge.challenge.service.impl;

import com.alkemy.challenge.challenge.dto.MovieBasicDTO;
import com.alkemy.challenge.challenge.dto.MovieDTO;
import com.alkemy.challenge.challenge.dto.MovieFilterDTO;
import com.alkemy.challenge.challenge.entity.Character;
import com.alkemy.challenge.challenge.entity.Movie;
import com.alkemy.challenge.challenge.exception.ParamNotFound;
import com.alkemy.challenge.challenge.mapper.MovieMapper;
import com.alkemy.challenge.challenge.repository.CharacterRepository;
import com.alkemy.challenge.challenge.repository.MovieRepository;
import com.alkemy.challenge.challenge.repository.specifications.MovieSpecification;
import com.alkemy.challenge.challenge.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository repository;
    private MovieSpecification specification;
    private MovieMapper mapper;
    private CharacterRepository characterRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository repository, MovieSpecification specification, MovieMapper mapper, CharacterRepository characterRepository) {
        this.repository = repository;
        this.specification = specification;
        this.mapper = mapper;
        this.characterRepository = characterRepository;
    }

    @Override
    public List<MovieBasicDTO> getAll() {
        List<Movie> entities = this.repository.findAll();
        List<MovieBasicDTO> basicDTOS = this.mapper.movieEntity2MovieBasicDTO(entities);
        return basicDTOS;
    }

    @Override
    public MovieDTO save(MovieDTO movieDTO) {
        Movie entity = this.mapper.movieDTO2Entity(movieDTO);
        Movie entitySaved = this.repository.save(entity);
        MovieDTO result = this.mapper.movieEntity2DTO(entity, false);
        return result;
    }

    @Override
    public MovieDTO getDetailsById(Long idMovie) {
        Movie entity = this.repository.findById(idMovie)
                .orElseThrow(() -> new ParamNotFound("Movie not found with id " + idMovie));
        MovieDTO dto = this.mapper.movieEntity2DTO(entity, false);
        return dto;
    }

    @Override
    public List<MovieDTO> getByFilters(String title, Long genreId, String order) {
        MovieFilterDTO filterDTO = new MovieFilterDTO(title, genreId, order);
        List<Movie> entities = this.repository.findAll(this.specification.getByFilters(filterDTO));
        List<MovieDTO> dtos = this.mapper.movieEntitySet2DTOList(entities, true);
        return dtos;
    }

    @Override
    public MovieDTO update(Long id, MovieDTO movieDTO) {
        Movie entity = this.repository.findById(id)
                .orElseThrow(() -> new ParamNotFound("Movie not found with id " + id));
        this.mapper.movieEntityRefreshValues(entity, movieDTO);
        Movie entitySaved = this.repository.save(entity);
        MovieDTO result = this.mapper.movieEntity2DTO(entitySaved, false);
        return result;
    }

    @Override
    public void addCharacter(Long id, Long idCharacter) {
        Movie entity = this.repository.getById(id);
        entity.getCharacters().size();
        Character characterEntity = this.characterRepository.getById(idCharacter);
        entity.addCharacter(characterEntity);
        this.repository.save(entity);
    }

    @Override
    public void removeCharacter(Long id, Long idCharacter) {
        Movie entity = this.repository.getById(id);
        entity.getCharacters().size();
        Character characterEntity = this.characterRepository.getById(idCharacter);
        entity.remoteCharacter(characterEntity);
        this.repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        Movie entity = this.repository.findById(id)
                .orElseThrow(() -> new ParamNotFound("Movie not found with id " + id));
        this.repository.deleteById(entity.getId());
    }
}
