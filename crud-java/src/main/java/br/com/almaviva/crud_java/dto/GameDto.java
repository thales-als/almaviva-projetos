package main.java.br.com.almaviva.crud_java.dto;

import java.time.LocalDate;

public record GameDto(
        String title,
        String genre,
        String platform,
        LocalDate releaseDate,
        String developer
) {}
