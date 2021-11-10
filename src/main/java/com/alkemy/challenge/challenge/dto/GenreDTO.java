package com.alkemy.challenge.challenge.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GenreDTO implements Serializable {
    private Long id;
    private String name;
    private String image;
}
