package com.marianey.literAlura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosAutorDTO {
    public String name;
    public int birth_year;
    public int death_year;
}