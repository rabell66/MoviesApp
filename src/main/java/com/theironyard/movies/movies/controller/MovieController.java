package com.theironyard.movies.movies.controller;

import com.theironyard.movies.movies.Movie;
import com.theironyard.movies.movies.ResultsPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;


//@SpringBootApplication
//public class Application {
//
//    static final String API_URL = "http://gturnquist-quoters.cfapps.io/api/random";
//
//    public static void main(String args[]) {
//        SpringApplication.run(Application.class);
//    }

    @Controller
public class MovieController {
        static final String API_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=be2a38521a7859c95e2d73c48786e4bb";
        @RequestMapping(path = "/", method = RequestMethod.GET)
          public String login(){
            return "Home";
        }
        @RequestMapping(path = "/medium-popular-long-name", method = RequestMethod.GET)
          public String medPop(Model model){
              model.addAttribute("movies",getMovies(API_URL)
                      .stream()
                      .filter(e->e.getTitle().length()>9)
                      .filter(e->e.getPopularity()>29 && e.getPopularity()<81)
                      .collect(Collectors.toList()));
              return "medium-popular-long-name";
        }
        @RequestMapping(path = "/now-playing", method = RequestMethod.GET)
          public String nowPlaying (Model model){

              model.addAttribute("movies",getMovies(API_URL));
              return "now-playing";
        }

        public List<Movie> getMovies(String route){
            RestTemplate restTemplate = new RestTemplate();
            ResultsPage results  = restTemplate.getForObject(route, ResultsPage.class);


               return results.getResults();
        }

    }