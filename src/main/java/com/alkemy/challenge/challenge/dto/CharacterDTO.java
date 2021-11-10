package com.alkemy.challenge.challenge.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class CharacterDTO implements Serializable {
    private Long id;
    private String image;
    private String name;
    private Integer age;
    private Float weight;
    private String history;
    private List<MovieDTO> movies;
}
