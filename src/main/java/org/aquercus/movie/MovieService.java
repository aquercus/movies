package org.aquercus.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class MovieService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/hello")
    public String hello(){
        return "Hello!";
    }

    @GetMapping("/list")
    public Collection<MovieModel> list(){
        return jdbcTemplate.query(
                "select imdb_id, title, year, rating from movies",
                (rs, rowNum) -> new MovieModel(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getBigDecimal(4))
        );

    }
}
