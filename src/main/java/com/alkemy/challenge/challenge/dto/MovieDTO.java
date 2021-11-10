package com.alkemy.challenge.challenge.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class MovieDTO implements Serializable {
    private Long id;
    private String image;
    private String title;
    private String creationDate;
    private Integer score;
    private Long genreId;
    private List<CharacterDTO> characters;
}
