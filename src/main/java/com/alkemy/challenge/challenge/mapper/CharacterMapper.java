package com.alkemy.challenge.challenge.mapper;

import com.alkemy.challenge.challenge.dto.CharacterBasicDTO;
import com.alkemy.challenge.challenge.dto.CharacterDTO;
import com.alkemy.challenge.challenge.dto.MovieDTO;
import com.alkemy.challenge.challenge.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alkemy.challenge.challenge.entity.Character;

import java.util.*;

@Component
public class CharacterMapper {

    @Autowired
    private MovieMapper movieMapper;

    public List<CharacterBasicDTO> characterEntity2CharacterBasicDTO(Collection<Character> entities){
        List<CharacterBasicDTO> dtos = new ArrayList<>();
        CharacterBasicDTO basicDTO;
        for (Character character:
             entities) {
            basicDTO = new CharacterBasicDTO();
            basicDTO.setId(character.getId());
            basicDTO.setImage(character.getImage());
            basicDTO.setName(character.getName());
            dtos.add(basicDTO);
        }
        return dtos;
    }

    public Character characterDTO2Entity(CharacterDTO characterDTO){
        Character character = new Character();
        character.setImage(characterDTO.getImage());
        character.setName(characterDTO.getName());
        character.setAge(characterDTO.getAge());
        character.setWeight(characterDTO.getWeight());
        character.setHistory(characterDTO.getHistory());
        return character;
    }

    public CharacterDTO characterEntity2DTO(Character entity, boolean loadMovies){
        CharacterDTO dto = new CharacterDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        dto.setHistory(entity.getHistory());
        if (loadMovies){
            List<MovieDTO> movieDTOS = this.movieMapper.movieEntityList2DTOList(entity.getMovies(), false);
            dto.setMovies(movieDTOS);
        }
        return dto;
    }

    public List<CharacterDTO> characterEntitySet2DTOList(Collection<Character> entities, boolean loadMovies){
       List<CharacterDTO> dtos = new ArrayList<>();
        for (Character character :
                entities) {
            dtos.add(this.characterEntity2DTO(character, loadMovies));
        }
        return dtos;
    }

    public Set<Character> characterDTOList2Entity(List<CharacterDTO> dtos){
        Set<Character> entities = new HashSet<>();
        for (CharacterDTO dto :
                dtos) {
            entities.add(this.characterDTO2Entity(dto));
        }
        return entities;
    }

    public void characterEntityRefreshValues(Character entity, CharacterDTO dto){
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setHistory(dto.getHistory());
    }
}
