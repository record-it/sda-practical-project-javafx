package pl.sda.api.swapi.service;

import pl.sda.api.swapi.domain.PersonDomain;
import java.util.Optional;

public interface PeopleService {
    Optional<PersonDomain> findById(int id);
    int countPeopleTallerThanPerson(int personId);
    double sumOfMass();
}
