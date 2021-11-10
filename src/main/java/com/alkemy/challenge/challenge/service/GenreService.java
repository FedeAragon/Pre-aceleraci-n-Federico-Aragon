package com.alkemy.challenge.challenge.service;

import com.alkemy.challenge.challenge.dto.GenreDTO;

import java.util.List;

public interface GenreService {

    public GenreDTO save(GenreDTO genreDTO);

    public List<GenreDTO> getAllGenres();
}
