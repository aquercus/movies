package org.aquercus.movie;

import java.math.BigDecimal;

public class MovieModel {
    private final String imdbId;
    private final String title;
    private final int year;
    private final BigDecimal rating;


    public MovieModel(String imdbId, String title, int year, BigDecimal rating) {
        this.imdbId = imdbId;
        this.title = title;
        this.year = year;
        this.rating = rating;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public BigDecimal getRating() {
        return rating;
    }
}
