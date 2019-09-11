package org.superbiz.moviefun.moviesapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.superbiz.moviefun.albumsapi.AlbumClient;
import org.superbiz.moviefun.albumsapi.AlbumFixtures;
import org.superbiz.moviefun.albumsapi.AlbumInfo;


import java.util.Map;

@Controller
public class SetupController {
    private final MoviesClient moviesClient;
    private final MovieFixtures movieFixtures;
    private final AlbumClient albumClient;
    private final AlbumFixtures albumFixtures;

    public SetupController(MoviesClient moviesClient, MovieFixtures movieFixtures, AlbumClient albumClient, AlbumFixtures albumFixtures) {
        this.moviesClient = moviesClient;
        this.movieFixtures = movieFixtures;
        this.albumClient = albumClient;
        this.albumFixtures = albumFixtures;
    }

    @GetMapping("/setup")
    public String setup(Map<String, Object> model) {
        for (MovieInfo movie : movieFixtures.load()) {
            moviesClient.addMovie(movie);
        }

        for (AlbumInfo album : albumFixtures.load()) {
            albumClient.addAlbum(album);
        }

        model.put("movies", moviesClient.getMovies());
        model.put("albums", albumClient.getAlbums());

        return "setup";
    }
}
