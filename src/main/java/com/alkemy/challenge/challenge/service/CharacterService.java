package com.alkemy.challenge.challenge.service;

import com.alkemy.challenge.challenge.dto.CharacterBasicDTO;
import com.alkemy.challenge.challenge.dto.CharacterDTO;

import java.util.List;
import java.util.Set;

public interface CharacterService {

    public List<CharacterBasicDTO> getAll();

    public CharacterDTO save(CharacterDTO characterDTO);

    public CharacterDTO getDetailsById(Long id);

    public List<CharacterDTO> getByFilters(String name, Integer age, Set<Long> movies, String order);

    public CharacterDTO update(Long id, CharacterDTO characterDTO);

    public void addMovie(Long id, Long idMovie);

    public void removeMovie(Long id, Long idMovie);

    public void delete(Long id);
}
