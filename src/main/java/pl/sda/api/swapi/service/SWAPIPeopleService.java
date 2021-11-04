package pl.sda.api.swapi.service;

import pl.sda.api.swapi.config.Resource;
import pl.sda.api.swapi.domain.FilmDomain;
import pl.sda.api.swapi.domain.PersonDomain;
import pl.sda.api.swapi.mapper.FilmMapper;
import pl.sda.api.swapi.mapper.PersonMapper;
import pl.sda.api.swapi.model.Film;
import pl.sda.api.swapi.model.Person;
import pl.sda.api.swapi.repository.SWAPIGenericRepository;
import pl.sda.api.swapi.repository.SWAPIRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SWAPIPeopleService implements PeopleService{
    private SWAPIRepository<Person> people = new SWAPIGenericRepository<>(Resource.PEOPLE);
    private SWAPIRepository<Film> films = new SWAPIGenericRepository<>(Resource.FILMS);
    @Override
    public Optional<PersonDomain> findById(int id) {
        try {
            final Optional<Person> optionalPerson = people.findById(id);
            if (optionalPerson.isPresent()){
                Person person = optionalPerson.get();
                final List<String> urlToFilms = person.getFilms();
                final List<FilmDomain> filmDomains = urlToFilms.stream()
                        .map(url -> {
                            try {
                                return films.findByUrl(url);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return Optional.empty();
                        })
                        .filter(d -> d.isPresent())
                        .map(film -> FilmMapper.mapTo((Film) film.get()))
                        .collect(Collectors.toList());
                return Optional.of(PersonMapper.mapTo(person, filmDomains));
            } else {
                return Optional.empty();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public int countPeopleTallerThanPerson(int personId) {
        try {
            final Optional<Person> optionalPerson = people.findById(personId);
            if (optionalPerson.isEmpty()) {
                return -1;
            }
            Person person = optionalPerson.get();
            final PersonDomain personDomain = PersonMapper.mapTo(person, Collections.emptyList());
            if (!personDomain.isKnownHeight()) {
                return -1;
            }
            final List<Person> all = people.findAll();
            return (int) people.findAll().stream()
                    .map(p -> PersonMapper.mapTo(p, Collections.emptyList()))
                    .filter(PersonDomain::isKnownHeight)
                    .filter(domain -> domain.getHeight() > personDomain.getHeight())
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public double sumOfMass() {
        try {
            final List<Person> people = this.people.findAll();
            List<PersonDomain> domain = new ArrayList<>();
            for (Person person : people) {
                domain.add(PersonMapper.mapTo(person, Collections.emptyList()));
            }
            double sum = 0;
            for (PersonDomain personDomain : domain) {
                sum += personDomain.isKnownMass() ? personDomain.getMass() : 0;
            }
            return sum;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
