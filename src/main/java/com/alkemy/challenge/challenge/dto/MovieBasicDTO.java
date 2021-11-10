package com.alkemy.challenge.challenge.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class MovieBasicDTO implements Serializable {
    private Long id;
    private String image;
    private String title;
    private String creationDate;
}
