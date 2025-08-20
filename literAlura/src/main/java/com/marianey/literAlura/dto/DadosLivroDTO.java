package com.marianey.literAlura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosLivroDTO {
    public String title;
    public List<DadosAutorDTO> authors;
    public int download_count;
}