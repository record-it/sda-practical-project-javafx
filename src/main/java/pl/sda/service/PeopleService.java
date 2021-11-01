package pl.sda.service;
import pl.sda.domain.DomainPerson;

import java.util.List;
import java.util.Optional;

public interface PeopleService {
    Optional<DomainPerson> findById(int id);
    List<DomainPerson> findAll();
}
