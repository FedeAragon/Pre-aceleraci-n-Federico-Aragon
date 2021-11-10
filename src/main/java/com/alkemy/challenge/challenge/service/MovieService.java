package com.alkemy.challenge.challenge.service;

import com.alkemy.challenge.challenge.dto.MovieBasicDTO;
import com.alkemy.challenge.challenge.dto.MovieDTO;

import java.util.List;

public interface MovieService {

    public List<MovieBasicDTO> getAll();

    public MovieDTO save(MovieDTO movieDTO);

    public MovieDTO getDetailsById(Long idMovie);

    public List<MovieDTO> getByFilters(String title, Long genreId,String order);

    public MovieDTO update(Long id, MovieDTO movieDTO);

    public void addCharacter(Long id, Long idCharacter);

    public void removeCharacter(Long id, Long idCharacter);

    public void delete(Long id);
}
