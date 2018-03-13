package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController
{
    @Autowired
    DirectorRepository directorRepository;

    @RequestMapping("/")
    public String index(Model model)
    {
        //first let's create a director
        Director director = new Director();
        director.setName("Stephen Bullock");
        director.setGenre("Sci FI");

        //Now let's create a movie
        Movie movie = new Movie();
        movie.setTitle("Star Movie");
        movie.setYear(2017);
        movie.setDescription("About series");

        //Add the movie to an empty list
        Set<Movie> movies = new HashSet<Movie>();
        movies.add(movie);

        movie = new Movie();
        movie.setTitle("DeathStar Ewoks");
        movie.setYear(2011);
        movie.setDescription("About Ewoks on the DeathStar...");
        movies.add(movie);

        //add the list of movies to the directors move list
        director.setMovies(movies);

        directorRepository.save(director);

        //Grad all the directors from the database and send the to the template
        model.addAttribute("directors", directorRepository.findAll());
        return "index";
    }
}
