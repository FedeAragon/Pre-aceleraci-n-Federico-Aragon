package com.alkemy.challenge.challenge.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CharacterBasicDTO implements Serializable {
    private Long id;
    private String image;
    private String name;
}
