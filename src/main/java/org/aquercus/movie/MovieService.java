package org.aquercus.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class MovieService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/hello")
    public String hello(){
        return "Hello!";
    }

    @GetMapping("/movies")
    public Collection<MovieModel> list(){
        return jdbcTemplate.query(
                "select imdb_id, title, year, rating from movies",
                (rs, rowNum) -> new MovieModel(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getBigDecimal(4))
        );

    }

    @GetMapping("/movies/{id}")
    public MovieModel get(@PathVariable("id") String id){
        return jdbcTemplate.queryForObject(
                "select imdb_id, title, year, rating from movies where imdb_id = ?",
                (rs, rowNum) -> new MovieModel(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getBigDecimal(4)),
                id
        );
    }

    @PostMapping("/movies")
    public MovieModel add(@RequestBody MovieModel movie){
        jdbcTemplate.update(
                "insert into movies (imdb_id, title, year, rating) values (?,?,?,?)",
                movie.getImdbId(), movie.getTitle(), movie.getYear(), movie.getRating()
                );
        return get(movie.getImdbId());
    }

    @PutMapping("/movies/{id}")
    public MovieModel replace(@RequestBody MovieModel movie,@PathVariable("id") String id ){
        jdbcTemplate.update(
                "update movies set title = ?, year = ?, rating = ? where imdb_id = ?",
                 movie.getTitle(), movie.getYear(), movie.getRating(), id
        );
        return get(id);
    }

    @DeleteMapping("/movies/{id}")
    public void delete(@PathVariable("id") String id ){
        jdbcTemplate.update(
                "delete from movies where imdb_id = ?", id);

    }
}
