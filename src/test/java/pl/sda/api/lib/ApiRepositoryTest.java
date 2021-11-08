package pl.sda.api.lib;

import org.junit.jupiter.api.Test;
import pl.sda.api.swapi.model.Film;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;


class ApiRepositoryTest {

    @Test
    public void getTest() throws IOException, InterruptedException {
        ApiRepository<Film> films = new ApiRepository<Film>(Film.class);
        final Optional<Film> film = films.get(URI.create("https://swapi.dev/api/films/1/"));
        System.out.println(film);
    }

}