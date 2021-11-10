package com.alkemy.challenge.challenge.service.impl;

import com.alkemy.challenge.challenge.dto.GenreDTO;
import com.alkemy.challenge.challenge.entity.Genre;
import com.alkemy.challenge.challenge.mapper.GenreMapper;
import com.alkemy.challenge.challenge.repository.GenreRepository;
import com.alkemy.challenge.challenge.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private GenreMapper mapper;
    private GenreRepository repository;

    @Autowired
    public GenreServiceImpl(GenreMapper mapper, GenreRepository repository){
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public GenreDTO save(GenreDTO genreDTO) {
        Genre entity = this.mapper.genreDTO2Entity(genreDTO);
        Genre entitySaved = this.repository.save(entity);
        return this.mapper.genreEntity2DTO(entitySaved);
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        List<Genre> entities = this.repository.findAll();
        return this.mapper.genreEntity2GenreDTOList(entities);
    }
}
