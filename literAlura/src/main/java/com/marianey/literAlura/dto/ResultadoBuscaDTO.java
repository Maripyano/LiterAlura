package com.marianey.literAlura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultadoBuscaDTO {
    public int count;
    public String next;
    public String previous;
    public List<DadosLivroDTO> results;
}