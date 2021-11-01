package pl.sda.service;
import pl.sda.api.swapi.model.Film;
import pl.sda.api.swapi.model.Person;
import pl.sda.api.swapi.repository.SWAPIRepository;
import pl.sda.domain.DomainFilm;
import pl.sda.domain.DomainPerson;
import pl.sda.mapper.FilmMapper;
import pl.sda.mapper.PersonMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ApiPeopleService implements PeopleService{
    private final SWAPIRepository<Person> people;
    private final SWAPIRepository<Film> films;

    public ApiPeopleService(SWAPIRepository<Person> people, SWAPIRepository<Film> films) {
        this.people = people;
        this.films = films;
    }

    @Override
    public Optional<DomainPerson> findById(int id) {
        try {
            final Optional<Person> optionalPerson = people.findById(id);
            if (optionalPerson.isEmpty()) {
                return Optional.empty();
            }
            final Person person = optionalPerson.get();
            List<DomainFilm> domainFilms = new ArrayList<>();
            for (String url : person.getFilms()) {

                domainFilms.add(FilmMapper.mapTo(films.findByUrl(url).orElseThrow()));
            }
            return Optional.of(PersonMapper.mapTo(person, domainFilms, null));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<DomainPerson> findAll() {
        List<DomainPerson> personList = new ArrayList<>();
        try {
            final List<Person> all = this.people.findAll();
            for(Person person: all){
                List<DomainFilm> personFilms = new ArrayList<>();
                for(String url: person.getFilms()){
                    personFilms.add(FilmMapper.mapTo(films.findByUrl(url).orElseThrow()));
                }
                personList.add(PersonMapper.mapTo(person, personFilms, null));
            }
            return personList;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
