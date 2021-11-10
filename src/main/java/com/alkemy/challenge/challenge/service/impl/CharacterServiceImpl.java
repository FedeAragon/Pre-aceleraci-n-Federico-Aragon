package com.alkemy.challenge.challenge.service.impl;

import com.alkemy.challenge.challenge.dto.CharacterBasicDTO;
import com.alkemy.challenge.challenge.dto.CharacterDTO;
import com.alkemy.challenge.challenge.dto.CharacterFilterDTO;
import com.alkemy.challenge.challenge.entity.Character;
import com.alkemy.challenge.challenge.entity.Movie;
import com.alkemy.challenge.challenge.exception.ParamNotFound;
import com.alkemy.challenge.challenge.mapper.CharacterMapper;
import com.alkemy.challenge.challenge.repository.CharacterRepository;
import com.alkemy.challenge.challenge.repository.MovieRepository;
import com.alkemy.challenge.challenge.repository.specifications.CharacterSpecification;
import com.alkemy.challenge.challenge.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CharacterServiceImpl implements CharacterService {

    private CharacterRepository repository;
    private CharacterSpecification specification;
    private CharacterMapper mapper;
    private MovieRepository movieRepository;

    @Autowired
    public CharacterServiceImpl(CharacterRepository repository, CharacterSpecification specification, CharacterMapper mapper, MovieRepository movieRepository) {
        this.repository = repository;
        this.specification = specification;
        this.mapper = mapper;
        this.movieRepository = movieRepository;
    }

    @Override
    public List<CharacterBasicDTO> getAll() {
        List<Character> entities = this.repository.findAll();
        List<CharacterBasicDTO> basicDTOS = this.mapper.characterEntity2CharacterBasicDTO(entities);
        return basicDTOS;
    }

    @Override
    public CharacterDTO save(CharacterDTO characterDTO) {
        Character entity = this.mapper.characterDTO2Entity(characterDTO);
        Character entitySaved = this.repository.save(entity);
        CharacterDTO result = this.mapper.characterEntity2DTO(entitySaved, false);
        return result;
    }

    @Override
    public CharacterDTO getDetailsById(Long id) {
        Character entity = this.repository.findById(id)
                .orElseThrow(() -> new ParamNotFound("Character not found with id " + id));
        CharacterDTO dto = this.mapper.characterEntity2DTO(entity, false);
        return dto;
    }

    @Override
    public List<CharacterDTO> getByFilters(String name, Integer age, Set<Long> movies, String order) {
        CharacterFilterDTO filterDTO = new CharacterFilterDTO(name, age, movies, order);
        List<Character> entities = this.repository.findAll(this.specification.getByFilters(filterDTO));
        List<CharacterDTO> dtos = this.mapper.characterEntitySet2DTOList(entities, true);
        return dtos;
    }

    @Override
    public CharacterDTO update(Long id, CharacterDTO characterDTO) {
        Character entity = this.repository.findById(id)
                .orElseThrow(() -> new ParamNotFound("Character not found with id " + id));
        this.mapper.characterEntityRefreshValues(entity,characterDTO);
        Character entitySaved = this.repository.save(entity);
        CharacterDTO result = this.mapper.characterEntity2DTO(entitySaved, false);
        return result;
    }

    @Override
    public void addMovie(Long id, Long idMovie) {
        Character entity = this.repository.getById(id);
        entity.getMovies().size();
        Movie movieEntity = this.movieRepository.getById(idMovie);
        entity.addMovie(movieEntity);
        this.repository.save(entity);
    }

    @Override
    public void removeMovie(Long id, Long idMovie) {
        Character entity = this.repository.getById(id);
        entity.getMovies().size();
        Movie movieEntity = this.movieRepository.getById(idMovie);
        this.repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        Character entity = this.repository.findById(id)
                .orElseThrow(() -> new ParamNotFound("Character not found with id " + id));
        this.repository.deleteById(entity.getId());
    }
}
